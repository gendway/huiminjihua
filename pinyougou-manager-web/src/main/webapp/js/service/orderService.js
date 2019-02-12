/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("orderService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/order/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加Order
    this.add=function(entity){
        return $http.post("/order/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/order/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/order/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/order/delete.shtml",ids);
    }

});
