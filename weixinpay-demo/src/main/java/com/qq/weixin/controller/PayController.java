package com.qq.weixin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 16:40
 *
 ****/
@RestController
@RequestMapping(value = "/pay")
public class PayController {


    /***
     *
     * https://api.mch.weixin.qq.com/pay/unifiedorder
     * 生成支付链接地址
     */
    @RequestMapping(value = "/unifiedorder")
    public Map createNative(String appid,
                            String mch_id,
                            @RequestParam(required = false)String device_info,
                            String nonce_str){



        Map<String,String> dataMap= new HashMap<String,String>();
        dataMap.put("return_code","SUCCESS");
        dataMap.put("return_msg","FAIL");
        return  dataMap;
    }



}
