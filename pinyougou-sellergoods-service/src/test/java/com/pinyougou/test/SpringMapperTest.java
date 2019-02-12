package com.pinyougou.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.model.Brand;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/********
 * author:shenkunlin
 * date:2018/9/2 15:58
 * description:深圳黑马
 * version:1.0
 ******/
public class SpringMapperTest {

    private BrandMapper brandMapper;

    @Before
    public void init(){
        //初始化Spring容器
        ApplicationContext act = new ClassPathXmlApplicationContext("spring/spring.xml");

        //从容器中获取BrandMapper 的实例
        brandMapper = act.getBean(BrandMapper.class);
    }

    /***
     * 不忽略空值
     * 增加测试
     */
    @Test
    public void testInsert(){
        //原始数据
        Brand brand = new Brand();
        brand.setName("小红666");

        //调用增加方法
        brandMapper.insert(brand);
    }


    /***
     * 忽略空值
     * 增加测试
     */
    @Test
    public void testInsertSelective(){
        //原始数据
        Brand brand = new Brand();
        brand.setName("小黑666");

        //调用增加方法
        brandMapper.insertSelective(brand);
    }

    /***
     * 根据主键删除操作
     */
    @Test
    public void testDeleteByPrimaryKey(){
        Long id=92L;
        brandMapper.deleteByPrimaryKey(id);
    }

    /***
     * 根据实体Bean的数据参数
     *  brand只设置了name           delete from tb_brand where name=?
     *  brand设置了name和firstChar  delete from tb_brand where name=? and firstChar=?
     */
    @Test
    public void testDelete(){
        Brand brand = new Brand();
        brand.setName("小红666");
        brandMapper.delete(brand);
    }

    /***
     * 动态构建删除条件
     */
    @Test
    public void testDeleteByExample(){
        //delete from tb_brand where id in(92,68,67)
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //组装条件
        List<Long> ids = new ArrayList<Long>();
        ids.add(92L);
        ids.add(68L);
        ids.add(67L);
        criteria.andIn("id",ids);

        brandMapper.deleteByExample(example);
    }


    /****
     * 动态偶构建条件修改
     */
    @Test
    public void testUpdateByExample(){
        Brand brand = new Brand();
        brand.setName("小红果");

        //Example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //修改firstchar名字为H   update tb_bran set name=xx where first_char=?
        criteria.andEqualTo("firstChar","H");

        brandMapper.updateByExample(brand,example);
        //brandMapper.updateByExampleSelective(brand,example);
        //brandMapper.updateByPrimaryKey();
        //brandMapper.updateByPrimaryKeySelective();
    }

    /***
     * 查询
     */
    @Test
    public void testSelectAll(){
        //分页
        PageHelper.startPage(2,5);

        List<Brand> brands = brandMapper.selectAll();
        for (Brand brand : brands) {
            System.out.println(brand);
        }

        //分页信息
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brands);
        System.out.println(pageInfo);
    }


    /***
     * 查询多条数据
     */
    @Test
    public void testSelect(){
        Brand brand = new Brand();
        brand.setName("TCLAND长虹");
        brand.setFirstChar("T");
        List<Brand> brands = brandMapper.select(brand);

        for (Brand brand1 : brands) {
            System.out.println(brand1);
        }
    }


    /***
     * 查询多条数据
     */
    @Test
    public void testSelectOne(){
        Brand brand = new Brand();
        brand.setId(73L);
        Brand brandinfo = brandMapper.selectOne(brand);

        System.out.println(brandinfo);
    }

}
