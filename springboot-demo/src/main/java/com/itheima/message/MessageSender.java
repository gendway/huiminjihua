package com.itheima.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 15:09
 *
 ****/
@Component
public class MessageSender {

    //JmsMessagingTemplate:用来发送消息
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /***
     * 用来发送文本消息
     * @param text
     */
    public void sendTextMessage(String text){
        jmsMessagingTemplate.convertAndSend("UserInfo",text);
    }

    /****
     * 发送Map类型
     */
    public void sendMapMessage(){
        Map<String,String> dataMap  = new HashMap<String,String>();
        dataMap.put("useranme","小红");
        dataMap.put("address","中国深圳中粮黑马程序员");
        dataMap.put("age","22");

        //发送Map数据类型
        jmsMessagingTemplate.convertAndSend("Message-Map",dataMap);
    }

}
