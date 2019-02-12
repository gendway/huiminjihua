package com.pinyougou.mapper;

import com.pinyougou.model.ContentCategory;
import com.pinyougou.model.ContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ContentCategoryMapper extends Mapper<ContentCategory> {
    int countByExample(ContentCategoryExample example);

    int deleteByExample(ContentCategoryExample example);

    List<ContentCategory> selectByExample(ContentCategoryExample example);

    int updateByExampleSelective(@Param("record") ContentCategory record, @Param("example") ContentCategoryExample example);

    int updateByExample(@Param("record") ContentCategory record, @Param("example") ContentCategoryExample example);
}