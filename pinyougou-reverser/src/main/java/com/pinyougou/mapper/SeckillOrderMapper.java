package com.pinyougou.mapper;

import com.pinyougou.model.SeckillOrder;
import com.pinyougou.model.SeckillOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SeckillOrderMapper extends Mapper<SeckillOrder> {
    int countByExample(SeckillOrderExample example);

    int deleteByExample(SeckillOrderExample example);

    List<SeckillOrder> selectByExample(SeckillOrderExample example);

    int updateByExampleSelective(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

    int updateByExample(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);
}