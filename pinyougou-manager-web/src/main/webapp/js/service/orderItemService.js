/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("orderItemService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/orderItem/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加OrderItem
    this.add=function(entity){
        return $http.post("/orderItem/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/orderItem/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/orderItem/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/orderItem/delete.shtml",ids);
    }

});
