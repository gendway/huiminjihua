package com.itheima.test;

import com.github.wxpay.sdk.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 15:34
 *
 ****/
public class WeixinToolsTest {

    public static void main(String[] args) throws Exception {
        //生成随机字符
        String str = WXPayUtil.generateNonceStr();
        System.out.println(str);

        //Map-XML
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("username","小白");
        dataMap.put("age","19");
        dataMap.put("address","深圳中粮黑马");
        String paramXml = WXPayUtil.mapToXml(dataMap);
        System.out.println(paramXml);

        //XML-Map
        Map<String, String> xml2Map = WXPayUtil.xmlToMap(paramXml);
        //System.out.println(xml2Map);


        //Map-XML带有签名
        String strXml = WXPayUtil.generateSignedXml(dataMap, "DSFSDFSDFSDFSD");
        System.out.println(strXml);
    }


}
