package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/10 17:53
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class ValueTest {

    @Autowired
    private RedisTemplate redisTemplate;


    /***
     * 增加数据测试
     */
    @Test
    public void testAdd(){
        //boundValueOps用于操作简单的key:value类型
        redisTemplate.boundValueOps("username").set("小红红");
    }


    /***
     * 查询操作
     */
    @Test
    public void testGet(){
        Object username = redisTemplate.boundValueOps("username").get();
        System.out.println(username);
    }

    /***
     * 删除测试
     */
    @Test
    public void testDelete(){
        //redisTemplate.boundValueOps("username")
        redisTemplate.delete("username");
    }

}
