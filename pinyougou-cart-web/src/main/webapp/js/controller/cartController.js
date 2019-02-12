/*
* 购物车Controller
* */
app.controller('cartController',function ($scope,cartService) {

    //提交订单
    $scope.submitOrder=function () {
        //收货人地址   receiverAreaName
        $scope.order.receiverAreaName=$scope.address.address;

        //收货人手机号  receiverMobile
        $scope.order.receiverMobile=$scope.address.mobile;

        //收货人名字  receiver
        $scope.order.receiver=$scope.address.contact;

        //提交订单信息
        cartService.submitOrder($scope.order).success(function (response) {
            if(response.success){
                //下单成功
                location.href='/pay.html';
            }else{
                alert(response.message)
            }
        })
    }


    //切换支付方式
    $scope.order={paymentType:'1'};
    $scope.selectPayType=function (type) {
        $scope.order.paymentType=type;
    }
    
    //记录当前选中的收件人
    $scope.selectAddress=function (address) {
        $scope.address=address;
    }

    //获取用户的收货地址
    $scope.getAddressList=function () {
        cartService.getAddressList().success(function (response) {
            $scope.addressList=response;

            //循环所有收货地址，判断默认的收货地址
            for(var i=0;i<$scope.addressList.length;i++){
                if($scope.addressList[i].isDefault=='1'){
                    //深克隆
                    $scope.address=angular.copy($scope.addressList[i]);
                }
            }

        })
    }



    //查询所有购物车数据
    $scope.findCartList=function () {
        cartService.findCartList().success(function (response) {
            $scope.cartList=response;

            //计算金额和总数量
            sum($scope.cartList);
        })
    }


    //总金额和总件数计算
    sum=function (cartList) {
        //定义一个参数，用于存储总数量和总价格
        $scope.totalValue={totalNum:0,totalMoney:0.0};

        for(var i=0;i<cartList.length;i++){
            //cart对象
            var cart=cartList[i];

            //获取购物车商品明细列表
            var itemlist = cart.orderItemList;

            //循环商品明细
            for(var j=0;j<itemlist.length;j++){
                //总数量
                $scope.totalValue.totalNum+=itemlist[j].num;
                //总金额
                $scope.totalValue.totalMoney+=itemlist[j].totalFee;
            }
        }
    }

    //添加购物车
    $scope.add=function (itemid,num) {
        cartService.add(itemid,num).success(function (response) {
            if(response.success){
                //刷新页面
                $scope.findCartList();
            }else{
                alert(response.message);
            }
        })
    }

})