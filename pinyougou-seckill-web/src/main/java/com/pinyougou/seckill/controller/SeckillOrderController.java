package com.pinyougou.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.http.Result;
import com.pinyougou.seckill.service.SeckillOrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 17:27
 *
 ****/
@RestController
@RequestMapping(value = "/seckill/order")
public class SeckillOrderController {

    @Reference
    private SeckillOrderService seckillOrderService;

    /***
     * 增加订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/add")
    public Result add(Long id){
        try {
            //用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            //匿名登录
            if(username.equals("anonymousUser")){
                return  new Result(false,"403");
            }
            //下订单
            seckillOrderService.add(username,id);

            return  new Result(true,"下单成功！");
        } catch (Exception e) {
            return  new Result(false,e.getMessage());
        }
    }

}
