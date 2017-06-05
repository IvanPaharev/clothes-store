'use strict';

angular.module('myApp').controller('currencyController', ['$rootScope', 'currencyService',
    function($rootScope, currencyService) {

    var self = this;

    self.fetchCurrencyInfo = fetchCurrencyInfo;

    function fetchCurrencyInfo() {
        currencyService.getCurrencyInfo()
            .then(
                function (currencyInfo) {
                    $rootScope.currencyInfo = currencyInfo;
                    $rootScope.currencyInfo.rates.USD = 1;
                    $rootScope.currentCurrency = 1;
                },
                function (errResponse) {
                    console.error('Error while fetching currency info:' + errResponse.toString());
                }
            );
    }
}]);
