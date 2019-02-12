package com.itheima.test;

import com.itheima.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/12 16:57
 *
 ****/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-solr.xml")
public class SolrTest {

    /****
     * 执行索引库的增删改查
     */
    @Autowired
    private SolrTemplate solrTemplate;



    /***
     * 条件查询，同时分页
     */
    @Test
    public void testQueryByCondtion(){
        //创建Query指定查询条件
        Query query = new SimpleQuery();

        //增加条件
        Criteria criteria = new Criteria("item_title").is("华为93");
        //Criteria criteria = new Criteria("item_title").contains("华为93");

        //将条件对象给Query对象
        query.addCriteria(criteria);

        //指定分页参数
        query.setOffset(0);
        query.setRows(5);

        //执行分页搜索
        //Query：搜索条件的封装
        //Item.class:查询的数据结果集需要转换成Item
        ScoredPage<Item> scoredPage = solrTemplate.queryForPage(query, Item.class);

        //获取结果集
        List<Item> items = scoredPage.getContent();

        //总记录数
        long totalElements = scoredPage.getTotalElements();


        System.out.println("总记录数有："+totalElements);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /***
     * 条件查询，同时分页
     */
    @Test
    public void testQueryByCondtionForPage(){
        //创建Query指定查询条件
        Query query = new SimpleQuery("item_title:华为");

        //指定分页参数
        query.setOffset(5);
        query.setRows(5);

        //执行分页搜索
        //Query：搜索条件的封装
        //Item.class:查询的数据结果集需要转换成Item
        ScoredPage<Item> scoredPage = solrTemplate.queryForPage(query, Item.class);

        //获取结果集
        List<Item> items = scoredPage.getContent();

        //总记录数
        long totalElements = scoredPage.getTotalElements();


        System.out.println("总记录数有："+totalElements);
        for (Item item : items) {
            System.out.println(item);
        }
    }


    /***
     * 分页搜索
     */
    @Test
    public void testGetPage(){
        //创建Query指定查询条件
        Query query = new SimpleQuery("*:*");

        //指定分页参数
        query.setOffset(0);
        query.setRows(5);

        //执行分页搜索
        //Query：搜索条件的封装
        //Item.class:查询的数据结果集需要转换成Item
        ScoredPage<Item> scoredPage = solrTemplate.queryForPage(query, Item.class);

        //获取结果集
        List<Item> items = scoredPage.getContent();

        //总记录数
        long totalElements = scoredPage.getTotalElements();


        System.out.println("总记录数有："+totalElements);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /***
     * 删除所有
     *
     */
    @Test
    public void testDeleteAll(){
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

    /****
     * 根据ID删除
     */
    @Test
    public void testDeleteById(){
        solrTemplate.deleteById("1115");
        solrTemplate.commit();
    }



    /***
     * 增加数据测试
     */
    @Test
    public void testAdd(){
        Item item = new Item();
        item.setId(202L);
        item.setBrand("xiuaomi");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("小米手机666");
        item.setPrice(new BigDecimal(11));

        //实现增加操作
        solrTemplate.saveBean(item);

        //提交事务
        solrTemplate.commit();
    }



    /***
     * 增加数据测试
     */
    @Test
    public void testBatchAdd(){
        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i <10 ; i++) {
            Item item = new Item();
            item.setId(1L+(int)(Math.random()*100));
            item.setBrand("华为"+(int)(Math.random()*100));
            item.setCategory("手机"+(int)(Math.random()*100));
            item.setGoodsId(1L);
            item.setSeller("华为2号专卖店"+(int)(Math.random()*100));
            item.setTitle("华为Mate9"+(int)(Math.random()*100));
            item.setPrice(new BigDecimal(2+(int)(Math.random()*100)));

            items.add(item);
        }


        //实现增加操作
        solrTemplate.saveBeans(items);

        //提交事务
        solrTemplate.commit();
    }

}
