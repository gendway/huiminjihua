package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.model.Item;
import com.pinyougou.search.service.ItemSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/12 18:12
 *
 ****/
@Service
public class ItemSearchServiceImpl implements ItemSearchService {


    @Autowired
    private SolrTemplate solrTemplate;


    /****
     * 高亮数据查询显示
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //条件搜索  Query
        //Query query = new SimpleQuery("*:*");
        HighlightQuery query = new SimpleHighlightQuery(new SimpleStringCriteria("*:*"));

        //高亮配置
        highlightSettings(query);


        //如果searchMap为空，也就是没有传入任何条件，则搜索所有数据
        //如果不为空，则搜索对应关键词的数据
        if(searchMap!=null){
            //关键词的key是keyword
            String keywrod = (String) searchMap.get("keyword");

            //根据关键词查询
            if(StringUtils.isNotBlank(keywrod)){
                //第1个参数：要查询的域
                Criteria criteria = new Criteria("item_keywords").is(keywrod.replace(" ",""));
                query.addCriteria(criteria);
            }

            //分类过滤
            String category = (String) searchMap.get("category");
            if(StringUtils.isNotBlank(category)){
                //创建Criteria对象，用于填充对应的搜索条件
                Criteria criteria = new Criteria("item_category").is(category);

                //搜索过滤对象
                FilterQuery filterQuery = new SimpleFilterQuery();
                filterQuery.addCriteria(criteria);

                //将搜索过滤对象加入到Query
                query.addFilterQuery(filterQuery);
            }

            //品牌过滤
            String brand = (String) searchMap.get("brand");
            if(StringUtils.isNotBlank(brand)){
                //创建Criteria对象
                Criteria criteria = new Criteria("item_brand").is(brand);

                //搜索过滤对象
                FilterQuery filterQuery = new SimpleFilterQuery(criteria);

                //搜索过滤对象加入到Query中
                query.addFilterQuery(filterQuery);
            }


            //接收规格数据
            Object spec = searchMap.get("spec");
            if(spec!=null){
                //$scope.searchMap={"keyword":"","category":"","brand":"",
                // spec:{"网络制式":"联通3G","机身内存":"16G","尺码":"175寸"};
                //过滤搜索规格数据
                Map<String,String> specMap = JSON.parseObject(spec.toString(),Map.class);

                //循环迭代搜索
                for (Map.Entry<String, String> entry : specMap.entrySet()) {
                    //获取key=网络制式
                    String key = entry.getKey();
                    //要搜索的数据 value = 联通3G
                    String value = entry.getValue();

                    //创建Criteria     key = 网络制式
                    Criteria criteria = new Criteria("item_spec_"+key).is(value);
                    //创建FilterQuery
                    FilterQuery filterQuery = new SimpleFilterQuery(criteria);
                    //添加到Query中
                    query.addFilterQuery(filterQuery);
                }
            }

            //价格区间搜索
            String price = (String) searchMap.get("price");

            /***
             * 1)价格区间中间的连接符有可能是-     x=<price <y   2个价格区间都需要
             * 2)价格区间中间的连接符有可能是空格  x=<price 只需要第1个价格区间
             */
            if(StringUtils.isNotBlank(price)){
                //1)价格区间中间的连接符有可能是-     x=<price <y   2个价格区间都需要
                //                                   price between x and y
                String[] ranges = price.split("-");
                if(ranges!=null && ranges.length==2){
                    //创建Criteria,用于封装匹配条件
                    Criteria criteria = new Criteria("item_price")
                            .between(Long.parseLong(ranges[0]),     //最小区间值
                                     Long.parseLong(ranges[1]),      //最大区间值
                                    true,   //包含最小值
                                    false);//不包含最大值

                    //创建一个过滤搜索对象FilterQuery
                    FilterQuery filterQuery = new SimpleFilterQuery(criteria);

                    //将FilterFquery加入到Query中
                    query.addFilterQuery(filterQuery);
                }


                //2)价格区间中间的连接符有可能是空格  x=<price 只需要第1个价格区间
                ranges = price.split(" ");
                if(ranges!=null && ranges.length==2){
                    //创建Criteria,用于封装匹配条件
                    //Criteria criteria = new Criteria("item_price").between(Long.parseLong(ranges[0]),null,true,true);
                    Criteria criteria = new Criteria("item_price")
                            .greaterThanEqual(Long.parseLong(ranges[0]));   //最小区间

                    //创建一个过滤搜索对象FilterQuery
                    FilterQuery filterQuery = new SimpleFilterQuery();
                    filterQuery.addCriteria(criteria);

                    //将FilterFquery加入到Query中
                    query.addFilterQuery(filterQuery);
                }
            }

