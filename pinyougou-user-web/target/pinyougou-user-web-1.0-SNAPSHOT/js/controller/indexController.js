/*
* 首页获取用户名
* */
app.controller('indexController',function ($scope,loginService) {

    //查询用户名
    $scope.showName=function () {
        loginService.showName().success(function (response) {
            $scope.userName=response;
        })
    }

})