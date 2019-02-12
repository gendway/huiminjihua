package com.pinyougou.mapper;

import com.pinyougou.model.Specification;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecificationMapper extends Mapper<Specification> {
    List<Map<String,Object>> selectOptionList();
}