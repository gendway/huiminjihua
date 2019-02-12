package com.pinyougou.mapper;

import com.pinyougou.model.Cities;
import com.pinyougou.model.CitiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CitiesMapper extends Mapper<Cities> {
    int countByExample(CitiesExample example);

    int deleteByExample(CitiesExample example);

    List<Cities> selectByExample(CitiesExample example);

    int updateByExampleSelective(@Param("record") Cities record, @Param("example") CitiesExample example);

    int updateByExample(@Param("record") Cities record, @Param("example") CitiesExample example);
}