package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Cities;
import java.util.List;

public interface CitiesService {

	/**
	 * 返回Cities全部列表
	 * @return
	 */
	public List<Cities> getAll();

    /***
     * 分页返回Cities列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Cities> getAll(Cities cities, int pageNum, int pageSize);

    /***
     * 增加Cities信息
     * @param cities
     * @return
     */
    int add(Cities cities);

    /***
     * 根据ID查询Cities信息
     * @param id
     * @return
     */
    Cities getOneById(Long id);

    /***
     * 根据ID修改Cities信息
     * @param cities
     * @return
     */
    int updateCitiesById(Cities cities);

    /***
     * 根据ID批量删除Cities信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
