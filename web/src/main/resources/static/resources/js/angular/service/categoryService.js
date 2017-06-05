'use strict';

angular.module('myApp').factory('categoryService', ['$http', '$q', function($http, $q){

    return {
        getCategories: getCategories,
        addCategory: addCategory,
        updateCategory: updateCategory,
        deleteCategory: deleteCategory
    };

    function getCategories() {
        var deferred = $q.defer();
        $http.get('category')
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

    function addCategory(category) {
        var deferred = $q.defer();
        $http.post('category', category)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating category');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateCategory(category) {
        var deferred = $q.defer();
        $http.put('category', category)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating User');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteCategory(id) {
        var deferred = $q.defer();
        $http.delete('category/'+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

