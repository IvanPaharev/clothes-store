'use strict';

angular.module('myApp').controller('typeController', ['$scope', 'typeService',
    function($scope, typeService) {

    var self = this;
    self.type={
        name: null
    };
    self.types=[];
    self.isErrors = false;
    self.errorMessages = [];

    self.getTypes = getTypes;
    self.addType = addType;
    self.updateType = updateType;
    self.deleteType = deleteType;

    function getTypes(){
        typeService.getTypes()
            .then(
                function(types) {
                    self.types = types;
                },
                function(errResponse){
                    console.error('Error while fetching types'+errResponse);
                }
            );
    }

    function addType() {
        typeService.addType(self.type)
            .then(
                function(response) {
                    if (response.data) {
                        self.isErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.types.push(response);
                        self.isErrors = false;
                    }
                },
                function(errResponse){
                    console.error('Error while adding entity'+errResponse);
                }
            );
    }

    function updateType(type) {
        typeService.updateType(type)
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

    function deleteType(id) {
        typeService.deleteType(id)
            .then(
                self.types = [],
                getTypes()
            );
    }

}]);
