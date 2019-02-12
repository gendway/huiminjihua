package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.manager.service.MessageSender;
import com.pinyougou.model.Goods;
import com.pinyougou.model.Item;
import com.pinyougou.mq.MessageInfo;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @Autowired
    private MessageSender messageSender;

    /***
     * 审核操作
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping(value = "/update/status")
    public Result updateStatus(@RequestBody List<Long> ids,String status){
        try {
            int mcount = goodsService.updateStatus(ids,status);
            if(mcount>0){

                if(status.equals("1")){
                    //如果审核通过,将商品信息Item增加到索引库
                    //根据GoodsIds查询Items
                    List<Item> items = goodsService.getByGoodsIds(ids,status);

                    //发送的消息封装
                    MessageInfo messageInfo = new MessageInfo(MessageInfo.METHOD_UPDATE, items);

                    //调用消息发送
                    messageSender.sendObjectMessage(messageInfo);
                }
                return new Result(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new Result(false,"审核失败");
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
            int dcount = goodsService.deleteByIds(ids);

            if(dcount>0){
                //发送消息到消息队列
                MessageInfo messageInfo = new MessageInfo(MessageInfo.METHOD_DELETE, ids);

                //发送消息
                messageSender.sendObjectMessage(messageInfo);
                return new Result(true,"删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"删除失败");
    }

    /***
     * 修改信息
     * @param goods
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody Goods goods){
        try {
            //根据ID修改Goods信息
            int mcount = goodsService.updateGoodsById(goods);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询Goods信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Goods getById(@PathVariable(value = "id")long id){
        //根据ID查询Goods信息
        Goods goods = goodsService.getOneById(id);
        return goods;
    }


    /***
     * 增加Goods数据
     * @param goods
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Goods goods){
        try {
            //执行增加
            int acount = goodsService.add(goods);

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
    public PageInfo<Goods> list(@RequestBody Goods goods,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return goodsService.getAll(goods,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Goods> list() {
        return goodsService.getAll();
    }
}
