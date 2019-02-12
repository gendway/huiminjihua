package com.pinyougou.mapper;
import com.pinyougou.model.Brand;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface BrandMapper extends Mapper<Brand> {
    List<Brand> getAllBrand();

    List<Map<String,Object>> selectOptionList();
}