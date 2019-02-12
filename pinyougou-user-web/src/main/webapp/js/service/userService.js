/*
* 创建Service
* */
app.service('userService',function ($http) {

    //创建验证码
    this.createCode=function (phone) {
        return $http.get('/user/create/code.shtml?phone='+phone);
    }

    //注册功能
    this.reg=function (entity,code) {
        return $http.post('/user/add.shtml?code='+code,entity);
    }


})