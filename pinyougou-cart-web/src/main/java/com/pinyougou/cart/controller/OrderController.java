package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.http.Result;
import com.pinyougou.model.Order;
import com.pinyougou.order.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/25 17:11
 *
 ****/
@RestController
@RequestMapping(value = "/order")
public class OrderController {


    @Reference
    private OrderService orderService;

    /***
     * 接收前台传入的订单数据
     * @param order
     * @return
     */
    @RequestMapping(value = "/add")
    public Result add(@RequestBody Order order){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            //买家ID
            order.setUserId(username);

            //创建时间
            order.setCreateTime(new Date());

            //修改时间
            order.setUpdateTime(order.getCreateTime());

            //状态    1未付款
            order.setStatus("1");

            //来源  PC
            order.setSourceType("2");

            //调用order实现订单的增加
            int acount = orderService.add(order);
            if(acount>0){
                return  new Result(true,"恭喜你增加成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new Result(false,"恭喜你增加不成功！");
    }
}
