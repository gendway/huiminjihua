package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SellerMapper;
import com.pinyougou.model.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

	/**
	 * 返回Seller全部列表
	 * @return
	 */
	@Override
    public List<Seller> getAll(){
        return sellerMapper.selectAll();
    }


    /***
     * 分页返回Seller列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<Seller> getAll(Seller seller,int pageNum, int pageSize) {
        //执行分页
        PageHelper.startPage(pageNum,pageSize);
       
        //执行查询
        //List<Seller> all = sellerMapper.select(seller);


        Example example = new Example(Seller.class);
        Example.Criteria criteria = example.createCriteria();

        //模糊查询
        if(seller!=null){
            //公司名称
            if(StringUtils.isNotBlank(seller.getName())){
                criteria.andLike("name","%"+seller.getName()+"%");
            }
            //店铺名称
            if(StringUtils.isNotBlank(seller.getNickName())){
                criteria.andLike("nickName","%"+seller.getNickName()+"%");
            }

            //状态
            if(StringUtils.isNotBlank(seller.getStatus())){
                criteria.andEqualTo("status",seller.getStatus());
            }
        }

        //执行查询
        List<Seller> all = sellerMapper.selectByExample(example);

        PageInfo<Seller> pageInfo = new PageInfo<Seller>(all);
        return pageInfo;
    }



    /***
     * 增加Seller信息
     * @param seller
     * @return
     */
    @Override
    public int add(Seller seller) {
        return sellerMapper.insertSelective(seller);
    }


    /***
     * 根据ID查询Seller信息
     * @param id
     * @return
     */
    @Override
    public Seller getOneById(String id) {
        return sellerMapper.selectByPrimaryKey(id);
    }


    /***
     * 根据ID修改Seller信息
     * @param seller
     * @return
     */
    @Override
    public int updateSellerById(Seller seller) {
        return sellerMapper.updateByPrimaryKeySelective(seller);
    }


    /***
     * 根据ID批量删除Seller信息
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        //创建Example，来构建根据ID删除数据
        Example example = new Example(Seller.class);
        Example.Criteria criteria = example.createCriteria();

        //所需的SQL语句类似 delete from tb_seller where id in(1,2,5,6)
        criteria.andIn("id",ids);
        return sellerMapper.deleteByExample(example);
    }
}
