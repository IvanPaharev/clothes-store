'use strict';

angular.module('myApp').factory('colorService', ['$http', '$q', function($http, $q){

    return {
        getColors: getColors,
        addColor: addColor,
        updateColor: updateColor,
        deleteColor: deleteColor
    };

    function getColors() {
        var deferred = $q.defer();
        $http.get('color')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching colors');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function addColor(color) {
        var deferred = $q.defer();
        $http.post('color', color)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating color');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateColor(color) {
        var deferred = $q.defer();
        $http.put('color', color)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating color');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteColor(id) {
        var deferred = $q.defer();
        $http.delete('color/'+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting color');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

