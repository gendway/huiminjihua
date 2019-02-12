package com.pinyougou.solr;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/12 17:30
 *
 ****/
@Component
public class SolrUtil {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;


    /***
     * 商品数据批量导入索引库
     */
    public void batchAdd(){
        //select * from tb_item where status=1
        //查询数据,商品状态为上架状态
        Item item = new Item();
        item.setStatus("1");
        List<Item> items = itemMapper.select(item);

        //循环，将JSON字符转Map
        for (Item itemMap : items) {
            //规格字符串值
            String specString = itemMap.getSpec();

            //将规格字符串值转成Map
            Map<String,String> dataMap = JSON.parseObject(specString, Map.class);
            itemMap.setSpecMap(dataMap);
        }

        //将数据导入到索引库中
        solrTemplate.saveBeans(items);

        //提交
        solrTemplate.commit();
    }


    /***
     * 动态域搜索
     */
    public void queryByCondtion(String fieldName,String keywords){
        //创建Query指定查询条件
        Query query = new SimpleQuery();

        //增加条件
        Criteria criteria = new Criteria("item_spec_"+fieldName).is(keywords);

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


    public void deleteAll() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
