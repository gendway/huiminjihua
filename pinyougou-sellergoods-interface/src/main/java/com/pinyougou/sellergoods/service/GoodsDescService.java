package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.GoodsDesc;
import java.util.List;

public interface GoodsDescService {

	/**
	 * 返回GoodsDesc全部列表
	 * @return
	 */
	public List<GoodsDesc> getAll();

    /***
     * 分页返回GoodsDesc列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<GoodsDesc> getAll(GoodsDesc goodsDesc, int pageNum, int pageSize);

    /***
     * 增加GoodsDesc信息
     * @param goodsDesc
     * @return
     */
    int add(GoodsDesc goodsDesc);

    /***
     * 根据ID查询GoodsDesc信息
     * @param id
     * @return
     */
    GoodsDesc getOneById(Long id);

    /***
     * 根据ID修改GoodsDesc信息
     * @param goodsDesc
     * @return
     */
    int updateGoodsDescById(GoodsDesc goodsDesc);

    /***
     * 根据ID批量删除GoodsDesc信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
