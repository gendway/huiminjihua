/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("goodsDescService",function($http){

    //查询列表
    this.findAll=function(page,size,searchEntity){
        return $http.post("/goodsDesc/list.shtml?page="+page+"&size="+size,searchEntity);
    }

    //增加GoodsDesc
    this.add=function(entity){
        return $http.post("/goodsDesc/add.shtml",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/goodsDesc/update.shtml",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/goodsDesc/"+id+".shtml");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/goodsDesc/delete.shtml",ids);
    }

});
