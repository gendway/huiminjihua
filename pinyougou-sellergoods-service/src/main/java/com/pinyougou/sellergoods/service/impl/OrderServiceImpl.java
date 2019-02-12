package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.OrderMapper;
import com.pinyougou.model.Order;
import com.pinyougou.sellergoods.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

	/**
	 * 返回Order全部列表
	 * @return
	 */
	@Override
    public List<Order> getAll(){
        return orderMapper.selectAll();
    }


    /***
     * 分页返回Order列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Order> getAll(Order order,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Order> all = orderMapper.select(order);
        PageInfo<Order> pageInfo = new PageInfo<Order>(all);
        return pageInfo;
    }



    /***
     * 增加Order信息
     * @param order
     * @return
     */
    @Override
    public int add(Order order) {
        return orderMapper.insertSelective(order);
    }


    /***
     * 根据ID查询Order信息
     * @param id
     * @return
     */
    @Override
    public Order getOneById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Order信息
     * @param order
     * @return
     */
    @Override
    public int updateOrderById(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    /***
     * 根据ID批量删除Order信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_order where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return orderMapper.deleteByExample(example);
    }
}
