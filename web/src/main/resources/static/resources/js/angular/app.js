'use strict';
var App = angular.module('myApp',['ngRoute', 'ngFileUpload'])
    .config(function($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'pages/public/home.html',
            controller : 'dressListController'
        }).when('/dresses', {
            templateUrl: 'pages/public/dresses.html',
            controller : 'dressListController'
        }).when('/dresses/:type', {
            templateUrl: 'pages/public/dresses.html',
            controller : 'dressListController'
        }).when('/dress/:id', {
            templateUrl: 'pages/public/dress.html',
            controller : 'dressListController'
        }).when('/login', {
            templateUrl: 'pages/public/login.html',
            controller : 'loginController'
        }).when('/register', {
            templateUrl: 'pages/public/register.html',
            controller : 'loginController'
        }).when('/userBag', {
            templateUrl: 'pages/user/userBag.html',
            controller : 'dressListController'
        }).when('/orderPage', {
            templateUrl: 'pages/user/orderPage.html',
            controller : 'orderController'
        }).when('/userRoom', {
            templateUrl: 'pages/user/userRoom.html',
            controller : 'userController'
        }).when('/dressAdmin', {
            templateUrl: 'pages/admin/dressAdmin.html',
            controller : 'dressListController'
        }).when('/dressAdmin/:id', {
            templateUrl: 'pages/admin/dressAdmin.html',
            controller : 'dressListController'
        }).when('/category', {
            templateUrl: 'pages/admin/categories.html',
            controller : 'categoryController'
        }).when('/manufacturer', {
            templateUrl: 'pages/admin/manufacturers.html',
            controller : 'manufacturerController'
        }).when('/user', {
            templateUrl: 'pages/admin/users.html',
            controller : 'userController'
        }).when('/color', {
            templateUrl: 'pages/admin/colors.html',
            controller : 'colorController'
        }).when('/size', {
            templateUrl: 'pages/admin/sizes.html',
            controller : 'sizeController'
        }).when('/role', {
            templateUrl: 'pages/admin/roles.html',
            controller : 'roleController'
        }).when('/order', {
            templateUrl: 'pages/admin/orders.html',
            controller : 'orderController'
        }).when('/status', {
            templateUrl: 'pages/admin/statuses.html',
            controller : 'statusController'
        }).when('/type', {
            templateUrl: 'pages/admin/types.html',
            controller : 'typeController'
        }).otherwise('/', {
            templateUrl: 'pages/public/home.html',
            controller : 'dressListController'
        });
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });

App.directive('restrict', function(userService){
    return{
        restrict: 'A',
        priority: 100000,
        scope: false,
        link: function(){},
        compile:  function(element, attr, linker){
            var accessDenied = true;
            var user = userService.getUser();
            var attributes = attr.access.split(" ");
            for(var i in attributes){
                for (var j in user.authorities) {
                    if(user.authorities[j].authority == attributes[i]){
                        accessDenied = false;
                    }
                }
            }
            if(accessDenied){
                element.children().remove();
                element.remove();
            }
        }
    }
});

