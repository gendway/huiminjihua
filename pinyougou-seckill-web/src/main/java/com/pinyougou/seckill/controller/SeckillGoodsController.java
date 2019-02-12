package com.pinyougou.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.model.SeckillGoods;
import com.pinyougou.seckill.service.SeckillGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 16:08
 *
 ****/
@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;

    /***
     * 根据ID查询商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/one")
    public SeckillGoods getOne(Long id){
        return seckillGoodsService.getOne(id);
    }



    /****
     * 秒杀商品列表
     * @return
     */
    @RequestMapping(value = "/list")
    public List<SeckillGoods> list(){
        return  seckillGoodsService.list();
    }

}
