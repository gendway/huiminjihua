/*
* 创建service
* */
app.service('loginService',function ($http) {

    //向后台发送请求执行登录
    this.login=function (username,password) {
        return $http.post('/login?username='+username+'&password='+password);
    }

})