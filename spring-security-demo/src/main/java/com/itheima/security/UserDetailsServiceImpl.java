package com.itheima.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/5 15:49
 *
 ****/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    /***
     * 到数据库中查询用户账号密码角色信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        String passsword = "123456"; //模拟数据库中查询的数据

        //用户角色信息
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //添加一个角色
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //创建一个User
        return new User(username,passsword,authorities);
    }
}
