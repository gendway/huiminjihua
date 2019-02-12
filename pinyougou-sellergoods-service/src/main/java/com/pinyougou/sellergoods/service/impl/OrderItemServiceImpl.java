package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.OrderItemMapper;
import com.pinyougou.model.OrderItem;
import com.pinyougou.sellergoods.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

	/**
	 * 返回OrderItem全部列表
	 * @return
	 */
	@Override
    public List<OrderItem> getAll(){
        return orderItemMapper.selectAll();
    }


    /***
     * 分页返回OrderItem列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<OrderItem> getAll(OrderItem orderItem,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<OrderItem> all = orderItemMapper.select(orderItem);
        PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(all);
        return pageInfo;
    }



    /***
     * 增加OrderItem信息
     * @param orderItem
     * @return
     */
    @Override
    public int add(OrderItem orderItem) {
        return orderItemMapper.insertSelective(orderItem);
    }


    /***
     * 根据ID查询OrderItem信息
     * @param id
     * @return
     */
    @Override
    public OrderItem getOneById(Long id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改OrderItem信息
     * @param orderItem
     * @return
     */
    @Override
    public int updateOrderItemById(OrderItem orderItem) {
        return orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }


    /***
     * 根据ID批量删除OrderItem信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_orderItem where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return orderItemMapper.deleteByExample(example);
    }
}
