package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Provinces;
import java.util.List;

public interface ProvincesService {

	/**
	 * 返回Provinces全部列表
	 * @return
	 */
	public List<Provinces> getAll();

    /***
     * 分页返回Provinces列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Provinces> getAll(Provinces provinces, int pageNum, int pageSize);

    /***
     * 增加Provinces信息
     * @param provinces
     * @return
     */
    int add(Provinces provinces);

    /***
     * 根据ID查询Provinces信息
     * @param id
     * @return
     */
    Provinces getOneById(Long id);

    /***
     * 根据ID修改Provinces信息
     * @param provinces
     * @return
     */
    int updateProvincesById(Provinces provinces);

    /***
     * 根据ID批量删除Provinces信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
