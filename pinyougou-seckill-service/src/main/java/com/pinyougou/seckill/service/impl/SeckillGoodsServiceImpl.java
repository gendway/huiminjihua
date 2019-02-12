package com.pinyougou.seckill.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.model.SeckillGoods;
import com.pinyougou.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 16:07
 *
 ****/
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 根据商品ID查询秒杀商品信息
     * @param id
     * @return
     */
    @Override
    public SeckillGoods getOne(Long id) {
        return (SeckillGoods) redisTemplate.boundHashOps("SeckillGoods").get(id);
    }

    /***
     * 商品秒杀列表
     * @return
     */
    @Override
    public List<SeckillGoods> list() {
        return redisTemplate.boundHashOps("SeckillGoods").values();
    }
}
