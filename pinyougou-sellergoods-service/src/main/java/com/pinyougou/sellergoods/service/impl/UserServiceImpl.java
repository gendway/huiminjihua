package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.UserMapper;
import com.pinyougou.model.User;
import com.pinyougou.sellergoods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

	/**
	 * 返回User全部列表
	 * @return
	 */
	@Override
    public List<User> getAll(){
        return userMapper.selectAll();
    }


    /***
     * 分页返回User列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<User> getAll(User user,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<User> all = userMapper.select(user);
        PageInfo<User> pageInfo = new PageInfo<User>(all);
        return pageInfo;
    }



    /***
     * 增加User信息
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        return userMapper.insertSelective(user);
    }


    /***
     * 根据ID查询User信息
     * @param id
     * @return
     */
    @Override
    public User getOneById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改User信息
     * @param user
     * @return
     */
    @Override
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


    /***
     * 根据ID批量删除User信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_user where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return userMapper.deleteByExample(example);
    }
}
