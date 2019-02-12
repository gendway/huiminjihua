/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("userService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/user/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加User
    this.add=function(entity){
        return $http.post("/user/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/user/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/user/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/user/delete.shtml",ids);
    }

});
