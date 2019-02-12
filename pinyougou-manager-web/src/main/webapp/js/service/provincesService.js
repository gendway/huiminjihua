/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("provincesService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/provinces/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加Provinces
    this.add=function(entity){
        return $http.post("/provinces/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/provinces/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/provinces/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/provinces/delete.shtml",ids);
    }

});
