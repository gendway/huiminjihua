/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/content/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加Content
    this.add=function(entity){
        return $http.post("/content/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/content/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/content/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/content/delete.shtml",ids);
    }

});
