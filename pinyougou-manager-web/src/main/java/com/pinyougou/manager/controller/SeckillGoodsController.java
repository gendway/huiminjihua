package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.SeckillGoods;
import com.pinyougou.sellergoods.service.SeckillGoodsService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/seckillGoods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;


    /***
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //根据ID删除数据
            int dcount = seckillGoodsService.deleteByIds(ids);

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
     * @param seckillGoods
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody SeckillGoods seckillGoods){
        try {
            //根据ID修改SeckillGoods信息
            int mcount = seckillGoodsService.updateSeckillGoodsById(seckillGoods);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询SeckillGoods信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SeckillGoods getById(@PathVariable(value = "id")long id){
        //根据ID查询SeckillGoods信息
        SeckillGoods seckillGoods = seckillGoodsService.getOneById(id);
        return seckillGoods;
    }


    /***
     * 增加SeckillGoods数据
     * @param seckillGoods
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody SeckillGoods seckillGoods){
        try {
            //执行增加
            int acount = seckillGoodsService.add(seckillGoods);

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
    public PageInfo<SeckillGoods> list(@RequestBody SeckillGoods seckillGoods,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return seckillGoodsService.getAll(seckillGoods,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SeckillGoods> list() {
        return seckillGoodsService.getAll();
    }
}
