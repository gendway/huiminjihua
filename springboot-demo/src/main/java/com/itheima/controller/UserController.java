package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 14:58
 *
 ****/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private Environment environment;


    /***
     * SpringBoot第一个请求
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("my name is xiaohonghong!");

        //获取配置文件信息
        String url  = environment.getProperty("url");
        return  "hello !"+url;
    }

}
