package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.GoodsDescMapper;
import com.pinyougou.model.GoodsDesc;
import com.pinyougou.sellergoods.service.GoodsDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class GoodsDescServiceImpl implements GoodsDescService {

    @Autowired
    private GoodsDescMapper goodsDescMapper;

	/**
	 * 返回GoodsDesc全部列表
	 * @return
	 */
	@Override
    public List<GoodsDesc> getAll(){
        return goodsDescMapper.selectAll();
    }


    /***
     * 分页返回GoodsDesc列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<GoodsDesc> getAll(GoodsDesc goodsDesc,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<GoodsDesc> all = goodsDescMapper.select(goodsDesc);
        PageInfo<GoodsDesc> pageInfo = new PageInfo<GoodsDesc>(all);
        return pageInfo;
    }



    /***
     * 增加GoodsDesc信息
     * @param goodsDesc
     * @return
     */
    @Override
    public int add(GoodsDesc goodsDesc) {
        return goodsDescMapper.insertSelective(goodsDesc);
    }


    /***
     * 根据ID查询GoodsDesc信息
     * @param id
     * @return
     */
    @Override
    public GoodsDesc getOneById(Long id) {
        return goodsDescMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改GoodsDesc信息
     * @param goodsDesc
     * @return
     */
    @Override
    public int updateGoodsDescById(GoodsDesc goodsDesc) {
        return goodsDescMapper.updateByPrimaryKeySelective(goodsDesc);
    }


    /***
     * 根据ID批量删除GoodsDesc信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(GoodsDesc.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_goodsDesc where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return goodsDescMapper.deleteByExample(example);
    }
}
