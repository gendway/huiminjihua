package com.pinyougou.mapper;

import com.pinyougou.model.FreightTemplate;
import com.pinyougou.model.FreightTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface FreightTemplateMapper extends Mapper<FreightTemplate> {
    int countByExample(FreightTemplateExample example);

    int deleteByExample(FreightTemplateExample example);

    List<FreightTemplate> selectByExample(FreightTemplateExample example);

    int updateByExampleSelective(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByExample(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);
}