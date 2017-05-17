'use strict';

angular.module('myApp').factory('userService', ['$http', '$q', function($http, $q){

    var user = {};

    return {
        getUser: getUser,
        authenticate: authenticate,
        logout: logout,
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

    function logout(scope) {
        $http.post('logout', {}).finally(function() {
            scope.authenticated = false;
        });
    }

    function register(user) {
        $http.post('user', user);
    }

}]);

