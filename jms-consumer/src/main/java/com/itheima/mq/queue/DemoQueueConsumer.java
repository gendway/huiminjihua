package com.itheima.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 16:16
 *  点对点消息消费
 ****/
public class DemoQueueConsumer {


    //消息消费实现
    public static void main(String[] args)  throws Exception{
        //创建链接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.211.128:61616");

        //创建链接对象Connection
        Connection connection = connectionFactory.createConnection();

        //开启链接
        connection.start();

        //创建会话对象Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //指定接收的队列地址
        Queue queue = session.createQueue("queue_test");

        //创建消息接收对象
        MessageConsumer consumer = session.createConsumer(queue);

        //监听模式实现消息接收   创建了MessageListener的实现类
        //监听模式相当于开了一个线程
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message!=null){
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;

                        try {
                            System.out.println("监听收到的消息："+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //需要让主线程不挂
        //Thread.sleep(10000);
        System.in.read();

        //关闭资源
        session.close();
        connection.close();
    }

    //消息消费实现
    public static void main_bak(String[] args)  throws Exception{
        //创建链接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.211.128:61616");

        //创建链接对象Connection
        Connection connection = connectionFactory.createConnection();

        //开启链接
        connection.start();

        //创建会话对象Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //指定接收的队列地址
        Queue queue = session.createQueue("queue_test");

        //创建消息接收对象
        MessageConsumer consumer = session.createConsumer(queue);

        //接收指定队列的消息
        while (true){
            //接收消息
            Message message = consumer.receive(10000);
            if(message!=null){
                if(message instanceof TextMessage){
                    //将消息强转TextMessage对象
                    TextMessage textMessage = (TextMessage) message;

                    System.out.println("读取到的消息："+textMessage.getText());

                    //跳出循环
                    break;
                }
            }
        }

        //关闭资源
        session.close();
        connection.close();



        //关闭资源

    }

}
