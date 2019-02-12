package com.itheima.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/5 15:06
 *   该类的作用可以完全顶替之前-security.xml
 *   SpringSecurityConfig:必须继承一个类 WebSecurityConfigurerAdapter
 *****/
@Component
@EnableWebSecurity      //开启SpringSecurity注解配置
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //三个方法的方法名都是：configure

    /*****
     * 1、公开链接或者静态资源取消安全控制，用户无需登录也能访问
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略某些地址的安全控制，也就是用户无需登录页能访问
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/*.html");
    }


    /*****
     * 2、配置需要根据用户登陆后的角色决定访问规则的链接信息配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用户请求授权配置
        http.authorizeRequests()
                // /jsp/下所有的文件都需要ADMIN的权限才能访问
                .antMatchers("/jsp/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/pages/**").access("hasAnyRole('USER')");


        //手动开启登录配置
        http.formLogin()
                .loginPage("/login.html")   //配置登录页面  login.html
                .defaultSuccessUrl("/images/1.png",true) //登录成功后跳转的欢迎页
                .loginProcessingUrl("/login");       //登录处理的地址

        //登出配置
        http.logout()
            .invalidateHttpSession(true)    //让session销毁
            .logoutUrl("/logout")           //退出地址
            .logoutSuccessUrl("/login.html");

        //异常解决
        http.exceptionHandling().accessDeniedPage("/error.html");

        //一个用户只允许在一个地方登录，如果该账号在异地登录，则当前用户被挤下线
        http.sessionManagement().maximumSessions(1).expiredUrl("/login.html");

        //关闭CSRF
        http.csrf().disable();
    }


    /***
     * UserDetailsServiceImpl在SpringIOC容器中
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /***
     * 3、授权认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ROLE_不需要写
        //auth.inMemoryAuthentication().withUser("xiaohong").password("123456").roles("ADMIN");

        //自定义授权认证类需要实现一个接口UserDetailsService
        auth.userDetailsService(userDetailsService);

    }
}
