package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.model.Address;
import com.pinyougou.user.service.AddressService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/25 16:03
 *
 ****/
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /****
     * 用户名：当前登录的用户
     * @return
     */
    @RequestMapping(value = "/user/list")
    public List<Address> getListByUserId(){
        //获取用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //获取用户的收货地址
        List<Address> address = addressService.getListByUserId(username);

        return  address;
    }

}
