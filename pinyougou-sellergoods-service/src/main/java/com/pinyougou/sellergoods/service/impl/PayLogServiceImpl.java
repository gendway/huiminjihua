package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.PayLogMapper;
import com.pinyougou.model.PayLog;
import com.pinyougou.sellergoods.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class PayLogServiceImpl implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

	/**
	 * 返回PayLog全部列表
	 * @return
	 */
	@Override
    public List<PayLog> getAll(){
        return payLogMapper.selectAll();
    }


    /***
     * 分页返回PayLog列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<PayLog> getAll(PayLog payLog,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<PayLog> all = payLogMapper.select(payLog);
        PageInfo<PayLog> pageInfo = new PageInfo<PayLog>(all);
        return pageInfo;
    }



    /***
     * 增加PayLog信息
     * @param payLog
     * @return
     */
    @Override
    public int add(PayLog payLog) {
        return payLogMapper.insertSelective(payLog);
    }


    /***
     * 根据ID查询PayLog信息
     * @param id
     * @return
     */
    @Override
    public PayLog getOneById(Long id) {
        return payLogMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改PayLog信息
     * @param payLog
     * @return
     */
    @Override
    public int updatePayLogById(PayLog payLog) {
        return payLogMapper.updateByPrimaryKeySelective(payLog);
    }


    /***
     * 根据ID批量删除PayLog信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(PayLog.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_payLog where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return payLogMapper.deleteByExample(example);
    }
}
