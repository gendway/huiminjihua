package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Areas;
import java.util.List;

public interface AreasService {

	/**
	 * 返回Areas全部列表
	 * @return
	 */
	public List<Areas> getAll();

    /***
     * 分页返回Areas列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Areas> getAll(Areas areas, int pageNum, int pageSize);

    /***
     * 增加Areas信息
     * @param areas
     * @return
     */
    int add(Areas areas);

    /***
     * 根据ID查询Areas信息
     * @param id
     * @return
     */
    Areas getOneById(Long id);

    /***
     * 根据ID修改Areas信息
     * @param areas
     * @return
     */
    int updateAreasById(Areas areas);

    /***
     * 根据ID批量删除Areas信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
