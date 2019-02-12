/*
* 购物车Service
* */
app.service('cartService',function ($http) {

    //提交订单信息
    this.submitOrder=function (order) {
        return $http.post('/order/add.shtml',order);
    }

    //获取用户收货地址
    this.getAddressList=function () {
        return $http.get('/address/user/list.shtml');
    }

    //查询购物车数据
    this.findCartList=function () {
        return $http.get('/cart/list.shtml');
    }

    //加入购物车
    this.add=function (itemid,num) {
        return $http.get('/cart/add.shtml?itemid='+itemid+'&num='+num);
    }

})