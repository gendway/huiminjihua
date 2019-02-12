/*
* 创建一个控制层
* */
app.controller('loginController',function ($scope,loginService) {
    
    
    //调用查询用户名的方法
    $scope.login=function () {
        loginService.login().success(function (response) {
            $scope.username=response;
        });
    }
});