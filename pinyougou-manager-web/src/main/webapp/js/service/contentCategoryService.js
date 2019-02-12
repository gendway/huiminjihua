/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentCategoryService",function($http){

    //查询列表
    this.findAllList=function(){
        return $http.get("/contentCategory/list.shtml");
    }


    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/contentCategory/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加ContentCategory
    this.add=function(entity){
        return $http.post("/contentCategory/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/contentCategory/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/contentCategory/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/contentCategory/delete.shtml",ids);
    }

});
