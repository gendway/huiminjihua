package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.UserService;

/********
 * author:shenkunlin
 * date:2018/8/31 17:08
 * description:深圳黑马
 * version:1.0
 ******/

/****
 * 1、集成dubbo的功能
 * 2、把该对象创建实例后给SpringIOC容器
 */
@Service
public class UserServiceImpl implements UserService {
    /***
     * 获取用户名
     * @return
     */
    @Override
    public String getName() {
        System.out.println("my name is xiaohong!");
        return "hello";
    }
}
