/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentService",function($http){

    //调用后台
    this.findByCategoryid=function (categoryid) {
        return $http.get('/content/findByCategoryId.shtml?categoryId='+categoryid);
    }

});
