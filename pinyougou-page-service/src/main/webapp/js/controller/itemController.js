app.controller('itemController',function ($scope,$http) {

    $scope.num =1;

    //加入购物车
    $scope.addCart=function () {
        //alert("购买的Item的ID是："+$scope.sku.id);
        if($scope.sku.id>0){
            //执行加入购物车操作
            $http.get('http://localhost:18093/cart/add.shtml?itemid='+$scope.sku.id+'&num='+$scope.num,{'withCredentials':true}).success(function (response) {
                if(response.success){
                    //加入购物车成功，跳转到购物车列表
                    alert("恭喜你增加购物车成功！")
                    location.href='http://localhost:18093/cart.html';
                }else{
                    alert(response.message)
                }
            })
        }
    }

    //购买数量加减
    $scope.addNum=function (num) {
        $scope.num=parseInt( $scope.num ) + parseInt( num );
        if($scope.num<=0){
            $scope.num=1;
        }
    }


    //定义一个变量，存储当前用户所选择的规格
    //$scope.specList={"网络":"移动3G","尺码":"165寸"};
    $scope.specList={};
    //定义一个默认的SKU
    $scope.sku={};
    //加载默认规格
    $scope.loadDefaultSku=function () {
        //需要深克隆
        $scope.specList=angular.copy( angular.fromJson( itemsList[0].spec ) );

        //设置默认的Item为默认的SKU
        $scope.sku = angular.copy( itemsList[0] );
    }

    //创建一个方法，用户点击规格的时候调用，并记录用户选择的规格
    $scope.selectSpec=function (key,value) {
        $scope.specList[key]=value;

        //判断当前选中的规格属于第几个商品，然后加载这个商品作为默认的sku
        for(var i=0;i<itemsList.length;i++){
            var json = itemsList[i].spec;
            var bo = mapMatch(json,$scope.specList);
            if(bo==true){
                $scope.sku=itemsList[i]
                return;
            }
        }

        //匹配不到
        $scope.sku={"id":0,"title":"---商品已下架----","price":0,"spec":{}};

    }


    //循环匹配2个map结构是否相同
    mapMatch=function (map1,map2) {
        for(var k in map1){
            //map1中的key的值和map2中的相同的key如果相同，说明结构一致，不同则不一致
            if(map1[k]!=map2[k]){
                return false;
            }
        }
        return true;
    }


    //判断用户是否选中了某规格
    $scope.isSelectedSpec=function (key,value) {
        //如果该规格已经选中，则返回selected
        if($scope.specList[key]==value){
            return 'selected';
        }
    }

});