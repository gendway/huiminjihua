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
public class ListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 左压栈
     * 从队列的左边开始增加数据
     */
    @Test
    public void testLeftAdd(){
        //只添加1条数据
        redisTemplate.boundListOps("Xiaohong").leftPush("花花");
        //批量添加
        redisTemplate.boundListOps("Xiaohong").leftPushAll("小红红","小花花","哈哈哈");
    }


    /***
     * 左压出栈
     */
    @Test
    public void testLeftGet(){
        Object result = redisTemplate.boundListOps("Xiaohong").leftPop();
        System.out.println(result);
    }




    /**
     * 右压栈
     */
    @Test
    public void testRightAdd(){
        //只添加1条数据
        redisTemplate.boundListOps("Xiaohong").rightPush("花花");
        //批量添加
        redisTemplate.boundListOps("Xiaohong").rightPushAll("小红红","小花花","哈哈哈");
    }




    /***
     * 左压出栈
     */
    @Test
    public void testRightGet(){
        Object result = redisTemplate.boundListOps("Xiaohong").rightPop();
        System.out.println(result);
    }

}
