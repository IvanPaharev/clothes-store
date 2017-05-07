'use strict';

angular.module('myApp').controller('categoryController', ['$scope', 'categoryService',
    function($scope, categoryService) {

    var self = this;
    self.category={
        name: null
    };
    self.categories=[];

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
                self.categories = [],
                getCategories()
            );
    }

    function updateCategory(category) {
        categoryService.updateCategory(category);
    }

    function deleteCategory(id) {
        categoryService.deleteCategory(id)
            .then(
                self.categories = [],
                getCategories()
            );
    }

}]);
