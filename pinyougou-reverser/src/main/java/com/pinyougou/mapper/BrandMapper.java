package com.pinyougou.mapper;

import com.pinyougou.model.Brand;
import com.pinyougou.model.BrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand> {
    int countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    List<Brand> selectByExample(BrandExample example);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);
}