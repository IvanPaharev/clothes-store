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

/*    function fetchDressById(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                    console.log("this is servise ressponce")
                    dress = response.data;
                },
                function (errResponse) {
                    console.error('Error while fetching user by id');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getDress() {
        console.log("service get dress "+dress);
        return dress;
    }*/

    function addCategory(category) {
        var deferred = $q.defer();
        $http.post('category', category)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating category');
                    deferred.reject(errResponse);
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
                    deferred.reject(errResponse);
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

