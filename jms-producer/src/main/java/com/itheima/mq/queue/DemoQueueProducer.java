package com.itheima.mq.queue;

import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 15:49
 *    点对点发送消息
 ****/
public class DemoQueueProducer {


    //点对点发送消息
    public static void main(String[] args) throws Exception{
        //创建链接对象工厂ConnectionFactory  JMS只定义了规范
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.211.128:61616");

        //创建链接对象Connection
        Connection connection = connectionFactory.createConnection();

        //开启链接对象
        connection.start();

        //创建会话对象Session
        //第1个参数：是否开启事务
        //第2个参数：应答模式
        //          1)AUTO_ACKNOWLEDGE:自动应答模式
        //          2)CLIENT_ACKNOWLEDGE:客户端应答   a.不会有重复数据
        //          3)DUPS_OK_ACKNOWLEDGE:客户端应答  b.容易产生重复数据
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建消息
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("小红你好高！"+Math.random()*1000);

        //指定消息发送的目标地址，发送到指定队列中
        Queue queue = session.createQueue("queue_test");

        //创建消息发送对象
        MessageProducer messageProducer = session.createProducer(queue);

        //消息发送实现
        messageProducer.send(textMessage);

        //资源关闭
        session.close();
        connection.close();
    }

}
