package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Item;
import java.util.List;

public interface ItemService {

	/**
	 * 返回Item全部列表
	 * @return
	 */
	public List<Item> getAll();

    /***
     * 分页返回Item列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Item> getAll(Item item, int pageNum, int pageSize);

    /***
     * 增加Item信息
     * @param item
     * @return
     */
    int add(Item item);

    /***
     * 根据ID查询Item信息
     * @param id
     * @return
     */
    Item getOneById(Long id);

    /***
     * 根据ID修改Item信息
     * @param item
     * @return
     */
    int updateItemById(Item item);

    /***
     * 根据ID批量删除Item信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
