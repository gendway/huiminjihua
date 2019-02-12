package com.pinyougou.mapper;

import com.pinyougou.model.TypeTemplate;
import com.pinyougou.model.TypeTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TypeTemplateMapper extends Mapper<TypeTemplate> {
    int countByExample(TypeTemplateExample example);

    int deleteByExample(TypeTemplateExample example);

    List<TypeTemplate> selectByExample(TypeTemplateExample example);

    int updateByExampleSelective(@Param("record") TypeTemplate record, @Param("example") TypeTemplateExample example);

    int updateByExample(@Param("record") TypeTemplate record, @Param("example") TypeTemplateExample example);
}