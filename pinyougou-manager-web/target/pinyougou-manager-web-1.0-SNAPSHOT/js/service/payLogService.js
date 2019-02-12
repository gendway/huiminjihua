/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("payLogService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/payLog/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加PayLog
    this.add=function(entity){
        return $http.post("/payLog/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/payLog/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/payLog/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/payLog/delete.shtml",ids);
    }

});
