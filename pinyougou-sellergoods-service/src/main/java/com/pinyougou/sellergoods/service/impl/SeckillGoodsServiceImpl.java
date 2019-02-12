package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SeckillGoodsMapper;
import com.pinyougou.model.SeckillGoods;
import com.pinyougou.sellergoods.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

	/**
	 * 返回SeckillGoods全部列表
	 * @return
	 */
	@Override
    public List<SeckillGoods> getAll(){
        return seckillGoodsMapper.selectAll();
    }


    /***
     * 分页返回SeckillGoods列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<SeckillGoods> getAll(SeckillGoods seckillGoods,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<SeckillGoods> all = seckillGoodsMapper.select(seckillGoods);
        PageInfo<SeckillGoods> pageInfo = new PageInfo<SeckillGoods>(all);
        return pageInfo;
    }



    /***
     * 增加SeckillGoods信息
     * @param seckillGoods
     * @return
     */
    @Override
    public int add(SeckillGoods seckillGoods) {
        return seckillGoodsMapper.insertSelective(seckillGoods);
    }


    /***
     * 根据ID查询SeckillGoods信息
     * @param id
     * @return
     */
    @Override
    public SeckillGoods getOneById(Long id) {
        return seckillGoodsMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改SeckillGoods信息
     * @param seckillGoods
     * @return
     */
    @Override
    public int updateSeckillGoodsById(SeckillGoods seckillGoods) {
        return seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
    }


    /***
     * 根据ID批量删除SeckillGoods信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(SeckillGoods.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_seckillGoods where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return seckillGoodsMapper.deleteByExample(example);
    }
}
