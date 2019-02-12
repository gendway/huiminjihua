//文件上传
app.service('uploadService',function ($http) {

    //上传文件
    this.uploadFile = function(){
        //针对某表单的操作方式
       //var formxiaohong = document.querySelector("xiaohong")

        //H5支持的表单打包,FormData对象可以组装一组用 XMLHttpRequest发送请求的键/值对。它可以更灵活方便的发送表单数据
        //打包的是当前js所在的页面的表单对象[一般是1个表单对象]
        var formData=new FormData();

        //往formData表单中添加数据
        //向表单中追加一个文件，文件的名字叫file,file.files[0]:表示当前js所在的页面的表单中type="file"的第一个元素
        formData.append("file",file.files[0]);

        //提交请求
        return $http({
            method:'POST',
            url:"/upload.shtml",
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });

        // 'Content-Type':undefined : <form enctype="multipart/form-data"
        // transformRequest: angular.identity表单序列化
    }
})
