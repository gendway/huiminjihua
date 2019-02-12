package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/10 17:53
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class SetTest {

    @Autowired
    private RedisTemplate redisTemplate;


    /***
     * 增加数据测试
     */
    @Test
    public void testAdd(){
        redisTemplate.boundSetOps("username").add("小红");
        redisTemplate.boundSetOps("username").add("小黑");
        redisTemplate.boundSetOps("username").add("小红");
        redisTemplate.boundSetOps("username").add("小红");
    }



    /***
     * 查询操作
     */
    @Test
    public void testGet(){
        Set members = redisTemplate.boundSetOps("username").members();
        System.out.println(members);
    }

    /***
     * 删除测试
     */
    @Test
    public void testDelete(){
        redisTemplate.boundSetOps("username").remove("小黑");
    }

}
