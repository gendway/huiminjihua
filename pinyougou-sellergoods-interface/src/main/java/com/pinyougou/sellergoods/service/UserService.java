package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.User;
import java.util.List;

public interface UserService {

	/**
	 * 返回User全部列表
	 * @return
	 */
	public List<User> getAll();

    /***
     * 分页返回User列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> getAll(User user, int pageNum, int pageSize);

    /***
     * 增加User信息
     * @param user
     * @return
     */
    int add(User user);

    /***
     * 根据ID查询User信息
     * @param id
     * @return
     */
    User getOneById(Long id);

    /***
     * 根据ID修改User信息
     * @param user
     * @return
     */
    int updateUserById(User user);

    /***
     * 根据ID批量删除User信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
