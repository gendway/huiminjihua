package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.SeckillGoods;
import java.util.List;

public interface SeckillGoodsService {

	/**
	 * 返回SeckillGoods全部列表
	 * @return
	 */
	public List<SeckillGoods> getAll();

    /***
     * 分页返回SeckillGoods列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SeckillGoods> getAll(SeckillGoods seckillGoods, int pageNum, int pageSize);

    /***
     * 增加SeckillGoods信息
     * @param seckillGoods
     * @return
     */
    int add(SeckillGoods seckillGoods);

    /***
     * 根据ID查询SeckillGoods信息
     * @param id
     * @return
     */
    SeckillGoods getOneById(Long id);

    /***
     * 根据ID修改SeckillGoods信息
     * @param seckillGoods
     * @return
     */
    int updateSeckillGoodsById(SeckillGoods seckillGoods);

    /***
     * 根据ID批量删除SeckillGoods信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
