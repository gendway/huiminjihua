package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/********
 * author:shenkunlin
 * date:2018/8/31 17:30
 * description:深圳黑马
 * version:1.0
 ******/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    /*@Autowired*/

    /****
     * 1)给单钱接口创建一个代理对象
     * 2)注入远程调用的信息给代理对象
     */
    @Reference
    private UserService userService;

    /***
     * 获取用户名远程测试
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/name")
    public String getName(){
        return userService.getName();
    }

}
