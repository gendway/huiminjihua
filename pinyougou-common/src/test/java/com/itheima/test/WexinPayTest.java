package com.itheima.test;

import com.github.wxpay.sdk.WXPayUtil;
import com.pinyougou.util.HttpClient;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 16:46
 *
 ****/
public class WexinPayTest {


    /***
     * 查询微信支付状态
     */
    public static  void queryPayStatus() throws Exception{
        //所需参数
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("appid","wx8397f8696b538317");
        dataMap.put("mch_id","1473426802");
        dataMap.put("out_trade_no","12366888999");
        dataMap.put("nonce_str",WXPayUtil.generateNonceStr());


        //转xml格式，并生成签名
        String xmlParam = WXPayUtil.generateSignedXml(dataMap,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb");

        //HttpClient
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";
        HttpClient httpClient = new HttpClient(url);

        //设置https
        httpClient.setHttps(true);


        //发送参数设置
        httpClient.setXmlParam(xmlParam);

        //执行post请求
        httpClient.post();

        //获取参数
        String content = httpClient.getContent();

        //转成Map
        Map<String, String> responseMap = WXPayUtil.xmlToMap(content);

        System.out.println(responseMap);

        System.out.println(responseMap.get("trade_state"));

    }

    /***
     * 创建微信二维码支付地址查询
     */
    public static  void createNative() throws Exception{
        //需要发送的参数
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("appid","wx8397f8696b538317");                  //应用ID
        dataMap.put("mch_id","1473426802");                         //商户编号
        dataMap.put("nonce_str", WXPayUtil.generateNonceStr());     //随机数
        dataMap.put("body","品优购小黄人");                         //产品描述
        dataMap.put("out_trade_no","12366888999");                  //商户生成的订单号
        dataMap.put("total_fee","1");                               //交易金额，单位分
        dataMap.put("spbill_create_ip","192.168.211.128");         //支付终端ID
        dataMap.put("notify_url","http://www.itheima.com");         //回调地址
        dataMap.put("trade_type","NATIVE");                         //支付类型

        //生成xml格式并生成签名
        String paramXml = WXPayUtil.generateSignedXml(dataMap,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb");

        System.out.println(paramXml);

        //创建HttpClient
        String url ="https://api.mch.weixin.qq.com/pay/unifiedorder";
        HttpClient httpClient = new HttpClient(url);

        //设置https请求
        httpClient.setHttps(true);

        //设置要发送的xml参数
        httpClient.setXmlParam(paramXml);

        //执行请求
        httpClient.post();

        //获取返回结果
        String content = httpClient.getContent();
        System.out.println(content);

        //将结果转成Map，获取code_url
        Map<String,String> responseMap = WXPayUtil.xmlToMap(content);

        String payurl = responseMap.get("code_url");

        System.out.println(payurl);

    }

    public static void main(String[] args) throws Exception {
        //createNative();
        queryPayStatus();
    }


}
