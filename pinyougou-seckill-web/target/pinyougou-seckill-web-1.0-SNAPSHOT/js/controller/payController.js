/*
* 支付controller
* */
app.controller('payController',function ($scope,$location,payService) {

    //获取支付金额
    $scope.getMoney=function () {
        return $location.search()['money'];
    }

    //二维码创建
    $scope.createNative=function () {
        payService.createNative().success(function (response) {
            //支付编号
            $scope.out_trade_no=response.out_trade_no;
            //交易金额
            $scope.total_fee=(response.total_fee/100);
            //url地址
            $scope.url=response.code_url;

            //创建二维码
            var qr = window.qr = new QRious({
                element: document.getElementById('payimg'),
                size: 300,
                value: $scope.url,
                level:'M'
            })

            //查询支付状态
            payService.queryPayStatus($scope.out_trade_no).success(function (response) {
                if(response.success){
                    location.href='/paysuccess.html?money='+$scope.total_fee;
                }else{
                    //跳转到支付失败页面
                    location.href='/payfail.html';
                }
            })
        })
    }


})