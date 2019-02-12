app.controller('seckillGoodsController',function ($scope,$interval,$location,seckillGoodsService) {

    //增加订单
    $scope.add=function () {
        var id=$location.search()['id'];

        if(!isNaN(id)){
            //增加订单
            seckillGoodsService.add(id).success(function (response) {
                if(response.success){
                    //增加订单成功，跳转到支付页
                    alert('下单成功，即将跳转到支付页！');
                    location.href="/pay.html";
                }else{
                    //未登录判断
                    if(response.message=='403'){
                        location.href="/login/loading.shtml";
                    }else{
                        alert(response.message)
                    }
                }
            })
        }
    }


    //调用查询商品列表
    $scope.getOne=function () {
        var id=$location.search()['id'];

        if(!isNaN(id)){
            seckillGoodsService.getOne(id).success(function (response) {
                $scope.item=response;

                //时间差=结束时间-当前时间
                var num = new Date($scope.item.endTime).getTime()-(new Date().getTime());

                //倒计时
                var time = $interval(function () {
                    //每次减1秒
                    num-=1000;

                    if(num<=0){
                        $scope.timestr="已结束";
                        $interval.cancel(time);
                    }else{
                        $scope.timestr = datetimeinfo(num);
                    }
                },1000);
            })
        }
    }


    //秒杀列表
    $scope.list=function () {
        seckillGoodsService.list().success(function (response) {
            $scope.list=response;
        })
    }

    /*
    *
    * 该方法的作用用于换算毫秒时间
    *               换算成：有多少天、有多少时、有多少分钟、有多少秒
    * */
    datetimeinfo=function (num) {
        var second = 1000;          //1秒有多少毫秒
        var minute = second*60;     //1分钟有多少毫秒
        var hour =minute*60;        //1小时有多少毫秒
        var day = hour*24;          //1天有多少毫秒

        //天
        var days = Math.floor(num/day);

        //小时
        var hours =Math.floor(num%day/hour);

        //分钟   天的秒数+小时的秒数 = Math.floor( num/minute*60 )
        var minutes =Math.floor( (num%hour)/minute );

        //秒
        var seconds = Math.floor( (num%minute)/second );
        return days+"天"+hours+"小时"+minutes+'分钟'+seconds+'秒';
    }
});