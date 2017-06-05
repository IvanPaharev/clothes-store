'use strict';

angular.module('myApp').controller('roleController', ['roleService',
    function(roleService) {

    var self = this;
    self.entity={

    };
    self.entities=[];
    self.isErrors = false;
    self.errorMessages = [];

    self.getAll = getAll;
    self.addEntity = addEntity;
    self.updateEntity = updateEntity;
    self.deleteEntity = deleteEntity;

    function getAll(){
        roleService.getAll()
            .then(
                function(entities) {
                    self.entities = entities;
                },
                function(errResponse){
                    console.error('Error while fetching entities'+errResponse);
                }
            );
    }

    function addEntity() {
        roleService.addEntity(self.entity)
            .then(
                function(response) {
                    if (response.data) {
                        self.isErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.entities.push(response);
                        self.isErrors = false;
                    }
                },
                function(errResponse){
                    console.error('Error while adding entity'+errResponse);
                }
            );
    }

    function updateEntity(entity) {
        roleService.updateEntity(entity)
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

    function deleteEntity(id) {
        roleService.deleteEntity(id)
            .then(
                self.entities = [],
                getAll()
            );
    }

}]);
