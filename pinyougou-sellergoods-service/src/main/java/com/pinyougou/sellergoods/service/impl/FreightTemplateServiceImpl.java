package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.FreightTemplateMapper;
import com.pinyougou.model.FreightTemplate;
import com.pinyougou.sellergoods.service.FreightTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

    @Autowired
    private FreightTemplateMapper freightTemplateMapper;

	/**
	 * 返回FreightTemplate全部列表
	 * @return
	 */
	@Override
    public List<FreightTemplate> getAll(){
        return freightTemplateMapper.selectAll();
    }


    /***
     * 分页返回FreightTemplate列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<FreightTemplate> getAll(FreightTemplate freightTemplate,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<FreightTemplate> all = freightTemplateMapper.select(freightTemplate);
        PageInfo<FreightTemplate> pageInfo = new PageInfo<FreightTemplate>(all);
        return pageInfo;
    }



    /***
     * 增加FreightTemplate信息
     * @param freightTemplate
     * @return
     */
    @Override
    public int add(FreightTemplate freightTemplate) {
        return freightTemplateMapper.insertSelective(freightTemplate);
    }


    /***
     * 根据ID查询FreightTemplate信息
     * @param id
     * @return
     */
    @Override
    public FreightTemplate getOneById(Long id) {
        return freightTemplateMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改FreightTemplate信息
     * @param freightTemplate
     * @return
     */
    @Override
    public int updateFreightTemplateById(FreightTemplate freightTemplate) {
        return freightTemplateMapper.updateByPrimaryKeySelective(freightTemplate);
    }


    /***
     * 根据ID批量删除FreightTemplate信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(FreightTemplate.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_freightTemplate where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return freightTemplateMapper.deleteByExample(example);
    }
}
