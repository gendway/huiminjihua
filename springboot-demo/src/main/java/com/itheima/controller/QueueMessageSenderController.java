package com.itheima.controller;

import com.itheima.message.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 15:13
 *
 ****/
@RestController
@RequestMapping(value = "/queue")
public class QueueMessageSenderController {

    @Autowired
    private MessageSender messageSender;

    /***
     * 测试发送文本消息
     * @param text
     * @return
     */
    @RequestMapping(value = "/send/text")
    public String sendText(String text){
        //调用发送
        messageSender.sendTextMessage(text);
        return  "OK";
    }


    /***
     * 测试发送Map消息
     * @return
     */
    @RequestMapping(value = "/send/map")
    public String sendMap(){
        //调用发送
        messageSender.sendMapMessage();
        return  "OK";
    }

}
