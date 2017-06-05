'use strict';

angular.module('myApp').controller('orderController', ['orderService',
    function(orderService) {

    var self = this;
    self.entity={

    };
    self.entities=[];
    self.orderData = {
        paymentData: null,
        deliveryAddress: null
    };
    self.paidSuccess = false;
    self.isErrors = false;
    self.errorMessages = [];


    self.getAll = getAll;
    self.addEntity = addEntity;
    self.updateEntity = updateEntity;
    self.deleteEntity = deleteEntity;

    function getAll(){
        orderService.getAll()
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
        orderService.addEntity(self.entity)
            .then(
                function(response) {
                    if (response.data) {
                        self.isErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.entities.push(response);
                        self.paidSuccess = response;
                        self.isErrors = false;
                    }
                },
                function(errResponse){
                    console.error('Error while adding entity'+errResponse);
                }
            );
    }

    function updateEntity(entity) {
        orderService.updateEntity(entity)
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
        orderService.deleteEntity(id)
            .then(
                self.entities = [],
                getAll()
            );
    }

}]);
