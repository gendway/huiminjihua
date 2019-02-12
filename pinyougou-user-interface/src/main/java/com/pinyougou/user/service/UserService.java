package com.pinyougou.user.service;

import com.pinyougou.model.User;

public interface UserService {

    /***
     * 查询用户信息
     * @param username
     * @return
     */
    User getUserInfoByUserName(String username);


    /***
     * 增加User信息
     * @param user
     * @return
     */
    int add(User user);

    //发送验证码
    void createCode(String phone) throws Exception;

    /***
     * 验证码校验
     * @param phone
     * @param code
     * @return
     */
    Boolean checkCode(String phone, String code);

    /**
     * 查询用户名存在的个数
     * @param username
     * @return
     */
    int getUserByUserName(String username);

    /**
     * 根据手机号判断对应的手机被注册个数
     * @param phone
     * @return
     */
    int getPhoneCount(String phone);
}
