package com.pinyougou.page.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2018/9/17 15:53
 *
 ****/
public interface ItemPageService {

    /***
     * 根据GoodsId生成静态页
     * @param goodsId : Goods、GoodsDesc、List<Item>、把goodsId作为静态页名字
     * @return
     */
    Boolean buildHtml(Long goodsId) throws Exception;

    /***
     * 根据ID删除静态页
     * @param id
     */
    void deleteHtml(Long id);
}
