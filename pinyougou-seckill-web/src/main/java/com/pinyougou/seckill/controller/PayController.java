package com.pinyougou.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.http.Result;
import com.pinyougou.model.PayLog;
import com.pinyougou.model.SeckillOrder;
import com.pinyougou.pay.service.WeixinPayService;
import com.pinyougou.seckill.service.SeckillOrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 17:56
 *
 ****/
@RestController
@RequestMapping(value = "/pay")
public class PayController {

    @Reference
    private WeixinPayService weixinPayService;

    @Reference
    private SeckillOrderService orderService;

    /***
     * 创建二维码
     * @return
     */
    @RequestMapping(value = "/createNative")
    public Map<String, String> createNative() {
        //Redis--->key:username  value:SeckillOrder

        //获取用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //获取用户订单信息
        SeckillOrder order = orderService.getOrderByUserName(username);

        //return weixinPayService.createNative(order.getId()+"", order.getMoney().doubleValue()*100+"");
        return weixinPayService.createNative(order.getId()+"", "1");
    }


    /***
     * 订单状态查询
     * @param tradeoutno
     * @return
     */
    @RequestMapping(value = "/queryPayStatus")
    public Result queryPayStatus(String tradeoutno) throws InterruptedException {
        int count=0;
        //获取用户登录名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        while (true){
            //查询交易状态
            Map<String, String> map = weixinPayService.queryPayStatus(tradeoutno);

            if (map == null) {
                return new Result(false, "查询支付失败！");
            }

            //支付成功
            if (map.get("trade_state").equals("SUCCESS")) {
                //修改支付状态和日志状态
                orderService.updatePayStatus(username ,map.get("transaction_id"));

                return new Result(true, "支付成功！");
            }

            //支付失败
            if(map.get("trade_state").equals("PAYERROR")){
                return new Result(false, "支付失败！");
            }

            //休眠3秒
            Thread.sleep(3000);
            count++;
            if(count>10){
                //如果订单在30分钟以内(30秒)未支付，则取消订单
                //先关闭腾讯微信支付订单
                Map<String,String> closeResult = weixinPayService.closePay(tradeoutno);

                //关闭成功，则取消本地订单
                if(closeResult.get("result_code").equals("SUCCESS")){
                    //取消本地地订单
                    orderService.removeOrder(username);
                }else{
                    if(closeResult.get("err_code").equals("ORDERPAID")){
                        //如果用于已经支付，则修改本地订单状态为已支付
                        map = weixinPayService.queryPayStatus(tradeoutno);
                        //修改支付状态和日志状态
                        orderService.updatePayStatus(username ,map.get("transaction_id"));
                    }
                }
                return new Result(false, "timeout");
            }
        }
    }


}
