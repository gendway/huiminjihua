package com.pinyougou.pay.service;

import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 17:42
 *
 ****/
public interface WeixinPayService {

    /***
     * 获取支付二维码的url
     * out_trade_no:商户生成的交易编号
     * total_fee:   交易金额
     */
    Map createNative(String out_trade_no,String total_fee);


    /***
     * 查询订单状态
     * out_trade_no:商户生成的交易编号
     */
    Map queryPayStatus(String out_trade_no);

    /***
     * 关闭订单
     * @param tradeoutno
     * @return
     */
    Map<String,String> closePay(String tradeoutno);
}
