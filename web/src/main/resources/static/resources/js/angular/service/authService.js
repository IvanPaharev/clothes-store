'use strict';

angular.module('myApp').factory('authService', ['$http', function($http){

    var user = {};

    return {
        getUser: getUser,
        authenticate: authenticate,
        logout: logout,
        register: register
    };

    function getUser() {
        return user;
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

    function logout(scope) {
        $http.post('logout', {}).finally(function() {
            scope.authenticated = false;
        });
    }

    function register(user) {
        $http.post('user', user);
    }

}]);

