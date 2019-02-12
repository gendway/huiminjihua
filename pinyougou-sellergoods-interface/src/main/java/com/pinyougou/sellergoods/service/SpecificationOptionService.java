package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.SpecificationOption;
import java.util.List;

public interface SpecificationOptionService {

	/**
	 * 返回SpecificationOption全部列表
	 * @return
	 */
	public List<SpecificationOption> getAll();

    /***
     * 分页返回SpecificationOption列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SpecificationOption> getAll(SpecificationOption specificationOption, int pageNum, int pageSize);

    /***
     * 增加SpecificationOption信息
     * @param specificationOption
     * @return
     */
    int add(SpecificationOption specificationOption);

    /***
     * 根据ID查询SpecificationOption信息
     * @param id
     * @return
     */
    SpecificationOption getOneById(Long id);

    /***
     * 根据ID修改SpecificationOption信息
     * @param specificationOption
     * @return
     */
    int updateSpecificationOptionById(SpecificationOption specificationOption);

    /***
     * 根据ID批量删除SpecificationOption信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
