package com.pinyougou.mapper;

import com.pinyougou.model.OrderItem;
import com.pinyougou.model.OrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OrderItemMapper extends Mapper<OrderItem> {
    int countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    List<OrderItem> selectByExample(OrderItemExample example);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);
}