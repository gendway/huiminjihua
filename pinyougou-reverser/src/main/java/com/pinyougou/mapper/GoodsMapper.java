package com.pinyougou.mapper;

import com.pinyougou.model.Goods;
import com.pinyougou.model.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsMapper extends Mapper<Goods> {
    int countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);
}