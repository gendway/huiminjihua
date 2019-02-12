package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.ItemCat;
import java.util.List;

public interface ItemCatService {

	/**
	 * 返回ItemCat全部列表
	 * @return
	 */
	public List<ItemCat> getAll();

    /***
     * 分页返回ItemCat列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ItemCat> getAll(ItemCat itemCat, int pageNum, int pageSize);

    /***
     * 增加ItemCat信息
     * @param itemCat
     * @return
     */
    int add(ItemCat itemCat);

    /***
     * 根据ID查询ItemCat信息
     * @param id
     * @return
     */
    ItemCat getOneById(Long id);

    /***
     * 根据ID修改ItemCat信息
     * @param itemCat
     * @return
     */
    int updateItemCatById(ItemCat itemCat);

    /***
     * 根据ID批量删除ItemCat信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    /***
     * 根据父ID查询所有子分类
     * @param id
     * @return
     */
    List<ItemCat> findByParentId(long id);
}
