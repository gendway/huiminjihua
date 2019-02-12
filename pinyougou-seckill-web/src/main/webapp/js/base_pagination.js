//创建一个应用
var app = angular.module('pinyougou',['pagination']);
app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true);
}]);