'use strict';

angular.module('myApp').factory('typeService', ['$http', '$q', function($http, $q){

    return {
        getTypes: getTypes,
        addType: addType,
        updateType: updateType,
        deleteType: deleteType
    };

    function getTypes() {
        var deferred = $q.defer();
        $http.get('type')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching types');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function addType(type) {
        var deferred = $q.defer();
        $http.post('type', type)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating type');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateType(type) {
        var deferred = $q.defer();
        $http.put('type', type)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating type');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteType(id) {
        var deferred = $q.defer();
        $http.delete('type/'+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting type');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

