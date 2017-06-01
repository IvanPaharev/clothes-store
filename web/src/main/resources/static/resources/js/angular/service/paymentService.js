'use strict';

angular.module('myApp').factory('paymentService', ['$http', '$q', function($http, $q){

    return {
        processPayment: processPayment
    };

    function processPayment(orderData) {
        var deferred = $q.defer();
        $http.post('userOrder/new', orderData)
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

}]);


