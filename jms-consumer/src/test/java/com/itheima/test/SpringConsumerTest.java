package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/19 17:12
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mq.xml")
public class SpringConsumerTest {

    /***
     * 和运行程序，保证主线程不挂
     */
    @Test
    public void testSleep() throws IOException {
        System.in.read();
    }

}
