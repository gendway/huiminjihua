package com.pinyougou.mapper;

import com.pinyougou.model.PayLog;
import com.pinyougou.model.PayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PayLogMapper extends Mapper<PayLog> {
    int countByExample(PayLogExample example);

    int deleteByExample(PayLogExample example);

    List<PayLog> selectByExample(PayLogExample example);

    int updateByExampleSelective(@Param("record") PayLog record, @Param("example") PayLogExample example);

    int updateByExample(@Param("record") PayLog record, @Param("example") PayLogExample example);
}