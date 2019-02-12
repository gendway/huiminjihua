package com.pinyougou.seckill.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 18:45
 *
 ****/
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    /***
     * 地址跳转页
     * @param request
     * @return
     */
    @RequestMapping(value = "/loading")
    public String loading(HttpServletRequest request){
        //请求的来源
        String url = request.getHeader("referer");
        if(StringUtils.isNotBlank(url)){
            return "redirect:"+url;
        }
        return "redirect:/seckill-index.html";
    }
}
