'use strict';

angular.module('myApp').factory('manufacturerService', ['$http', '$q', function($http, $q){

    return {
        getAll: getAll,
        addEntity: addEntity,
        updateEntity: updateEntity,
        deleteEntity: deleteEntity
    };

    function getAll() {
        var deferred = $q.defer();
        $http.get('manufacturer')
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

    function addEntity(manufacturer) {
        var deferred = $q.defer();
        $http.post('manufacturer', manufacturer)
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


    function updateEntity(manufacturer) {
        var deferred = $q.defer();
        $http.put('manufacturer', manufacturer)
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
        $http.delete('manufacturer/'+id)
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

