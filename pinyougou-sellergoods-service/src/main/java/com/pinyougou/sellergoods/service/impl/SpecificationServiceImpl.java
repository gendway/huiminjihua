package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.model.Specification;
import com.pinyougou.model.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

	/**
	 * 返回Specification全部列表
	 * @return
	 */
	@Override
    public List<Specification> getAll(){
        return specificationMapper.selectAll();
    }


    /***
     * 分页返回Specification列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Specification> getAll(Specification specification,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Specification> all = specificationMapper.select(specification);
        PageInfo<Specification> pageInfo = new PageInfo<Specification>(all);
        return pageInfo;
    }

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;


    /***
     * 增加Specification信息
     * @param specification
     * @return
     */
    @Override
    public int add(Specification specification) {
        //将规格信息增加到规格表
        int acount = specificationMapper.insertSelective(specification);

        //获取规格信息的主键c_id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)

        //将规格选项增加到规格选项表
        List<SpecificationOption> specificationOptionList = specification.getSpecificationOptionList();

        //循环增加
        for (SpecificationOption specificationOption : specificationOptionList) {
            //并将主键赋值给specificationOptionList的spe
            specificationOption.setSpecId(specification.getId());

            //增加入库
            specificationOptionMapper.insertSelective(specificationOption);
        }
        return acount;
    }


    /***
     * 根据ID查询Specification信息
     * @param id
     * @return
     */
    @Override
    public Specification getOneById(Long id) {
        //规格信息
        Specification specification = specificationMapper.selectByPrimaryKey(id);

        //查询规格属性  select * from tb_specification_option where spec_id=?
        //JavaBean中哪些属性不为空，则会把它作为查询条件
        SpecificationOption specificationOption = new SpecificationOption();
        specificationOption.setSpecId(id);
        List<SpecificationOption> specificationOptions = specificationOptionMapper.select(specificationOption);
        specification.setSpecificationOptionList(specificationOptions);

        return specification;
    }


    /***
     * 根据ID修改Specification信息
     * @param specification
     * @return
     */
    @Override
    public int updateSpecificationById(Specification specification) {
        //规格信息修改
        int mcount = specificationMapper.updateByPrimaryKeySelective(specification);

        //删除之前的规格属性   delete from tb_specification_option where spec_id=?
        SpecificationOption specificationOption = new SpecificationOption();
        specificationOption.setSpecId(specification.getId());
        specificationOptionMapper.delete(specificationOption);

        //添加新的规格属性
        List<SpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        for (SpecificationOption option : specificationOptionList) {
            //设置外键关联ID
            option.setSpecId(specification.getId());

            //增加
            specificationOptionMapper.insertSelective(option);
        }

        return mcount;
    }


    /***
     * 根据ID批量删除Specification信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Specification.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_specification where id in(1,2,5,6)
        criteria.andIn("id",ids);

        //删除规格信息
        int dcount = specificationMapper.deleteByExample(example);

        //批量删除规格选项  delete from tb_specification_option where spec_id in(xxx,sadas,sadas,d)
        Example example1 = new Example(SpecificationOption.class);
        Example.Criteria criteria1 = example1.createCriteria();

        //所需的SQL语句类似 delete from tb_specification where specId in(1,2,5,6)
        criteria1.andIn("specId",ids);

        //删除规格选项
        dcount+=specificationOptionMapper.deleteByExample(example1);

        return dcount;
    }

    @Override
    public List<Map<String, Object>> selectOptionList() {
        return specificationMapper.selectOptionList();
    }
}
