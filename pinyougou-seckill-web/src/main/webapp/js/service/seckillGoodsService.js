app.service('seckillGoodsService',function ($http) {

    //下单
    this.add=function (id) {
        return $http.get('/seckill/order/add.shtml?id='+id);
    }

    //根据DI查询商品详情
    this.getOne=function (id) {
        return $http.get('/seckill/goods/one.shtml?id='+id);
    }

    //秒杀列表
    this.list=function () {
        return $http.get('/seckill/goods/list.shtml');
    }

})