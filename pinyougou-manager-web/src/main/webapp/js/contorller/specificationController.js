/*****
 * 定义一个控制层 controller
 * 发送HTTP请求从后台获取数据
 ****/
app.controller("specificationController",function($scope,$http,$controller,specificationService){

    //定义一个集合，用于存储所有规格选项 存入entity对象中
    //$scope.entity={specificationOptionList:[]}

    //新增一行规格属性
    $scope.addTableRow=function () {
        //往集合中增加一个空数据
        $scope.entity.specificationOptionList.push({});
    }

    //删除一行
    $scope.deleteTableRow=function (index) {
        $scope.entity.specificationOptionList.splice(index,1);
    }



    //继承父控制器
    $controller("baseController",{$scope:$scope});

    //获取所有的Specification信息
    $scope.getPage=function(page,size){
        //发送请求获取数据
        specificationService.findAll(page,size,$scope.searchEntity).success(function(response){
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
            result = specificationService.update($scope.entity);
        }else{
            //增加操作
            result = specificationService.add($scope.entity);
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
        specificationService.findOne(id).success(function(response){
            //将后台的数据绑定到前台
            $scope.entity=response;
        });
    }

    //批量删除
    $scope.delete=function(){
        specificationService.delete($scope.selectids).success(function(response){
            //判断删除状态
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }
});
