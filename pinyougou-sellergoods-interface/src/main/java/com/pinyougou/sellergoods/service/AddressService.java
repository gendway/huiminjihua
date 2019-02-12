package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Address;
import java.util.List;

public interface AddressService {

	/**
	 * 返回Address全部列表
	 * @return
	 */
	public List<Address> getAll();

    /***
     * 分页返回Address列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Address> getAll(Address address, int pageNum, int pageSize);

    /***
     * 增加Address信息
     * @param address
     * @return
     */
    int add(Address address);

    /***
     * 根据ID查询Address信息
     * @param id
     * @return
     */
    Address getOneById(Long id);

    /***
     * 根据ID修改Address信息
     * @param address
     * @return
     */
    int updateAddressById(Address address);

    /***
     * 根据ID批量删除Address信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
