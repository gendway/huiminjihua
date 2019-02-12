package com.itheima.mq.spring;

import com.itheima.domain.User;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 17:05
 *      Spring集成ActiveMQ实现消息接收
 ****/
@Component
public class SpringMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //文本消息
        if(message instanceof TextMessage){
            //强转文本类型
            TextMessage textMessage = (TextMessage) message;

            try {
                System.out.println("监听到的消息："+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        //接收MapMessage类型
        if(message instanceof MapMessage){
            //强转MapMessage类型
            MapMessage mapMessage = (MapMessage) message;

            try {
                //获取对应key的值
                Map<String,String> dataMap = (Map<String, String>) mapMessage.getObject("userData");
                System.out.println(dataMap);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        //接收ObjectMessage
        if(message instanceof ObjectMessage){
            //强转成ObjectMessage
            ObjectMessage objectMessage = (ObjectMessage) message;

            try {
                //获取数据，并强转成JavaBean
                User user = (User) objectMessage.getObject();
                System.out.println(user);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
