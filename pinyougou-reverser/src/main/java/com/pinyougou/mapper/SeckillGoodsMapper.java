package com.pinyougou.mapper;

import com.pinyougou.model.SeckillGoods;
import com.pinyougou.model.SeckillGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SeckillGoodsMapper extends Mapper<SeckillGoods> {
    int countByExample(SeckillGoodsExample example);

    int deleteByExample(SeckillGoodsExample example);

    List<SeckillGoods> selectByExample(SeckillGoodsExample example);

    int updateByExampleSelective(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);

    int updateByExample(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);
}