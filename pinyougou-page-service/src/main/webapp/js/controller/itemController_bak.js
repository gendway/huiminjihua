app.controller('itemController',function ($scope) {


    //定义一个变量，存储当前用户所选择的规格
    //$scope.specList={"网络":"移动3G","尺码":"165寸"};
    $scope.specList={};

    //定义一个默认的SKU
    $scope.sku={};

    console.log(itemsList);
    //加载默认规格
    $scope.loadDefaultSku=function () {
        $scope.specList=angular.fromJson( itemsList[0].spec );

        //设置默认的Item为默认的SKU
        $scope.sku = angular.copy( itemsList[0] );
    }

    //创建一个方法，用户点击规格的时候调用，并记录用户选择的规格
    $scope.selectSpec=function (key,value) {
        $scope.specList[key]=value;
    }

    //判断用户是否选中了某规格
    $scope.isSelectedSpec=function (key,value) {
        //如果该规格已经选中，则返回selected
        if($scope.specList[key]==value){
            return 'selected';
        }
    }

});