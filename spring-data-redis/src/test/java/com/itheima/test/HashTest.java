package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/10 17:53
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class HashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 右2层key
     *     namespace
     *              key
     */
    @Test
   public void testAdd(){
        redisTemplate.boundHashOps("NameSpace").put("1","小花花");
        redisTemplate.boundHashOps("NameSpace").put("2","大花花");
        redisTemplate.boundHashOps("NameSpace").put("3","中花花");

        redisTemplate.boundHashOps("XiaohongHAHA").put("1","中花花");
        redisTemplate.boundHashOps("XiaohongHAHA").put("2","中花花");
   }


    /***
     * 查询操作
     */
    @Test
   public void testGet(){
        Object result1 = redisTemplate.boundHashOps("NameSpace").get("1");
        System.out.println(result1);

        Object result2 = redisTemplate.boundHashOps("XiaohongHAHA").get("1");
        System.out.println(result2);
    }


    /***
     * 删除操作
     */
    @Test
    public void testDelete(){
        redisTemplate.boundHashOps("NameSpace").delete("2");
    }

    /***
     * 查询所有
     */
    @Test
    public void testGetAll(){
        List nameSpace = redisTemplate.boundHashOps("NameSpace").values();

        for (Object o : nameSpace) {
            System.out.println(o);
        }
    }

}
