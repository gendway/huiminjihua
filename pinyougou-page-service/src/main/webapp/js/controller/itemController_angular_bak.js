app.controller('itemController',function ($scope) {


    //定义一个变量，存储当前用户所选择的规格
    //$scope.specList={"网络":"移动3G","尺码":"165寸"};
    $scope.specList={};
    //定义一个默认的SKU
    $scope.sku={};
    //加载默认规格
    $scope.loadDefaultSku=function () {
        $scope.specList=angular.fromJson( itemsList[0].spec );

        //设置默认的Item为默认的SKU
        $scope.sku = angular.copy( itemsList[0] );
    }

    //创建一个方法，用户点击规格的时候调用，并记录用户选择的规格
    $scope.selectSpec=function (key,value) {
        $scope.specList[key]=value;

        //判断当前选中的规格属于第几个商品，然后加载这个商品作为默认的sku
        for(var i=0;i<itemsList.length;i++){
            //获取第i个的spec
            var currentSpec=angular.fromJson( itemsList[i].spec );
            if(angular.equals($scope.specList,currentSpec)){
                //当前被循环的itemsList对象就是选中的SKU
                $scope.sku= angular.copy( itemsList[i] );
            }
        }
    }

    //判断用户是否选中了某规格
    $scope.isSelectedSpec=function (key,value) {
        //如果该规格已经选中，则返回selected
        if($scope.specList[key]==value){
            return 'selected';
        }
    }

});