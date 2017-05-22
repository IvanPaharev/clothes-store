'use strict';

angular.module('myApp').factory('userService', ['$http', '$q', function($http, $q){

    var user = {};

    return {
        getUser: getUser,
        authenticate: authenticate,
        logout: logout,
        initAuthentication: initAuthentication,
        register: register,
        fetchUser: fetchUser,
        updateUser: updateUser
    };

    function getUser() {
        return user;
    }

    function fetchUser() {
        var deferred = $q.defer();
        $http.post('user/details', user.name)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching categories');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function updateUser(user) {
        $http.put('user', user);
    }

    function authenticate(scope, credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).then(function(response) {
            if (response.data.name) {
                user = response.data;
                scope.authenticated = true;
            } else {
                scope.authenticated = false;
            }
            callback && callback();
        }, function() {
            scope.authenticated = false;
            callback && callback();
        });

    }

    function initAuthentication(scope) {
        var deferred = $q.defer();
        $http.get('user')
            .then(
                function(response) {
                    if (response.data.name) {
                        user = response.data;
                        scope.authenticated = true;
                        deferred.resolve(response.data);
                    } else {
                        scope.authenticated = false;
                    }
                },
                function (errResponse) {
                    console.error('Error while auth initialization: ' + errResponse);
                    deferred.resolve(errResponse)
                }
            );
        return deferred.promise;
    }

    function logout(scope, location) {
        $http.post('logout', {}).finally(function() {
            scope.authenticated = false;
            location.path("/");
        });
    }

    function register(user) {
        $http.post('user', user);
    }

}]);

