package com.pinyougou.search.service;

import com.pinyougou.model.Item;

import java.util.List;
import java.util.Map;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/12 18:06
 *
 ****/
public interface ItemSearchService {

    /***
     * 搜索方法
     *
     * 传入的参数：Map
     *      关键词、
     *      分类、
     *      品牌、
     *      规格、
     *      价格、
     *      排序
     * 响应数据：Map
     *      商品列表、
     *      分页数据
     * @param searchMap
     * @return
     */
    Map<String,Object> search(Map<String,Object> searchMap);

    /***
     * 批量导入索引库
     * @param items
     */
    void importList(List<Item> items);

    /***
     * 根据GoodsId删除对应的Item索引信息
     * @param ids
     */
    void deleteByGoodsIds(List<Long> ids);
}
