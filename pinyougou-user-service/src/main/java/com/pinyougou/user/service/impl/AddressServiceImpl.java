package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.AddressMapper;
import com.pinyougou.model.Address;
import com.pinyougou.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/25 16:01
 *
 ****/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /***
     * 根据用户的ID查询收件地址
     * @param userid
     * @return
     */
    @Override
    public List<Address> getListByUserId(String userid) {
        //select * from tb_address where userid=?
        Address address = new Address();
        address.setUserId(userid);
        return addressMapper.select(address);
    }
}
