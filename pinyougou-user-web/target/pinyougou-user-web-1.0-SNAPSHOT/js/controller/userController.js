/*
* 创建Controller
* */
app.controller('userController',function ($scope,userService) {

    //创建验证码
    $scope.createCode=function () {
        userService.createCode($scope.entity.phone).success(function (response) {
            alert(response.message);
        });
    }


    //注册方法
    $scope.reg=function () {
        userService.reg($scope.entity,$scope.code).success(function (response) {
            alert(response.message);
        })
    }
    
})