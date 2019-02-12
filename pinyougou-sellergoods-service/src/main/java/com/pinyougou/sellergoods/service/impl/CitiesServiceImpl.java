package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.CitiesMapper;
import com.pinyougou.model.Cities;
import com.pinyougou.sellergoods.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;

	/**
	 * 返回Cities全部列表
	 * @return
	 */
	@Override
    public List<Cities> getAll(){
        return citiesMapper.selectAll();
    }


    /***
     * 分页返回Cities列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Cities> getAll(Cities cities,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Cities> all = citiesMapper.select(cities);
        PageInfo<Cities> pageInfo = new PageInfo<Cities>(all);
        return pageInfo;
    }



    /***
     * 增加Cities信息
     * @param cities
     * @return
     */
    @Override
    public int add(Cities cities) {
        return citiesMapper.insertSelective(cities);
    }


    /***
     * 根据ID查询Cities信息
     * @param id
     * @return
     */
    @Override
    public Cities getOneById(Long id) {
        return citiesMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Cities信息
     * @param cities
     * @return
     */
    @Override
    public int updateCitiesById(Cities cities) {
        return citiesMapper.updateByPrimaryKeySelective(cities);
    }


    /***
     * 根据ID批量删除Cities信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Cities.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_cities where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return citiesMapper.deleteByExample(example);
    }
}
