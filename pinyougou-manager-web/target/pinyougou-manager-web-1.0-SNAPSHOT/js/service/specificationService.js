/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("specificationService",function($http){

    //查询规格选项
    this.findOptionList=function () {
        return $http.get('/specification/optionlist.shtml');
    }

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/specification/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加Specification
    this.add=function(entity){
        return $http.post("/specification/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/specification/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/specification/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/specification/delete.shtml",ids);
    }

});
