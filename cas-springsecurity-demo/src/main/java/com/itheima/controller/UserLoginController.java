package com.itheima.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/22 18:09
 *
 ****/
@RestController
@RequestMapping(value = "/user")
public class UserLoginController {

    /***
     * 获取用户登录名
     * @return
     */
    @RequestMapping(value = "/name")
    public String getName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
