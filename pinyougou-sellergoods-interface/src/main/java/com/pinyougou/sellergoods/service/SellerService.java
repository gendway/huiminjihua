package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Seller;
import java.util.List;

public interface SellerService {

	/**
	 * 返回Seller全部列表
	 * @return
	 */
	public List<Seller> getAll();

    /***
     * 分页返回Seller列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Seller> getAll(Seller seller, int pageNum, int pageSize);

    /***
     * 增加Seller信息
     * @param seller
     * @return
     */
    int add(Seller seller);

    /***
     * 根据ID查询Seller信息
     * @param id
     * @return
     */
    Seller getOneById(String id);

    /***
     * 根据ID修改Seller信息
     * @param seller
     * @return
     */
    int updateSellerById(Seller seller);

    /***
     * 根据ID批量删除Seller信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
