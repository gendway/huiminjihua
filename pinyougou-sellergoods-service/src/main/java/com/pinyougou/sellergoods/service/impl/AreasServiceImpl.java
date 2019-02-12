package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.AreasMapper;
import com.pinyougou.model.Areas;
import com.pinyougou.sellergoods.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasMapper areasMapper;

	/**
	 * 返回Areas全部列表
	 * @return
	 */
	@Override
    public List<Areas> getAll(){
        return areasMapper.selectAll();
    }


    /***
     * 分页返回Areas列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Areas> getAll(Areas areas,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Areas> all = areasMapper.select(areas);
        PageInfo<Areas> pageInfo = new PageInfo<Areas>(all);
        return pageInfo;
    }



    /***
     * 增加Areas信息
     * @param areas
     * @return
     */
    @Override
    public int add(Areas areas) {
        return areasMapper.insertSelective(areas);
    }


    /***
     * 根据ID查询Areas信息
     * @param id
     * @return
     */
    @Override
    public Areas getOneById(Long id) {
        return areasMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Areas信息
     * @param areas
     * @return
     */
    @Override
    public int updateAreasById(Areas areas) {
        return areasMapper.updateByPrimaryKeySelective(areas);
    }


    /***
     * 根据ID批量删除Areas信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_areas where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return areasMapper.deleteByExample(example);
    }
}
