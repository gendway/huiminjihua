package com.pinyougou.manager.service;

import com.pinyougou.mq.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 18:09
 *
 ****/
@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination destination;

    /***
     * 消息发送
     * @param messageInfo
     */
    public void sendObjectMessage(MessageInfo messageInfo){
        //发送消息即可
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //创建一个ObjectMessage对象
                ObjectMessage objectMessage = session.createObjectMessage();

                //封装一个数据
                objectMessage.setObject(messageInfo);
                return objectMessage;
            }
        });
    }

}
