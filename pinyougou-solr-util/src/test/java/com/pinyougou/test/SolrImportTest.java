package com.pinyougou.test;

import com.pinyougou.solr.SolrUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/12 17:35
 *
 ****/
public class SolrImportTest {

    private SolrUtil solrUtil;

    @Before
    public void init(){
        //Spring容器
        ApplicationContext act = new ClassPathXmlApplicationContext("classpath:spring/spring-solr.xml");

        //获取SolrUtil的实例
        solrUtil = act.getBean(SolrUtil.class);
    }


    /***
     * 数据批量导入
     */
    @Test
    public void testAdd(){
        solrUtil.batchAdd();
    }


    @Test
    public void testDelete(){
        solrUtil.deleteAll();
    }

    /**
     * 查询机身内存：16G
     */
    @Test
    public void testGetBySpec(){
        solrUtil.queryByCondtion("机身内存","16G");
    }

}
