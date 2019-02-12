package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.PayLog;
import java.util.List;

public interface PayLogService {

	/**
	 * 返回PayLog全部列表
	 * @return
	 */
	public List<PayLog> getAll();

    /***
     * 分页返回PayLog列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<PayLog> getAll(PayLog payLog, int pageNum, int pageSize);

    /***
     * 增加PayLog信息
     * @param payLog
     * @return
     */
    int add(PayLog payLog);

    /***
     * 根据ID查询PayLog信息
     * @param id
     * @return
     */
    PayLog getOneById(Long id);

    /***
     * 根据ID修改PayLog信息
     * @param payLog
     * @return
     */
    int updatePayLogById(PayLog payLog);

    /***
     * 根据ID批量删除PayLog信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
