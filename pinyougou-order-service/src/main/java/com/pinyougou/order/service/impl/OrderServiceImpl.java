package com.pinyougou.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.OrderItemMapper;
import com.pinyougou.mapper.OrderMapper;
import com.pinyougou.mapper.PayLogMapper;
import com.pinyougou.model.Cart;
import com.pinyougou.model.Order;
import com.pinyougou.model.OrderItem;
import com.pinyougou.model.PayLog;
import com.pinyougou.order.OrderService;
import com.pinyougou.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/25 17:55
 *
 ****/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private PayLogMapper payLogMapper;

    //根据用户名查询支付信息
    @Override
    public PayLog getPayLogByUserId(String username){
        return (PayLog) redisTemplate.boundHashOps("PayLog").get(username);
    }

    /***
     * 增加订单入库
     * @param order
     * @return
     */
    @Override
    public int add(Order order) {
        //取出Redis中的购物车数据--购物车明细
        List<Cart> carts = (List<Cart>) redisTemplate.boundHashOps("CartList40").get(order.getUserId());

        //总金额
        double money = 0;

        //记录所有订单编号
        List<String> orderIdList = new ArrayList<String>();

        //循环购物车数据，根据每个商家的购物车数据创建对应的订单
        for (Cart cart : carts) {
            //创建ID
            Long id = idWorker.nextId();
            //创建新的订单
            Order newOrder = new Order();
            newOrder.setOrderId(id);
            //买家ID
            newOrder.setUserId(order.getUserId());
            //创建时间
            newOrder.setCreateTime(order.getCreateTime());
            //修改时间
            newOrder.setUpdateTime(order.getUpdateTime());
            //状态    1未付款
            newOrder.setStatus(order.getStatus());
            //来源  PC
            newOrder.setSourceType(order.getSourceType());
            //商家ID
            newOrder.setSellerId(cart.getSellerId());

            //设置支付类型
            newOrder.setPaymentType(order.getPaymentType());

            //总金额
            double totalFee = 0;

            //循环增加订单明细
            for (OrderItem orderItem : cart.getOrderItemList()) {
                orderItem.setId(idWorker.nextId());
                //设置订单编号
               orderItem.setOrderId(id);

                //设置商家ID
                orderItem.setSellerId(cart.getSellerId());

                //总金额计算
                totalFee+=orderItem.getTotalFee().doubleValue();

                //增加订单明细
                orderItemMapper.insertSelective(orderItem);
            }

            //设置订单总金额
            newOrder.setPayment(new BigDecimal(totalFee));

            //增加订单
            orderMapper.insertSelective(newOrder);

            //计算所有订单总金额
            money+=totalFee;

            //记录当前订单编号
            orderIdList.add(id+"");
        }

        // XML 文件 forEach 批量增加

        //清空购物车
        //redisTemplate.boundHashOps("CartList40").delete(order.getUserId());


        //添加支付日志
        if(order.getPaymentType().equals("1")){
            PayLog payLog = new PayLog();
            payLog.setOutTradeNo(idWorker.nextId()+"");     //交易编号
            payLog.setCreateTime(order.getCreateTime());    //创建时间
            payLog.setTotalFee((long) money);               //支付总金额
            payLog.setUserId(order.getUserId());            //用户编号
            payLog.setTradeState("0");                      //待支付
            //2334,1111,666
            payLog.setOrderList(orderIdList.toString().replace("[","").replace("]","").replace(" ",""));                        //支付的订单编号
            payLog.setPayType("1");                         //线上支付

            //增加支付日志
            payLogMapper.insertSelective(payLog);

            //将交易日志存入缓存
            redisTemplate.boundHashOps("PayLog").put(order.getUserId(),payLog);
        }
        return 1;
    }

    /***
     * 修改订单装填和支付日志状态
     * @param username
     * @param transaction_id
     */
    @Override
    public void updatePayStatus(String username, String transaction_id) {
        //获取支付日志
        PayLog payLog = (PayLog) redisTemplate.boundHashOps("PayLog").get(username);

        if(payLog!=null){
            //修改订单状态   2334,1111,666
            String orderList = payLog.getOrderList();

            //需要修改状态的订单编号
            String[] ids = orderList.split(",");

            //循环修改订单状态
            for (String id : ids) {
                Order order = new Order();
                order.setOrderId(Long.parseLong(id));
                order.setStatus("2");
                order.setPaymentTime(new Date());   //实质上应该获取腾讯给你返回的支付时间

                orderMapper.updateByPrimaryKeySelective(order);
            }
            //修改日志状态
            payLog.setTradeState("1");
            payLog.setPayTime(new Date());
            payLog.setTransactionId(transaction_id);
            payLogMapper.updateByPrimaryKeySelective(payLog);

            //清空缓存
            redisTemplate.boundHashOps("PayLog").delete(username);
        }
    }
}
