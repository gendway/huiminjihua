package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Specification;
import java.util.List;
import java.util.Map;

public interface SpecificationService {

	/**
	 * 返回Specification全部列表
	 * @return
	 */
	public List<Specification> getAll();

    /***
     * 分页返回Specification列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Specification> getAll(Specification specification, int pageNum, int pageSize);

    /***
     * 增加Specification信息
     * @param specification
     * @return
     */
    int add(Specification specification);

    /***
     * 根据ID查询Specification信息
     * @param id
     * @return
     */
    Specification getOneById(Long id);

    /***
     * 根据ID修改Specification信息
     * @param specification
     * @return
     */
    int updateSpecificationById(Specification specification);

    /***
     * 根据ID批量删除Specification信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    List<Map<String,Object>> selectOptionList();
}
