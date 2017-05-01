/**
 * Created by A-one on 29.04.2017.
 */
'use strict';

angular.module('myApp').controller('dressController', ['$scope', '$location', 'dressService', function($scope, $location, dressService) {
    var self = this;
    self.dress = {};

    self.fetchDressById = fetchDressById;
    self.logDress = logDress;

    function logDress() {
        self.dress = dressService.getDress();
        console.log(self.dress);
    }

    function fetchDressById(id) {
        dressService.fetchDressById(id)
            .then(
                function (d) {
                    self.dress = d;
                },
                function (errResponse) {
                    console.error('Error while fetching dress by id:' + errResponse.toString());
                }
            );
        $location.path("/dress/"+id);
    }
}]);
