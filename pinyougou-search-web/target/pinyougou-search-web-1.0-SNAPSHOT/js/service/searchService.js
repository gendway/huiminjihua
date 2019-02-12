/*
* service
* */
app.service('searchService',function ($http) {

    //搜索实现
    this.search=function (entity) {
        return $http.post('/item/search.shtml',entity);
    }

})