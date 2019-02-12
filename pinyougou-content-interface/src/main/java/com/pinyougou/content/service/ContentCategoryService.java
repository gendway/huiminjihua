package com.pinyougou.content.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.ContentCategory;
import java.util.List;

public interface ContentCategoryService {

	/**
	 * 返回ContentCategory全部列表
	 * @return
	 */
	public List<ContentCategory> getAll();

    /***
     * 分页返回ContentCategory列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ContentCategory> getAll(ContentCategory contentCategory, int pageNum, int pageSize);

    /***
     * 增加ContentCategory信息
     * @param contentCategory
     * @return
     */
    int add(ContentCategory contentCategory);

    /***
     * 根据ID查询ContentCategory信息
     * @param id
     * @return
     */
    ContentCategory getOneById(Long id);

    /***
     * 根据ID修改ContentCategory信息
     * @param contentCategory
     * @return
     */
    int updateContentCategoryById(ContentCategory contentCategory);

    /***
     * 根据ID批量删除ContentCategory信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
