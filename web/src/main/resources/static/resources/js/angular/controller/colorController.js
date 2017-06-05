'use strict';

angular.module('myApp').controller('colorController', ['$scope', 'colorService',
    function($scope, colorService) {

    var self = this;
    self.color={
        color: null,
        imageSource: null
    };
    self.colors=[];
    self.isErrors = false;
    self.errorMessages = [];

    self.getColors = getColors;
    self.addColor = addColor;
    self.updateColor = updateColor;
    self.deleteColor = deleteColor;

    function getColors(){
        colorService.getColors()
            .then(
                function(colors) {
                    self.colors = colors;
                },
                function(errResponse){
                    console.error('Error while fetching colors'+errResponse);
                }
            );
    }

    function addColor() {
        colorService.addColor(self.color)
            .then(
                function(response) {
                    if (response.data) {
                        self.isErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.colors.push(response);
                        self.isErrors = false;
                    }
                },
                function(errResponse){
                    console.error('Error while adding entity'+errResponse);
                }
            );
    }

    function updateColor(color) {
        colorService.updateColor(color)
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

    function deleteColor(id) {
        colorService.deleteColor(id)
            .then(
                self.colors = [],
                getColors()
            );
    }

}]);
