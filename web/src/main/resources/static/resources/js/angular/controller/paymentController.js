'use strict';

angular.module('myApp').controller('paymentController', ['$scope', 'paymentService',
    function($scope, paymentService) {

        var self = this;
        self.orderData = {
            paymentData: null,
            deliveryAddress: null
        };
        self.paidSuccess = false;

        self.processPayment = processPayment;

        function processPayment(){
            paymentService.processPayment(self.orderData)
                .then(
                    function (d) {
                        self.paidSuccess = d;
                    }
                );
        }

    }]);
