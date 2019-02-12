package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Content;
import java.util.List;

public interface ContentService {

	/**
	 * 返回Content全部列表
	 * @return
	 */
	public List<Content> getAll();

    /***
     * 分页返回Content列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Content> getAll(Content content, int pageNum, int pageSize);

    /***
     * 增加Content信息
     * @param content
     * @return
     */
    int add(Content content);

    /***
     * 根据ID查询Content信息
     * @param id
     * @return
     */
    Content getOneById(Long id);

    /***
     * 根据ID修改Content信息
     * @param content
     * @return
     */
    int updateContentById(Content content);

    /***
     * 根据ID批量删除Content信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
