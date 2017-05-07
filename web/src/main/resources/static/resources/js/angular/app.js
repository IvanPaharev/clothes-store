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
            templateUrl : 'home.html',
            controller : 'dressListController'
        }).when('/dressList', {
            templateUrl : 'dressList.html',
            controller : 'dressListController'
        }).when('/dress/:id', {
            templateUrl: '/dress.html',
            controller : 'dressListController'
        }).when('/dressAdmin', {
            templateUrl: '/dressAdmin.html',
            controller : 'dressListController'
        }).when('/dressAdmin/:id', {
            templateUrl: '/dressAdmin.html',
            controller : 'dressListController'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'loginController'
        }).when('/register', {
            templateUrl : 'register.html',
            controller : 'loginController'
        }).when('/categoriesList', {
            templateUrl : 'categoriesList.html',
            controller : 'categoryController'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });

App.directive('restrict', function(authService){
    return{
        restrict: 'A',
        priority: 100000,
        scope: false,
        link: function(){},
        compile:  function(element, attr, linker){
            var accessDenied = true;
            var user = authService.getUser();
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

