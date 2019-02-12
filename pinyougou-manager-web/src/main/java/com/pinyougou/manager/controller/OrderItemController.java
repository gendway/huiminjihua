package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.OrderItem;
import com.pinyougou.sellergoods.service.OrderItemService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/orderItem")
public class OrderItemController {

    @Reference
    private OrderItemService orderItemService;


    /***
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //根据ID删除数据
            int dcount = orderItemService.deleteByIds(ids);

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
     * @param orderItem
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody OrderItem orderItem){
        try {
            //根据ID修改OrderItem信息
            int mcount = orderItemService.updateOrderItemById(orderItem);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询OrderItem信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public OrderItem getById(@PathVariable(value = "id")long id){
        //根据ID查询OrderItem信息
        OrderItem orderItem = orderItemService.getOneById(id);
        return orderItem;
    }


    /***
     * 增加OrderItem数据
     * @param orderItem
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody OrderItem orderItem){
        try {
            //执行增加
            int acount = orderItemService.add(orderItem);

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
    public PageInfo<OrderItem> list(@RequestBody OrderItem orderItem,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return orderItemService.getAll(orderItem,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<OrderItem> list() {
        return orderItemService.getAll();
    }
}
