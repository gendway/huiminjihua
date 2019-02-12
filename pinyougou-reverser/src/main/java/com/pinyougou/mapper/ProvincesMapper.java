package com.pinyougou.mapper;

import com.pinyougou.model.Provinces;
import com.pinyougou.model.ProvincesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProvincesMapper extends Mapper<Provinces> {
    int countByExample(ProvincesExample example);

    int deleteByExample(ProvincesExample example);

    List<Provinces> selectByExample(ProvincesExample example);

    int updateByExampleSelective(@Param("record") Provinces record, @Param("example") ProvincesExample example);

    int updateByExample(@Param("record") Provinces record, @Param("example") ProvincesExample example);
}