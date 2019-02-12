package com.itheima.mq.spring;

import com.itheima.domain.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 16:52
 *  集成Spring消息生产者
 ****/
@Component
public class MessageProducer {

    //消息发送对象JmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate;

    //发送地址
    @Autowired
    private Destination destination;


    /**
     * 发送文本消息
     */
    public void sendTextMessage(String text){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建消息对象
                TextMessage textMessage = session.createTextMessage();
                //设置消息内容
                textMessage.setText(text);
                return textMessage;
            }
        });
    }

    /****
     * 发送MapMessage类型
     * @param dataMap
     */
    public void sendMapMessage(Map<String,String> dataMap){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建一个MapMessage类型
                MapMessage mapMessage = session.createMapMessage();
                //设置键值对数据   MapMessage:key=value
                mapMessage.setObject("userData",dataMap);
                return mapMessage;
            }
        });
    }


    /***
     * ObjectMessage
     * 发送JavaBean
     */
    public void sendObjectMessage(User user){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建ObjectMessage
                ObjectMessage objectMessage = session.createObjectMessage();

                //设置发送的数据
                objectMessage.setObject(user);
                return objectMessage;
            }
        });
    }


}
