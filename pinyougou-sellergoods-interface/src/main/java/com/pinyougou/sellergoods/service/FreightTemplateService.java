package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.FreightTemplate;
import java.util.List;

public interface FreightTemplateService {

	/**
	 * 返回FreightTemplate全部列表
	 * @return
	 */
	public List<FreightTemplate> getAll();

    /***
     * 分页返回FreightTemplate列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<FreightTemplate> getAll(FreightTemplate freightTemplate, int pageNum, int pageSize);

    /***
     * 增加FreightTemplate信息
     * @param freightTemplate
     * @return
     */
    int add(FreightTemplate freightTemplate);

    /***
     * 根据ID查询FreightTemplate信息
     * @param id
     * @return
     */
    FreightTemplate getOneById(Long id);

    /***
     * 根据ID修改FreightTemplate信息
     * @param freightTemplate
     * @return
     */
    int updateFreightTemplateById(FreightTemplate freightTemplate);

    /***
     * 根据ID批量删除FreightTemplate信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
