package com.pinyougou.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.cart.service.CartService;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.model.Cart;
import com.pinyougou.model.Item;
import com.pinyougou.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/23 15:56
 *
 ****/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemMapper itemMapper;


    /***
     * 合并操作
     */
    @Override
    public List<Cart> mergeList(List<Cart> rediscarts,List<Cart> cookiecarts){

        //循环Cookie购物车数据
        for (Cart cart : cookiecarts) {
            //Cookie中的购物车明细
            List<OrderItem> orderItemList = cart.getOrderItemList();

            //循环购物车明细
            for (OrderItem orderItem : orderItemList) {
                //Item的id
                Long itemid = orderItem.getItemId();
                //购买该商品的个数
                Integer num = orderItem.getNum();

                //循环将数据加入到reids购物车中
                rediscarts = add(rediscarts,itemid,num);
            }

        }

        return rediscarts;
    }

    /****
     * List<Cart>
     *      Cart
     *          商家信息
     *         List<OrderItem>购买商品明细
     *
     * @param carts
     * @param itemid
     * @param num
     * @return
     */
    @Override
    public List<Cart> add(List<Cart> carts,Long itemid, Integer num) {
        //防止空指针
        if(carts==null){
            carts = new ArrayList<Cart>();
        }

        //查询该商品的信息
        Item item = itemMapper.selectByPrimaryKey(itemid);

        //判断该商品的商家购物车是否已经存在
        Cart cart = searchCart(carts,item.getSellerId());

        //如果存在，则获取该商家的购物车信息 Cart
        if(cart!=null){
            //获取该商家的购物车(Cart)里面所有商品明细，检查当前需要购买的商品是否已经加入了购物车
            OrderItem orderItem = searchOrderItem(cart.getOrderItemList(),itemid);

            //如果存在该商品购买明细，则让对应的数量增加+价格重新计算(总价=数量*单价)
            if(orderItem!=null){
                //数量增加=原有数量+现在要购买的数量
                orderItem.setNum (orderItem.getNum()+num);
                //总价格=单价*总数量
                double totalFee = orderItem.getPrice().doubleValue() * orderItem.getNum();
                orderItem.setTotalFee(new BigDecimal(totalFee));

                //如果购买的商品数量<=0，应该将该商品从该商家购物车(Cart)的集合中移除
                if(orderItem.getNum()<=0){
                    cart.getOrderItemList().remove(orderItem);
                }

                //如果该商家的购物车对象的购买商品明细集合没有数据了，则移除该商家对应的购物车
                if(cart.getOrderItemList().size()<=0){
                    carts.remove(cart);
                }
            }else{
                //如果不存在，则创建一个新的OrderItem商品明细，加入到该商家对应购物车的商品明细集合中
                orderItem = craeteOrderItem(item,num);
                cart.getOrderItemList().add(orderItem);
            }
        }else{
            //创建一个该商家的购物车(Cart)对象
            cart = new Cart();
            cart.setSellerId(item.getSellerId());
            cart.setSellerName(item.getSeller());

            //创建一个商品明细
            OrderItem orderItem = craeteOrderItem(item,num);

            //将商品明细加入到商家购物车明细集合中
            cart.getOrderItemList().add(orderItem);

            //将新建的Cart加入到carts集合中
            carts.add(cart);
        }
        return carts;
    }




    /***
     * 创建OrderItem对象
     * @param item
     * @param num
     * @return
     */
    public OrderItem craeteOrderItem(Item item,Integer num){
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(item.getId());
        orderItem.setGoodsId(item.getGoodsId());
        orderItem.setPicPath(item.getImage());
        orderItem.setTitle(item.getTitle());
        orderItem.setPrice(item.getPrice());
        orderItem.setNum(num);
        //总价格=单价*数量
        double totalFee = orderItem.getNum()*orderItem.getPrice().doubleValue();
        orderItem.setTotalFee(new BigDecimal(totalFee));
        return orderItem;
    }

    /***
     * 查找商品明细
     * @param orderItemList
     * @param itemid
     * @return
     */
    public OrderItem searchOrderItem(List<OrderItem> orderItemList,Long itemid){
        for (OrderItem orderItem : orderItemList) {
            if(orderItem.getItemId().longValue()==itemid.longValue()){
                return orderItem;
            }
        }
        return  null;
    }


    /***
     * 查询商家的购物车数据
     * @param carts
     * @param sellerId
     * @return
     */
    public Cart searchCart(List<Cart> carts,String sellerId){
        for (Cart cart : carts) {
            if(cart.getSellerId().equals(sellerId)){
                return cart;
            }
        }
        return  null;
    }


    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 加入购物车到redis
     * @param usernmae
     * @param carts
     */
    @Override
    public void addGoodsToRedis(String usernmae, List<Cart> carts) {
        redisTemplate.boundHashOps("CartList40").put(usernmae,carts);
    }

    /***
     * 从redis中取数据
     * @param username
     * @return
     */
    @Override
    public List<Cart> findCartListFromRedis(String username) {
        return (List<Cart>) redisTemplate.boundHashOps("CartList40").get(username);
    }
}