            //分页
            Integer pageNum = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("size");
            if(pageNum==null){
                pageNum=1;
            }
            if(size==null){
                size=10;
            }
            query.setOffset((pageNum-1)*size);//从第几条开始查询     (pageNum-1)*size
            query.setRows(size);      //每页最多显示多少条   size



            //排序功能
            String sort = (String) searchMap.get("sort");
            String sortField= (String) searchMap.get("sortField");

            //执行排序
            if(StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(sortField)){
                //query.addSort(new Sort(Sort.Direction.ASC,sortField));
                Sort orders = null;

                //升序
                if(sort.equalsIgnoreCase("ASC")){
                    orders = new Sort(Sort.Direction.ASC,sortField);
                }else{
                    //降序
                    orders = new Sort(Sort.Direction.DESC,sortField);
                }

                query.addSort(orders);
            }

        }



        //执行查询
        //返回的结果包含非高亮数据和高亮数据
        HighlightPage<Item> highlightPage = solrTemplate.queryForHighlightPage(query, Item.class);
        //高亮数据替换
        highlightReplace(highlightPage);

        //获取返回结果，封装到Map中
        Map<String,Object> dataMap = new HashMap<String,Object>();

        //获取分类分组数据
        List<String> categoryList = getCategory(query);

        //将分类数据存入到Map中，主要作用是页面显示
        dataMap.put("categoryList",categoryList);

        //当用户选择了分类的时候，则根据分类检索规格和品牌
        String category = (String) searchMap.get("category");
        if(StringUtils.isNotBlank(category)){
            dataMap.putAll(getBrandAndSpec(category));
        }else{
            //默认选中第1个分类，查询分类对应的品牌信息和规格信息
            if(categoryList!=null && categoryList.size()>0){
                //Map<String,Object> specBrandMap =  getBrandAndSpec(categoryList.get(0));
                dataMap.putAll(getBrandAndSpec(categoryList.get(0)));
            }
        }

        //总记录数
        long totalElements = highlightPage.getTotalElements();
        dataMap.put("total",totalElements);

        //结果集对象
        List<Item> items = highlightPage.getContent();
        dataMap.put("rows",items);
        return dataMap;
    }

    /****
     * 批量导入索引库
     * @param items
     */
    @Override
    public void importList(List<Item> items) {
        solrTemplate.saveBeans(items);
        solrTemplate.commit();
    }

    /***
     * GoodsId
     * @param ids
     */
    @Override
    public void deleteByGoodsIds(List<Long> ids) {
        //delete from tb_item where goodsid in(xx,xxx,xxx)
        Criteria criteria = new Criteria("item_goodsid").in(ids);
        Query query = new SimpleQuery(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
        //solrTemplate.deleteById(ids);
    }

    /***
     * 获取分类分组数据
     * @param query
     * @return
     */
    public List<String> getCategory(HighlightQuery query) {
        System.out.println("getOffset:"+query.getOffset());
        System.out.println("getRows:"+query.getRows());

        //重置分页
        query.setRows(100);
        query.setOffset(0);

        //分组查询，条件封装都使用上面的Query对象
        GroupOptions groupOptions = new GroupOptions();

        //指定对应的分组域
        groupOptions.addGroupByField("item_category");

        //将分组设置添加到Query中
        query.setGroupOptions(groupOptions);

        //执行查询
        GroupPage<Item> groupPage = solrTemplate.queryForGroupPage(query, Item.class);

        //获取对应域的分组结果
        GroupResult<Item> groupResult = groupPage.getGroupResult("item_category");

        //groupResult中的数据，具备键值对结构
        List<String> categoryList = new ArrayList<String>();
        for (GroupEntry<Item> itemGroupEntry : groupResult.getGroupEntries()) {
            //获取对应结果集
            String groupValue = itemGroupEntry.getGroupValue();
            categoryList.add(groupValue);
        }
        return categoryList;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 获取模板ID
     * 同时获取规格和品牌信息
     */
    public Map<String, Object> getBrandAndSpec(String category){
        Map<String,Object> dataMap = new HashMap<String,Object>();

        //获取模板ID
        Long typeTemplateId = (Long) redisTemplate.boundHashOps("ItemCat").get(category);

        if(typeTemplateId!=null){
            //获取品牌信息
            List<Map> brandList = (List<Map>) redisTemplate.boundHashOps("BrandList").get(typeTemplateId);

            //获取规格信息
            List<Map> specList = (List<Map>) redisTemplate.boundHashOps("SpecList").get(typeTemplateId);


            //将品牌和规格存入到map 中
            dataMap.put("brandList",brandList);
            dataMap.put("specList",specList);
        }

        return dataMap;
    }


    /****
     * 高亮数据替换
     * @param highlightPage
     */
    public void highlightReplace(HighlightPage<Item> highlightPage) {
        //高亮信息和非高亮信息的集合
        List<HighlightEntry<Item>> highlighted = highlightPage.getHighlighted();

        //循环所有数据
        for (HighlightEntry<Item> itemHighlightEntry : highlighted) {
            //获取被循环的非高亮数据
            Item item = itemHighlightEntry.getEntity();

            //获取被循环的高亮数据[假设：它的值只有1条记录]
            List<HighlightEntry.Highlight> highlights = itemHighlightEntry.getHighlights();

            //有高亮数据，则替换非高亮数据
            if(highlights!=null && highlights.size()>0){

                //获取高亮记录
                HighlightEntry.Highlight highlight = highlights.get(0);

                //从高亮记录获取高亮数据[假设：只有1条记录]
                List<String> snipplets = highlight.getSnipplets();

                if(snipplets!=null && snipplets.size()>0){
                    //获取高亮字符
                    String hlstr = snipplets.get(0);
                    //将非高亮数据替换成高亮数据
                    item.setTitle(hlstr);
                }
            }
        }
    }

    /****
     * 高亮设置
     * @param query
     */
    public void highlightSettings(HighlightQuery query) {
        //高亮信息设置
        HighlightOptions highlightOptions = new HighlightOptions();
        //设置item_title为高亮域
        highlightOptions.addField("item_title");
        //设置前缀    例如：    小红<span style="color:red;">吃饭</span>了吗
        highlightOptions.setSimplePrefix("<span style=\"color:red;\">");
        //设置后缀
        highlightOptions.setSimplePostfix("</span>");

        //两高亮选项添加到Query
        query.setHighlightOptions(highlightOptions);
    }


    /****
     *  高亮数据显示
     * @param searchMap
     * @return
     */
    public Map<String, Object> searchNotHl(Map<String, Object> searchMap) {
        //条件搜索  Query
        Query query = new SimpleQuery("*:*");

        //如果searchMap为空，也就是没有传入任何条件，则搜索所有数据
        //如果不为空，则搜索对应关键词的数据
        if(searchMap!=null){
            //关键词的key是keyword
            String keywrod = (String) searchMap.get("keyword");

            //根据关键词查询
            if(StringUtils.isNotBlank(keywrod)){
                //第1个参数：要查询的域
                Criteria criteria = new Criteria("item_keywords").is(keywrod);
                query.addCriteria(criteria);
            }
        }

        //分页
        query.setOffset(0);
        query.setRows(10);

        //执行查询
        ScoredPage<Item> scoredPage = solrTemplate.queryForPage(query, Item.class);

        //获取返回结果，封装到Map中
        Map<String,Object> dataMap = new HashMap<String,Object>();

        //总记录数
        long totalElements = scoredPage.getTotalElements();
        dataMap.put("total",totalElements);

        //结果集对象
        List<Item> items = scoredPage.getContent();
        dataMap.put("rows",items);
        return dataMap;
    }
}
