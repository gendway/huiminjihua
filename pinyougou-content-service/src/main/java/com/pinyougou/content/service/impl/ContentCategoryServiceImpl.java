package com.pinyougou.content.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.ContentCategoryMapper;
import com.pinyougou.model.ContentCategory;
import com.pinyougou.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

	/**
	 * 返回ContentCategory全部列表
	 * @return
	 */
	@Override
    public List<ContentCategory> getAll(){
        return contentCategoryMapper.selectAll();
    }


    /***
     * 分页返回ContentCategory列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<ContentCategory> getAll(ContentCategory contentCategory,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        List<ContentCategory> all = contentCategoryMapper.select(contentCategory);
        PageInfo<ContentCategory> pageInfo = new PageInfo<ContentCategory>(all);
        return pageInfo;
    }



    /***
     * 增加ContentCategory信息
     * @param contentCategory
     * @return
     */
    @Override
    public int add(ContentCategory contentCategory) {
        return contentCategoryMapper.insertSelective(contentCategory);
    }


    /***
     * 根据ID查询ContentCategory信息
     * @param id
     * @return
     */
    @Override
    public ContentCategory getOneById(Long id) {
        return contentCategoryMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改ContentCategory信息
     * @param contentCategory
     * @return
     */
    @Override
    public int updateContentCategoryById(ContentCategory contentCategory) {
        return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }


    /***
     * 根据ID批量删除ContentCategory信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(ContentCategory.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_contentCategory where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return contentCategoryMapper.deleteByExample(example);
    }
}
