package com.pinyougou.pay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wxpay.sdk.WXPayUtil;
import com.pinyougou.http.Result;
import com.pinyougou.pay.service.WeixinPayService;
import com.pinyougou.util.HttpClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 17:46
 *
 ****/
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Value("${appid}")
    private String appid;

    @Value("${partner}")
    private String partner;

    @Value("${partnerkey}")
    private String partnerkey;

    @Value("${notifyurl}")
    private String notifyurl;


    /***
     * 获取二维码的url
     * @param out_trade_no
     * @param total_fee
     * @return
     */
    @Override
    public Map createNative(String out_trade_no, String total_fee) {
        try {
            //需要发送的参数
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("appid",appid);                  //应用ID
            dataMap.put("mch_id",partner);                         //商户编号
            dataMap.put("nonce_str", WXPayUtil.generateNonceStr());     //随机数
            dataMap.put("body","品优购小黄人");                         //产品描述
            dataMap.put("out_trade_no",out_trade_no);                  //商户生成的订单号
            dataMap.put("total_fee",total_fee);                               //交易金额，单位分
            dataMap.put("spbill_create_ip","192.168.211.128");         //支付终端ID
            dataMap.put("notify_url",notifyurl);         //回调地址
            dataMap.put("trade_type","NATIVE");                         //支付类型

            //生成xml格式并生成签名
            String paramXml = WXPayUtil.generateSignedXml(dataMap,partnerkey);

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

            //将结果转成Map，获取code_url
            Map<String,String> responseMap = WXPayUtil.xmlToMap(content);


            //封装所需的参数
            Map response = new HashMap();
            response.put("out_trade_no",out_trade_no);
            response.put("total_fee",total_fee);
            response.put("code_url",responseMap.get("code_url"));

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 查询订单状态
     * @param out_trade_no
     * @return
     */
    @Override
    public Map queryPayStatus(String out_trade_no) {
        try {
            //所需参数
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("appid",appid);
            dataMap.put("mch_id",partner);
            dataMap.put("out_trade_no",out_trade_no);
            dataMap.put("nonce_str",WXPayUtil.generateNonceStr());


            //转xml格式，并生成签名
            String xmlParam = WXPayUtil.generateSignedXml(dataMap,partnerkey);

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
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 关闭订单
     * @param tradeoutno
     * @return
     */
    @Override
    public Map<String, String> closePay(String tradeoutno) {
        try {
            //所需参数
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("appid",appid);
            dataMap.put("mch_id",partner);
            dataMap.put("out_trade_no",tradeoutno);
            dataMap.put("nonce_str",WXPayUtil.generateNonceStr());

            //转xml格式，并生成签名
            String xmlParam = WXPayUtil.generateSignedXml(dataMap,partnerkey);

            //HttpClient
            String url = "https://api.mch.weixin.qq.com/pay/closeorder";
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
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
