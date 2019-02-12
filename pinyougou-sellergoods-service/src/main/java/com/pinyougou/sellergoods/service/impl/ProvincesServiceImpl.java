package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.ProvincesMapper;
import com.pinyougou.model.Provinces;
import com.pinyougou.sellergoods.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class ProvincesServiceImpl implements ProvincesService {

    @Autowired
    private ProvincesMapper provincesMapper;

	/**
	 * 返回Provinces全部列表
	 * @return
	 */
	@Override
    public List<Provinces> getAll(){
        return provincesMapper.selectAll();
    }


    /***
     * 分页返回Provinces列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Provinces> getAll(Provinces provinces,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Provinces> all = provincesMapper.select(provinces);
        PageInfo<Provinces> pageInfo = new PageInfo<Provinces>(all);
        return pageInfo;
    }



    /***
     * 增加Provinces信息
     * @param provinces
     * @return
     */
    @Override
    public int add(Provinces provinces) {
        return provincesMapper.insertSelective(provinces);
    }


    /***
     * 根据ID查询Provinces信息
     * @param id
     * @return
     */
    @Override
    public Provinces getOneById(Long id) {
        return provincesMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Provinces信息
     * @param provinces
     * @return
     */
    @Override
    public int updateProvincesById(Provinces provinces) {
        return provincesMapper.updateByPrimaryKeySelective(provinces);
    }


    /***
     * 根据ID批量删除Provinces信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Provinces.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_provinces where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return provincesMapper.deleteByExample(example);
    }
}
