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
var App = angular.module('myApp',['ngRoute'])
    .config(function($routeProvider, $httpProvider) {

        //noinspection JSUnresolvedFunction
        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'dressListController'
        }).when('/dressList', {
            templateUrl : 'dressList.html',
            controller : 'dressListController'
        }).when('/dress/:name', {
            templateUrl: '/dress.html',
            controller : 'dressListController'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'loginController'
        }).when('/register', {
            templateUrl : 'register.html',
            controller : 'loginController'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });


