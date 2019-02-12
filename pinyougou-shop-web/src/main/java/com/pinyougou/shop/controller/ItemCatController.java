package com.pinyougou.shop.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.ItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    /*****
     * 根据父ID查询所有子分类
     * http://localhost:18082/itemCat/parent/1.shtml    查询父ID=1的所有分类
     * http://localhost:18082/itemCat/parent/2.shtml    查询父ID=2的所有分类
     */
    @RequestMapping(value = "/parent/{id}")
    public List<ItemCat> findByParentId(@PathVariable(value = "id")long id){
         return itemCatService.findByParentId(id);
    }


    /***
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //根据ID删除数据
            int dcount = itemCatService.deleteByIds(ids);

            if(dcount>0){
                return new Result(true,"删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"删除失败");
    }

    /***
     * 修改信息
     * @param itemCat
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody ItemCat itemCat){
        try {
            //根据ID修改ItemCat信息
            int mcount = itemCatService.updateItemCatById(itemCat);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询ItemCat信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ItemCat getById(@PathVariable(value = "id")long id){
        //根据ID查询ItemCat信息
        ItemCat itemCat = itemCatService.getOneById(id);
        return itemCat;
    }


    /***
     * 增加ItemCat数据
     * @param itemCat
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody ItemCat itemCat){
        try {
            //执行增加
            int acount = itemCatService.add(itemCat);

            if(acount>0){
                //增加成功
               return new Result(true,"增加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"增加失败");
    }



    /***
     * 分页查询数据
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageInfo<ItemCat> list(@RequestBody ItemCat itemCat,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return itemCatService.getAll(itemCat,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ItemCat> list() {
        return itemCatService.getAll();
    }
}
