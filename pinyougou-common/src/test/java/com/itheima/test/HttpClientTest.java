package com.itheima.test;

import com.pinyougou.util.HttpClient;

import java.io.IOException;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/26 16:02
 *
 ****/
public class HttpClientTest {


    public static void main(String[] args) throws Exception {
        //HttpClient   参数：url
        HttpClient httpClient = new HttpClient("http://www.itheima.com");
        //设置是否是https服务
        httpClient.setHttps(false);
        //xml参数
        httpClient.setXmlParam("");
        //发送请求
        httpClient.post();

        //获取响应数据
        String content = httpClient.getContent();
        System.out.println(content);
    }

}
