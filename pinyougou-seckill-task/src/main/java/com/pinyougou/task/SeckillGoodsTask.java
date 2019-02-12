package com.pinyougou.task;

import com.pinyougou.mapper.SeckillGoodsMapper;
import com.pinyougou.model.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/28 15:11
 *
 *      任务调度：定时任务
 ****/
@Component
public class SeckillGoodsTask {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /****
     *  Scheduled:任务调度注解
     *      cron:用来编写时间表达式
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void pushSeckillGoods2Redis(){
        /****
         * 1)审核通过
         * 2)库存>0
         * 3)开始时间=<当前时间<=结束时间
         * 4)之前如果已经加入到Redis缓存的数据，现在需要排除不加  1,2
         */
        Example example = new Example(SeckillGoods.class);
        Example.Criteria criteria = example.createCriteria();

        //审核通过
        criteria.andEqualTo("status","1");

        //库存>0
        criteria.andGreaterThan("stockCount",0);

        //开始时间=<当前时间<=结束时间
        // select * from tb_seckillgoods where now() between start_time and end_time
        criteria.andCondition("NOW() BETWEEN start_time AND end_time");

        //之前如果已经加入到Redis缓存的数据，现在需要排除不加  1,2    id not in(1,2)
        Set<Long> ids = redisTemplate.boundHashOps("SeckillGoods").keys();
        if(ids!=null && ids.size()>0){
            criteria.andNotIn("id",ids);
        }

        //查询数据
        List<SeckillGoods> goods = seckillGoodsMapper.selectByExample(example);

        System.out.println((goods==null || goods.size()<=0? "没有数据":"共加入"+goods.size()));

        //给每个商品单独存储到Redis中    列表查询   | 单个商品查询
        for (SeckillGoods good : goods) {
            System.out.println(good.getId());
            redisTemplate.boundHashOps("SeckillGoods").put(good.getId(),good);

            //存入队列操作
            pushCount2Queue(good);
        }
        System.out.println("----------------------");
    }

    //将商品个数信息存入队列
    public void pushCount2Queue(SeckillGoods good){
        for (int i = 0; i <good.getStockCount() ; i++) {
            redisTemplate.boundListOps("SeckillGoods_Id_"+good.getId()).leftPush(good.getId());
        }
    }

}
