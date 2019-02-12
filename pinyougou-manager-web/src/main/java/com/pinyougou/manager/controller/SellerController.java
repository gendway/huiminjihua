package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    /****
     * http://localhost:8080/seller/status/xiaohong/1.shtml  审核通过
     * http://localhost:8080/seller/status/xiaohong/2.shtml  审核不通过
     * http://localhost:8080/seller/status/xiaohong/3.shtml  关闭
     * @return
     */
    @RequestMapping(value = "/status/{sellerid}/{stu}")
    public Result updateStatus(@PathVariable(value = "sellerid")String sellerid,
                               @PathVariable(value = "stu")String status){
        try {
            //修改商户信息
            Seller seller = new Seller();
            seller.setSellerId(sellerid);
            seller.setStatus(status);

            //修改
            int mcount = sellerService.updateSellerById(seller);

            if(mcount>0){
                //修改成功
                return  new Result(true);
            }
        } catch (Exception e) {
        }
        return new Result(false,"修改状态失败！");
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
            int dcount = sellerService.deleteByIds(ids);

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
     * @param seller
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody Seller seller){
        try {
            //根据ID修改Seller信息
            int mcount = sellerService.updateSellerById(seller);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询Seller信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Seller getById(@PathVariable(value = "id")String id){
        //根据ID查询Seller信息
        Seller seller = sellerService.getOneById(id);
        return seller;
    }


    /***
     * 增加Seller数据
     * @param seller
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Seller seller){
        try {
            //执行增加
            int acount = sellerService.add(seller);

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
    public PageInfo<Seller> list(@RequestBody Seller seller,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return sellerService.getAll(seller,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Seller> list() {
        return sellerService.getAll();
    }
}
