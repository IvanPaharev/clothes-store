'use strict';
/*var app = angular.module('myApp',['ui.router'])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('/home');

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'home.html',
                controller: 'dressListController'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'login.html',
                controller: 'loginController'
            })
            .state('register', {
                url: '/register',
                templateUrl: 'register.html',
                controller: 'loginController'
            })
            .state('dressList', {
                url: '/dressList',
                templateUrl: 'dressList.html',
                controller: 'dressListController'
            })
/!*            .state('dress.list', {
                url: '/dressList',
                templateUrl: 'dressList.html',
                controller: 'dressListController'
            })
            .state('dress.id', {
                url: '/dress/:id',
                templateUrl: 'dress.html',
                controller: 'dressListController'
            })*!/
            .state('dress', {
                url: '/dress/:id',
                templateUrl: 'dress.html',
                controller: 'dressListController',
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });*/
var App = angular.module('myApp',['ngRoute', 'ngFileUpload'])
    .config(function($routeProvider, $httpProvider) {

        //noinspection JSUnresolvedFunction
        $routeProvider.when('/', {
            templateUrl : 'pages/home.html',
            controller : 'dressListController'
        }).when('/dressList/:type', {
            templateUrl : 'pages/dressList.html',
            controller : 'dressListController'
        }).when('/dress/:id', {
            templateUrl: 'pages/dress.html',
            controller : 'dressListController'
        }).when('/userBag', {
            templateUrl: 'pages/userBag.html',
            controller : 'dressListController'
        }).when('/orderPage', {
            templateUrl: 'pages/orderPage.html',
            controller : 'paymentController'
        }).when('/dressAdmin', {
            templateUrl: 'pages/dressAdmin.html',
            controller : 'dressListController'
        }).when('/dressAdmin/:id', {
            templateUrl: 'pages/dressAdmin.html',
            controller : 'dressListController'
        }).when('/login', {
            templateUrl : 'pages/login.html',
            controller : 'loginController'
        }).when('/register', {
            templateUrl : 'pages/register.html',
            controller : 'loginController'
        }).when('/categoriesList', {
            templateUrl : 'pages/categoriesList.html',
            controller : 'categoryController'
        }).when('/userRoom', {
            templateUrl : 'pages/user/userRoom.html',
            controller : 'userController'
        }).otherwise('/');

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

