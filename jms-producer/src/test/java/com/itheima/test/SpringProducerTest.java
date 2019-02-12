package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.mq.spring.MessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 17:02
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mq.xml")
public class SpringProducerTest {

    @Autowired
    private MessageProducer messageProducer;

    /***
     * 文本消息测试
     */
    @Test
    public void testSendTextMessage(){
        messageProducer.sendTextMessage("小红啊，你吃饭啊"+Math.random()*10000);
    }


    /***
     * 发送MapMessage类型测试
     */
    @Test
    public void testSendMapMessage(){
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("username","小红");
        dataMap.put("age","21");
        dataMap.put("address","深圳中粮!");
        messageProducer.sendMapMessage(dataMap);
    }


    /***
     * 发送ObjectMessage类型测试
     */
    @Test
    public void testSendObjectMessage(){
        User user = new User(123, "小红", new Date());
        messageProducer.sendObjectMessage(user);
    }

}
