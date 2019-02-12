package com.pinyougou.mapper;

import com.pinyougou.model.Item;
import com.pinyougou.model.ItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ItemMapper extends Mapper<Item> {
    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    List<Item> selectByExample(ItemExample example);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);
}