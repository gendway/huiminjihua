/*
* 搜索Controller实现
* */
app.controller('searchController',function ($scope,$location,searchService) {

    //定义一个数据，用于存储选择的筛选条件
    $scope.searchMap={"keyword":"","category":"","brand":"",spec:{},"price":"","pageNum":1,"size":10,"sort":"","sortField":""};

    //定义一个集合存储所有品牌
    $scope.resultMap={brandList:[]};

    //加载关键字
    $scope.loadKeyword=function () {
        //获取地址栏的关键字
        var keyword = $location.search()['keyword'];

        //搜索操作
        if(keyword!=null){
            $scope.searchMap.keyword=keyword;
        }

        //搜素
        $scope.search();
    }

    //搜索品牌
    $scope.keywordsLoadBrand=function () {
        if($scope.resultMap.brandList!=null){
            for(var i=0;i<$scope.resultMap.brandList.length;i++){
                //获取品牌名字
                var brandName = $scope.resultMap.brandList[i].text;
                var index = $scope.searchMap.keyword.indexOf(brandName);

                if(index>=0){
                    //将品牌加入到brand中
                    //$scope.searchMap.brand=brandName;
                    return true;
                }
            }
        }
        return false;
    }
    
    //排序搜索
    $scope.sortSearch=function (sort,sortField) {
        $scope.searchMap.sort=sort;
        $scope.searchMap.sortField=sortField;

        //搜索
        $scope.search();
    }


    //搜索条件移除实现
    $scope.removeItemSearch=function (key) {
        if(key=='category' || key=='brand' || key=='price'){
            $scope.searchMap[key]='';
        }else{
            //将数据从map结构中移除
            delete $scope.searchMap.spec[key];
        }

        //搜索实现
        $scope.search();
    }

    //点击搜索条件的时候，将选中的搜索条件记录
    $scope.addItemSearch=function (key,value) {
        if(key=='category' || key=='brand' || key=='price'){
            $scope.searchMap[key]=value;
        }else{
            $scope.searchMap.spec[key]=value;
        }

        //搜索实现
        $scope.search();
    }


    //搜索方法
    $scope.search=function () {
        searchService.search($scope.searchMap).success(function (response) {
            $scope.resultMap=response;

            //计算分页数据
            $scope.pageHandler(response.total,$scope.searchMap.pageNum);
        })
    }

    
    //分页搜索 pageNum:需要跳转的页码
    $scope.pageSearch=function (pageNum) {
        if(!isNaN(pageNum)){
            $scope.searchMap.pageNum=parseInt(pageNum);
        }

        //如果pageNum不为数字
        if(isNaN(pageNum)){
            $scope.searchMap.pageNum=1;
        }

        //当前要跳转的页面>总页数? 当前要跳转的页面=总页数
        if($scope.searchMap.pageNum>$scope.page.totalPage){
            $scope.searchMap.pageNum=$scope.page.totalPage;
        }
        //调用搜索
        $scope.search();
    }



    //分页定义
    $scope.page={
        size:10,        //每页显示多少条
        total:0,        //总共有多少条记录
        pageNum:1,      //当前页
        offset:1,       //偏移量
        lpage:1,        //起始页
        rpage:1,        //结束页
        totalPage:1,    //总页数
        pages:[],       //页码
        nextPage:1,     //下一页
        prePage:1,       //上一页
        hasPre:0,       //是否有上页
        hasNext:0       //是否有下页
    }
    
    
    /*
    * 分页计算
    * total:总记录数
    * pageNum:当前页
    * */
    $scope.pageHandler=function (total,pageNum) {
        //将pageNum给page.ageNum
        $scope.page.pageNum=pageNum;
        //总页数
        var totalPage = total%$scope.page.size==0? total/$scope.page.size:parseInt((total/$scope.page.size)+1);
        $scope.page.totalPage=totalPage;

        //偏移量
        var offset=$scope.page.offset;
        //起始页和结束页
        var lpage=$scope.page.lpage;
        var rpage=$scope.page.rpage;


        //1) pageNum-offset>0
        if((pageNum-offset)>0){
            lpage=pageNum-offset;
            rpage=pageNum+offset;
        }

        //2) pageNum-offset<=0
        if((pageNum-offset)<=0){
            lpage=1;
            rpage=pageNum+offset + Math.abs(pageNum-offset)  +1;    //Math.abs求绝对值
        }

        //3) rpage>totalPage
        if(rpage>totalPage){
            lpage=lpage-(rpage-totalPage);
            rpage=totalPage;
        }

        //4) lpage<=0
        if(lpage<=0){
            lpage=1;
        }

        //页码封装
        $scope.page.pages=[];
        for(var i=lpage;i<=rpage;i++){
            $scope.page.pages.push(i);
        }

        //下一页、上一页实现
        if((pageNum-1)>=1){
            $scope.page.prePage=(pageNum-1);
            $scope.page.hasPre=1;
        }else{
            $scope.page.hasPre=0;
        }
        if(pageNum<totalPage){
            $scope.page.nextPage=(pageNum+1);
            $scope.page.hasNext=1;
        }else{
            $scope.page.hasNext=0;
        }
    }
    
    
    
});