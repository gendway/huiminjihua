package com.pinyougou.mapper;

import com.pinyougou.model.Areas;
import com.pinyougou.model.AreasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AreasMapper extends Mapper<Areas> {
    int countByExample(AreasExample example);

    int deleteByExample(AreasExample example);

    List<Areas> selectByExample(AreasExample example);

    int updateByExampleSelective(@Param("record") Areas record, @Param("example") AreasExample example);

    int updateByExample(@Param("record") Areas record, @Param("example") AreasExample example);
}