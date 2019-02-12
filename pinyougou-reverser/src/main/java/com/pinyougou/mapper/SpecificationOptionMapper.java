package com.pinyougou.mapper;

import com.pinyougou.model.SpecificationOption;
import com.pinyougou.model.SpecificationOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SpecificationOptionMapper extends Mapper<SpecificationOption> {
    int countByExample(SpecificationOptionExample example);

    int deleteByExample(SpecificationOptionExample example);

    List<SpecificationOption> selectByExample(SpecificationOptionExample example);

    int updateByExampleSelective(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionExample example);

    int updateByExample(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionExample example);
}