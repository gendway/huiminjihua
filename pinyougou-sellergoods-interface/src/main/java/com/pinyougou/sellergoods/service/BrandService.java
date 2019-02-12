package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Brand;
import java.util.List;

public interface BrandService {

	/**
	 * 返回Brand全部列表
	 * @return
	 */
	public List<Brand> getAll();

    /***
     * 分页返回Brand列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Brand> getAll(Brand brand, int pageNum, int pageSize);

    /***
     * 增加Brand信息
     * @param brand
     * @return
     */
    int add(Brand brand);

    /***
     * 根据ID查询Brand信息
     * @param id
     * @return
     */
    Brand getOneById(Long id);

    /***
     * 根据ID修改Brand信息
     * @param brand
     * @return
     */
    int updateBrandById(Brand brand);

    /***
     * 根据ID批量删除Brand信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
