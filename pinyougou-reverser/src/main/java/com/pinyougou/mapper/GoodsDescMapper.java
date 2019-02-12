package com.pinyougou.mapper;

import com.pinyougou.model.GoodsDesc;
import com.pinyougou.model.GoodsDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsDescMapper extends Mapper<GoodsDesc> {
    int countByExample(GoodsDescExample example);

    int deleteByExample(GoodsDescExample example);

    List<GoodsDesc> selectByExampleWithBLOBs(GoodsDescExample example);

    List<GoodsDesc> selectByExample(GoodsDescExample example);

    int updateByExampleSelective(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);

    int updateByExample(@Param("record") GoodsDesc record, @Param("example") GoodsDescExample example);
}