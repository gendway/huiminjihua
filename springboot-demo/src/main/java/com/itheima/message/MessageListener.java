package com.itheima.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 15:12
 *
 ****/
@Component
public class MessageListener {

    /***
     * 消息监听
     * destination:指定了要读取的队列的消息
     * @param text
     */
    @JmsListener(destination = "UserInfo")
    public void readTextMessage(String text){
        System.out.println("读取的内容text:"+text);
    }

    /***
     * 去读Map消息
     */
    @JmsListener(destination = "Message-Map")
    public void readMapMessage(Map<String,String> dataMap){
        System.out.println("读取的Map内容:"+dataMap);
    }

}
