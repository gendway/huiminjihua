package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.GoodsDesc;
import com.pinyougou.sellergoods.service.GoodsDescService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/goodsDesc")
public class GoodsDescController {

    @Reference
    private GoodsDescService goodsDescService;


    /***
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //根据ID删除数据
            int dcount = goodsDescService.deleteByIds(ids);

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
     * @param goodsDesc
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody GoodsDesc goodsDesc){
        try {
            //根据ID修改GoodsDesc信息
            int mcount = goodsDescService.updateGoodsDescById(goodsDesc);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询GoodsDesc信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public GoodsDesc getById(@PathVariable(value = "id")long id){
        //根据ID查询GoodsDesc信息
        GoodsDesc goodsDesc = goodsDescService.getOneById(id);
        return goodsDesc;
    }


    /***
     * 增加GoodsDesc数据
     * @param goodsDesc
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody GoodsDesc goodsDesc){
        try {
            //执行增加
            int acount = goodsDescService.add(goodsDesc);

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
    public PageInfo<GoodsDesc> list(@RequestBody GoodsDesc goodsDesc,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return goodsDescService.getAll(goodsDesc,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<GoodsDesc> list() {
        return goodsDescService.getAll();
    }
}
