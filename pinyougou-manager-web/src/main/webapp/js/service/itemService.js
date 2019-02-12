/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("itemService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/item/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加Item
    this.add=function(entity){
        return $http.post("/item/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/item/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/item/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/item/delete.shtml",ids);
    }

});
