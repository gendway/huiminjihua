package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.pinyougou.cart.service.CartService;
import com.pinyougou.http.Result;
import com.pinyougou.model.Cart;
import com.pinyougou.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/23 16:43
 *
 ****/
@RestController
@RequestMapping(value = "/cart")
public class CartController {


    @Reference
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /***
     * 查询购物车集合
     * @return
     */
    @RequestMapping(value = "/list")
    public List<Cart> list(){
        //获取用户登录账号
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //用户未登录，数据存在Cookie
        String json = CookieUtil.getCookieValue(request,"CookieName","UTF-8");

        //将购物车数据转成List集合
        List<Cart> cookiecarts = JSON.parseArray(json, Cart.class);


        //匿名(未登录)
        if(username.equals("anonymousUser")){
            return cookiecarts;
        }else{
            //非匿名(已登录)
            List<Cart> rediscarts = cartService.findCartListFromRedis(username);

            //合并操作
            if(cookiecarts!=null && cookiecarts.size()>0){
                //合并
                rediscarts = cartService.mergeList(rediscarts,cookiecarts);

                //需要将新集合重新加入到Redis覆盖原来的购物车数据
                cartService.addGoodsToRedis(username,rediscarts);

                //清除Cookie数据
                CookieUtil.deleteCookie(request,response,"CookieName");
            }
            return rediscarts;
        }
    }


    /****
     *  加入购物车
     * @param itemid        Item的id
     * @param num           购买的数量
     * @return
     *
     * Nginx   Apache
     */
    @CrossOrigin(origins={"http://localhost:18088"},allowCredentials="true")
    @RequestMapping(value = "/add")
    public Result add(Long itemid,Integer num){
        //解决跨域问题
        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:18088");    //指定允许哪些域名跨域请求
        //response.setHeader("Access-Control-Allow-Credentials", "true");                  //表示允许跨域请求发送Cookie数据

        //获取匿名账号
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //调用查询购物车集合
        List<Cart> carts = list();

        //调用加入购物车方法
        carts = cartService.add(carts,itemid,num);

        //匿名，加入到Cookie中
        if(username.equals("anonymousUser")){
            //List<Cart>---JSON--Cookie
            String json = JSON.toJSONString(carts);

            //将JSON数据存入到Cookie中
            CookieUtil.setCookie(request,response,"CookieName",json,2600*24*30,"UTF-8");
        }else{
            //非匿名(已登录)
             cartService.addGoodsToRedis(username,carts);
        }
        return new Result(true,"加入购物车成功！");
    }

}
