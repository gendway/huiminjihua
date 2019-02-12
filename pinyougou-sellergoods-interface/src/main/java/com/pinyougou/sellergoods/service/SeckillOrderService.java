package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.SeckillOrder;
import java.util.List;

public interface SeckillOrderService {

	/**
	 * 返回SeckillOrder全部列表
	 * @return
	 */
	public List<SeckillOrder> getAll();

    /***
     * 分页返回SeckillOrder列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SeckillOrder> getAll(SeckillOrder seckillOrder, int pageNum, int pageSize);

    /***
     * 增加SeckillOrder信息
     * @param seckillOrder
     * @return
     */
    int add(SeckillOrder seckillOrder);

    /***
     * 根据ID查询SeckillOrder信息
     * @param id
     * @return
     */
    SeckillOrder getOneById(Long id);

    /***
     * 根据ID修改SeckillOrder信息
     * @param seckillOrder
     * @return
     */
    int updateSeckillOrderById(SeckillOrder seckillOrder);

    /***
     * 根据ID批量删除SeckillOrder信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
