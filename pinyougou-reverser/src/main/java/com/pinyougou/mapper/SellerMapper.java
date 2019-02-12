package com.pinyougou.mapper;

import com.pinyougou.model.Seller;
import com.pinyougou.model.SellerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SellerMapper extends Mapper<Seller> {
    int countByExample(SellerExample example);

    int deleteByExample(SellerExample example);

    List<Seller> selectByExample(SellerExample example);

    int updateByExampleSelective(@Param("record") Seller record, @Param("example") SellerExample example);

    int updateByExample(@Param("record") Seller record, @Param("example") SellerExample example);
}