'use strict';

angular.module('myApp').factory('roleService', ['$http', '$q', function($http, $q){

    return {
        getAll: getAll,
        addEntity: addEntity,
        updateEntity: updateEntity,
        deleteEntity: deleteEntity
    };

    function getAll() {
        var deferred = $q.defer();
        $http.get('role')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching entities'+errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function addEntity(role) {
        var deferred = $q.defer();
        $http.post('role', role)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating entity'+errResponse);
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateEntity(role) {
        var deferred = $q.defer();
        $http.put('role', role)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating entity'+errResponse);
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteEntity(id) {
        var deferred = $q.defer();
        $http.delete('role/'+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting entity'+errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

