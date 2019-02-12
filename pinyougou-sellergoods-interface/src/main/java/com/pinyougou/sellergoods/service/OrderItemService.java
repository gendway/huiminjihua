package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.OrderItem;
import java.util.List;

public interface OrderItemService {

	/**
	 * 返回OrderItem全部列表
	 * @return
	 */
	public List<OrderItem> getAll();

    /***
     * 分页返回OrderItem列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<OrderItem> getAll(OrderItem orderItem, int pageNum, int pageSize);

    /***
     * 增加OrderItem信息
     * @param orderItem
     * @return
     */
    int add(OrderItem orderItem);

    /***
     * 根据ID查询OrderItem信息
     * @param id
     * @return
     */
    OrderItem getOneById(Long id);

    /***
     * 根据ID修改OrderItem信息
     * @param orderItem
     * @return
     */
    int updateOrderItemById(OrderItem orderItem);

    /***
     * 根据ID批量删除OrderItem信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
