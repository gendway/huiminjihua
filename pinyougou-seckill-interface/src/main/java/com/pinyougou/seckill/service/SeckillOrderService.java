package com.pinyougou.seckill.service;

import com.pinyougou.model.SeckillOrder;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 17:28
 *
 ****/
public interface SeckillOrderService {

    /***
     * 增加订单
     * @param id
     */
    void add(String username,Long id);

    /***
     * 根据用户名查询订单信息
     * @param username
     * @return
     */
    SeckillOrder getOrderByUserName(String username);

    /***
     * 修改订单状态
     * @param username
     * @param transaction_id
     */
    void updatePayStatus(String username, String transaction_id);

    /***
     * 根据用户名移除订单信息
     * @param username
     */
    void removeOrder(String username);
}
