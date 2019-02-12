package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/5 16:24
 *
 ****/
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    /****
     * 获取用户名
     * @return
     */
    @RequestMapping(value = "/name")
    public String getUerName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
