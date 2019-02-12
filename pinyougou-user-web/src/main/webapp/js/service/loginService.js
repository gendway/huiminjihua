/*
* 登录名获取
* */
app.service('loginService',function ($http) {

    //获取用户名
    this.showName=function () {
        return $http.get('/user/name.shtml');
    }

})