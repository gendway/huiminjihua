/*****
 * 定义一个控制层 controller
 * 发送HTTP请求从后台获取数据
 ****/
app.controller("typeTemplateController",function($scope,$http,$controller,typeTemplateService,brandService,specificationService){

    //准备select2的数据
    $scope.brandList = {data: []};

    //id,text,获取所有品牌信息
    $scope.findBrandList=function () {
        brandService.findBrandList().success(function (response) {
            $scope.brandList = {data: response};
        });
    }

    
    //findOptionList
    $scope.specOptionList = {data: []};
    $scope.specList=function () {
         specificationService.findOptionList().success(function (response) {
             $scope.specOptionList= {data: response};
         });
    }


    //创建一个集合，用于存储扩展属性
    $scope.entity={customAttributeItems:[]};

    //增加数据的方法
    $scope.addTableRow=function () {
        $scope.entity.customAttributeItems.push({});
    }

    //删除数据
    $scope.deleteTableRow=function (index) {
        $scope.entity.customAttributeItems.splice(index,1);
    }


    //继承父控制器
    $controller("baseController",{$scope:$scope});

    //获取所有的TypeTemplate信息
    $scope.getPage=function(page,size){
        //发送请求获取数据
        typeTemplateService.findAll(page,size,$scope.searchEntity).success(function(response){
            //集合数据
            $scope.list = response.list;
            //分页数据
            $scope.paginationConf.totalItems=response.total;
        });
    }

    //添加或者修改方法
    $scope.save = function(){
        var result = null;
        if($scope.entity.id!=null){
            //执行修改数据
            result = typeTemplateService.update($scope.entity);
        }else{
            //增加操作
            result = typeTemplateService.add($scope.entity);
        }
        //判断操作流程
        result.success(function(response){
            //判断执行状态
            if(response.success){
                //重新加载新的数据
                $scope.reloadList();
            }else{
                //打印错误消息
                alert(response.message);
            }
        });
    }

    //根据ID查询信息
    $scope.getById=function(id){
        typeTemplateService.findOne(id).success(function(response){
            //将后台的数据绑定到前台
            $scope.entity=response;

            //品牌转JSON
            $scope.entity.brandIds=angular.fromJson( $scope.entity.brandIds );

            //规格转JSON
            $scope.entity.specIds=angular.fromJson( $scope.entity.specIds );

            //扩展属性转JSON
            $scope.entity.customAttributeItems=angular.fromJson( $scope.entity.customAttributeItems );


        });
    }

    //批量删除
    $scope.delete=function(){
        typeTemplateService.delete($scope.selectids).success(function(response){
            //判断删除状态
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }
});
