package com.itheima.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/20 17:27
 *
 ****/
@Component
public class Consumer {
    @JmsListener(destination="itcast")
    public void readMessage(String text){
        System.out.println("读到的消息："+text);
    }
}
