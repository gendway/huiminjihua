package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value = "/specificationOption")
public class SpecificationOptionController {

    @Reference
    private SpecificationOptionService specificationOptionService;


    /***
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody List<Long> ids){
        try {
            //根据ID删除数据
            int dcount = specificationOptionService.deleteByIds(ids);

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
     * @param specificationOption
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result modify(@RequestBody SpecificationOption specificationOption){
        try {
            //根据ID修改SpecificationOption信息
            int mcount = specificationOptionService.updateSpecificationOptionById(specificationOption);
            if(mcount>0){
                return new Result(true,"修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    /***
     * 根据ID查询SpecificationOption信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SpecificationOption getById(@PathVariable(value = "id")long id){
        //根据ID查询SpecificationOption信息
        SpecificationOption specificationOption = specificationOptionService.getOneById(id);
        return specificationOption;
    }


    /***
     * 增加SpecificationOption数据
     * @param specificationOption
     * 响应数据：success
     *                  true:成功  false：失败
     *           message
     *                  响应的消息
     *
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody SpecificationOption specificationOption){
        try {
            //执行增加
            int acount = specificationOptionService.add(specificationOption);

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
    public PageInfo<SpecificationOption> list(@RequestBody SpecificationOption specificationOption,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return specificationOptionService.getAll(specificationOption,page, size);
    }



    /***
     * 查询所有
     * 获取JSON数据
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SpecificationOption> list() {
        return specificationOptionService.getAll();
    }
}
