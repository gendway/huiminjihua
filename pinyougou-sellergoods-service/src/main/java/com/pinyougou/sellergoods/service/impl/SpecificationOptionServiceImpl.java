package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.model.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

	/**
	 * 返回SpecificationOption全部列表
	 * @return
	 */
	@Override
    public List<SpecificationOption> getAll(){
        return specificationOptionMapper.selectAll();
    }


    /***
     * 分页返回SpecificationOption列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<SpecificationOption> getAll(SpecificationOption specificationOption,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<SpecificationOption> all = specificationOptionMapper.select(specificationOption);
        PageInfo<SpecificationOption> pageInfo = new PageInfo<SpecificationOption>(all);
        return pageInfo;
    }



    /***
     * 增加SpecificationOption信息
     * @param specificationOption
     * @return
     */
    @Override
    public int add(SpecificationOption specificationOption) {
        return specificationOptionMapper.insertSelective(specificationOption);
    }


    /***
     * 根据ID查询SpecificationOption信息
     * @param id
     * @return
     */
    @Override
    public SpecificationOption getOneById(Long id) {
        return specificationOptionMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改SpecificationOption信息
     * @param specificationOption
     * @return
     */
    @Override
    public int updateSpecificationOptionById(SpecificationOption specificationOption) {
        return specificationOptionMapper.updateByPrimaryKeySelective(specificationOption);
    }


    /***
     * 根据ID批量删除SpecificationOption信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(SpecificationOption.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_specificationOption where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return specificationOptionMapper.deleteByExample(example);
    }
}
