'use strict';

angular.module('myApp').factory('languageService', ['$http', '$q', function($http, $q){

    return {
        fetchMessages: fetchMessages
    };

    function fetchMessages(lang) {
        var deferred = $q.defer();
        $http.get('messages/'+lang)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching messages');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);


