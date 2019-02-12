/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("typeTemplateService",function($http){

    //查询规格选项
    this.getOptionsByTypeId=function (id) {
        return $http.get('/typeTemplate/options/'+id+'.shtml');
    }

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/typeTemplate/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加TypeTemplate
    this.add=function(entity){
        return $http.post("/typeTemplate/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/typeTemplate/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/typeTemplate/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/typeTemplate/delete.shtml",ids);
    }

});
