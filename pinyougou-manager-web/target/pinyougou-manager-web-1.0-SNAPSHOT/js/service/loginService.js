/*
* 编写一个查询用户的Service
* */
app.service('loginService',function ($http) {

    //获取用户登录名
    this.login=function () {
        return $http.get('/login/name.shtml');
    }
    
})