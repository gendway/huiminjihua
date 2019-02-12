package com.pinyougou.cart.service;

import com.pinyougou.model.Cart;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/23 15:55
 *
 ****/
public interface CartService {

    /***
     * 加入购物车
     * @param carts     已经拥有的购物车集合
     * @param itemid    要加入购物车的Item的id
     * @param num       要购买的数量
     * @return
     */
    List<Cart> add(List<Cart> carts,Long itemid, Integer num);

    /***
     * 加入购物车
     * @param usernmae
     * @param carts
     */
    void addGoodsToRedis(String usernmae,List<Cart> carts);


    /***
     * 查询购物车
     * @param username
     * @return
     */
    List<Cart> findCartListFromRedis(String username);


    /***
     * 合并操作
     * @param rediscarts
     * @param cookiecarts
     * @return
     */
    List<Cart> mergeList(List<Cart> rediscarts,List<Cart> cookiecarts);
}
