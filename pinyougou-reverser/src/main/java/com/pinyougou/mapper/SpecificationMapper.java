package com.pinyougou.mapper;

import com.pinyougou.model.Specification;
import com.pinyougou.model.SpecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SpecificationMapper extends Mapper<Specification> {
    int countByExample(SpecificationExample example);

    int deleteByExample(SpecificationExample example);

    List<Specification> selectByExample(SpecificationExample example);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationExample example);
}