package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.http.Result;
import com.pinyougou.model.User;
import com.pinyougou.user.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Reference
    private UserService userService;

    /***
     * 获取用户名
     * @return
     */
    @RequestMapping(value = "/name")
    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    /***
     * 创建验证码
     * @return
     */
    @RequestMapping(value = "/create/code")
    public Result crateCode(String phone){
        try {
            //创建验证码
            userService.createCode(phone);
            return  new Result(true,"发送验证码成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new Result(false,"发送验证码不成功！");
    }


    /***
     * 增加User数据
     * @param user
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody User user,String code){
        try {
            //验证码是否有效
            Boolean bo = userService.checkCode(user.getPhone(),code);
            if(!bo){
                return  new Result(false,"该验证码无效！");
            }

            //判断用户名是否重复
            int count = userService.getUserByUserName(user.getUsername());
            if(count>0){
                return  new Result(false,"用户名被占用！");
            }

            //手机号是否被重复占用
            int mcount = userService.getPhoneCount(user.getPhone());
            if(mcount>0){
                return  new Result(false,"该手机号已经被占用！");
            }

            //执行增加
            int acount = userService.add(user);

            if(acount>0){
                //增加成功
               return new Result(true,"增加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"增加失败");
    }


}
