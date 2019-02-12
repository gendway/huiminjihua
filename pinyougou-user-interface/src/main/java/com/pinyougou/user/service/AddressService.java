package com.pinyougou.user.service;

import com.pinyougou.model.Address;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/25 15:56
 *
 ****/
public interface AddressService {

    /**
     * 根据用户ID查询用户收件地址列表
     * userid:username
     * @return
     */
    List<Address> getListByUserId(String userid);
}
