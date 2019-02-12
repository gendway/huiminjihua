package com.pinyougou.seckill.service;

import com.pinyougou.model.SeckillGoods;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 16:06
 *
 ****/
public interface SeckillGoodsService {


    /***
     * 根据ID查询秒杀商品
     * @param id
     * @return
     */
    SeckillGoods getOne(Long id);

    /****
     * 秒杀商品列表查询
     * @return
     */
    List<SeckillGoods> list();

}
