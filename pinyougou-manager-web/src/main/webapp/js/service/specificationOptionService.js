/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("specificationOptionService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/specificationOption/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加SpecificationOption
    this.add=function(entity){
        return $http.post("/specificationOption/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/specificationOption/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/specificationOption/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/specificationOption/delete.shtml",ids);
    }

});
