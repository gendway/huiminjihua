package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 15:27
 *
 ****/
@RestController
@RequestMapping("info")
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        System.out.println("helloWorld");
        return "ok";
    }
}
