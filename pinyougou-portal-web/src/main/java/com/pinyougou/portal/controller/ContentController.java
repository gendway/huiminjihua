package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.model.Content;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Reference
    private ContentService contentService;


    /****
     * 根据分类查询广告信息
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/findByCategoryId")
    public List<Content> getByCategoryId(long categoryId){
        return  contentService.findByCategoryId(categoryId);
    }


}
