package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SeckillOrderMapper;
import com.pinyougou.model.SeckillOrder;
import com.pinyougou.sellergoods.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

	/**
	 * 返回SeckillOrder全部列表
	 * @return
	 */
	@Override
    public List<SeckillOrder> getAll(){
        return seckillOrderMapper.selectAll();
    }


    /***
     * 分页返回SeckillOrder列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<SeckillOrder> getAll(SeckillOrder seckillOrder,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<SeckillOrder> all = seckillOrderMapper.select(seckillOrder);
        PageInfo<SeckillOrder> pageInfo = new PageInfo<SeckillOrder>(all);
        return pageInfo;
    }



    /***
     * 增加SeckillOrder信息
     * @param seckillOrder
     * @return
     */
    @Override
    public int add(SeckillOrder seckillOrder) {
        return seckillOrderMapper.insertSelective(seckillOrder);
    }


    /***
     * 根据ID查询SeckillOrder信息
     * @param id
     * @return
     */
    @Override
    public SeckillOrder getOneById(Long id) {
        return seckillOrderMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改SeckillOrder信息
     * @param seckillOrder
     * @return
     */
    @Override
    public int updateSeckillOrderById(SeckillOrder seckillOrder) {
        return seckillOrderMapper.updateByPrimaryKeySelective(seckillOrder);
    }


    /***
     * 根据ID批量删除SeckillOrder信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(SeckillOrder.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_seckillOrder where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return seckillOrderMapper.deleteByExample(example);
    }
}
