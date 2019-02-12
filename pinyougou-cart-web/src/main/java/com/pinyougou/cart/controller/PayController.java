package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.http.Result;
import com.pinyougou.model.PayLog;
import com.pinyougou.order.OrderService;
import com.pinyougou.pay.service.WeixinPayService;
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
    private OrderService orderService;

    /***
     * 创建二维码
     * @return
     */
    @RequestMapping(value = "/createNative")
    public Map<String, String> createNative() {
        //用户登录名字
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //查询支付日志
        PayLog payLog=orderService.getPayLogByUserId(username);

        //return weixinPayService.createNative(payLog.getOutTradeNo(), (payLog.getTotalFee()*100)+"");
        return weixinPayService.createNative(payLog.getOutTradeNo(), "1");
    }


    /***
     * 订单状态查询
     * @param tradeoutno
     * @return
     */
    @RequestMapping(value = "/queryPayStatus")
    public Result queryPayStatus(String tradeoutno) throws InterruptedException {
        int count=0;

        while (true){
            //查询交易状态
            Map<String, String> map = weixinPayService.queryPayStatus(tradeoutno);

            if (map == null) {
                return new Result(false, "查询支付失败！");
            }

            //支付成功
            if (map.get("trade_state").equals("SUCCESS")) {
                //获取用户登录名
                String username = SecurityContextHolder.getContext().getAuthentication().getName();

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
                return new Result(false, "timeout");
            }
        }
    }


}
