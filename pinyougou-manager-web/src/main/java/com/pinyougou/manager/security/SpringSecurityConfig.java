package com.pinyougou.manager.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/5 15:59
 *
 ****/
@Component
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /*****
     * 公开链接
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略公开链接的授权管理
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**")
                .antMatchers("/plugins/**")
                .antMatchers("/*.html");
    }


    /****
     * 登录后能访问的链接配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用户登录后访问控制
        http.authorizeRequests()
                //其他所有链接需要ADMIN角色权限才能访问
                .antMatchers("/**").access("hasAnyRole('ADMIN')");

        //CSRF禁用
        http.csrf().disable();


        //异常处理
        http.exceptionHandling().accessDeniedPage("/error.html");


        //一个用户只能在一个地方登录
        http.sessionManagement().maximumSessions(1).expiredUrl("/login.html");


        //登录
        http.formLogin()
                .loginPage("/login.html")   //登录页面
                .defaultSuccessUrl("/admin/index.html",true)    //登录成功后总是跳转到这个页面
                .loginProcessingUrl("/login")   //登录处理地址
                .failureUrl("/login.html");     //登录失败跳转地址

        //登出
        http.logout()
                .invalidateHttpSession(true)        //登出后销毁session
                .logoutUrl("/logout")               //登出处理地址
                .logoutSuccessUrl("/login.html");   //登出成功后跳转地址


        //默认情况，SpringSecurity禁止使用iframe，需要将iframe一些禁止选项关闭
        http.headers().frameOptions().disable();
    }


    /***
     * 授权配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")   //创建用户，用户名为 admin
                .password("123456")             //用户名的密码是123456
                .roles("ADMIN");                //用户的角色是ADMIN
    }
}
