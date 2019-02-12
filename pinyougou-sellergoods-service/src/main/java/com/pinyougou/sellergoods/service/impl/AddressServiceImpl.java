package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.AddressMapper;
import com.pinyougou.model.Address;
import com.pinyougou.sellergoods.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

	/**
	 * 返回Address全部列表
	 * @return
	 */
	@Override
    public List<Address> getAll(){
        return addressMapper.selectAll();
    }


    /***
     * 分页返回Address列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Address> getAll(Address address,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Address> all = addressMapper.select(address);
        PageInfo<Address> pageInfo = new PageInfo<Address>(all);
        return pageInfo;
    }



    /***
     * 增加Address信息
     * @param address
     * @return
     */
    @Override
    public int add(Address address) {
        return addressMapper.insertSelective(address);
    }


    /***
     * 根据ID查询Address信息
     * @param id
     * @return
     */
    @Override
    public Address getOneById(Long id) {
        return addressMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Address信息
     * @param address
     * @return
     */
    @Override
    public int updateAddressById(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }


    /***
     * 根据ID批量删除Address信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_address where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return addressMapper.deleteByExample(example);
    }
}
