package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.model.Item;
import com.pinyougou.sellergoods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

	/**
	 * 返回Item全部列表
	 * @return
	 */
	@Override
    public List<Item> getAll(){
        return itemMapper.selectAll();
    }


    /***
     * 分页返回Item列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Item> getAll(Item item,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<Item> all = itemMapper.select(item);
        PageInfo<Item> pageInfo = new PageInfo<Item>(all);
        return pageInfo;
    }



    /***
     * 增加Item信息
     * @param item
     * @return
     */
    @Override
    public int add(Item item) {
        return itemMapper.insertSelective(item);
    }


    /***
     * 根据ID查询Item信息
     * @param id
     * @return
     */
    @Override
    public Item getOneById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Item信息
     * @param item
     * @return
     */
    @Override
    public int updateItemById(Item item) {
        return itemMapper.updateByPrimaryKeySelective(item);
    }


    /***
     * 根据ID批量删除Item信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Item.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_item where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return itemMapper.deleteByExample(example);
    }
}
