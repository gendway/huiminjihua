package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Order;
import java.util.List;

public interface OrderService {

	/**
	 * 返回Order全部列表
	 * @return
	 */
	public List<Order> getAll();

    /***
     * 分页返回Order列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Order> getAll(Order order, int pageNum, int pageSize);

    /***
     * 增加Order信息
     * @param order
     * @return
     */
    int add(Order order);

    /***
     * 根据ID查询Order信息
     * @param id
     * @return
     */
    Order getOneById(Long id);

    /***
     * 根据ID修改Order信息
     * @param order
     * @return
     */
    int updateOrderById(Order order);

    /***
     * 根据ID批量删除Order信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
