package com.pinyougou.mapper;

import com.pinyougou.model.ItemCat;
import com.pinyougou.model.ItemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ItemCatMapper extends Mapper<ItemCat> {
    int countByExample(ItemCatExample example);

    int deleteByExample(ItemCatExample example);

    List<ItemCat> selectByExample(ItemCatExample example);

    int updateByExampleSelective(@Param("record") ItemCat record, @Param("example") ItemCatExample example);

    int updateByExample(@Param("record") ItemCat record, @Param("example") ItemCatExample example);
}