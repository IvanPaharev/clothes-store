'use strict';

angular.module('myApp').factory('currencyService', ['$http', '$q', function($http, $q){

    return {
        getCurrencyInfo: getCurrencyInfo
    };

    function getCurrencyInfo() {
        var deferred = $q.defer();
        $http.get('currencyInfo')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching currency info');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);


