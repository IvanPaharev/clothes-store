'use strict';

angular.module('myApp').controller('categoryController', ['$scope', 'categoryService',
    function($scope, categoryService) {

    var self = this;
    self.category={
        name: null
    };
    self.categories=[];
    self.isErrors = false;
    self.errorMessages = [];

    self.getCategories = getCategories;
    self.addCategory = addCategory;
    self.updateCategory = updateCategory;
    self.deleteCategory = deleteCategory;

    function getCategories(){
        categoryService.getCategories()
            .then(
                function(c) {
                    self.categories = c;
                },
                function(errResponse){
                    console.error('Error while fetching categories'+errResponse);
                }
            );
    }

    function addCategory() {
        categoryService.addCategory(self.category)
            .then(
                function(response) {
                    if (response.data) {
                        self.isErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.categories.push(response);
                        self.isErrors = false;
                    }
                },
                function(errResponse){
                    console.error('Error while adding entity'+errResponse);
                }
            );
    }

    function updateCategory(category) {
        categoryService.updateCategory(category)
            .then(
            function(response) {
                if (response.data) {
                    self.isErrors = response.data.errors;
                    self.errorMessages = response.data.errors;
                }
                if (!response.data) {
                    self.isErrors = false;
                }
            },
            function(errResponse){
                console.error('Error while adding entity'+errResponse);
            }
        );
    }

    function deleteCategory(id) {
        categoryService.deleteCategory(id)
            .then(
                self.categories = [],
                getCategories()
            );
    }

}]);
