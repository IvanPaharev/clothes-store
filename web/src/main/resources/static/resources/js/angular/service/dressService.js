'use strict';

angular.module('myApp').factory('dressService', ['$http', '$q', 'Upload', function($http, $q, Upload){

    var REST_SERVICE_URI = 'http://localhost:8080/dress/';

    var dress = {};

    var onLoad = function(reader, deferred, scope) {
        return function() {
            scope.$apply(function() {
                deferred.resolve(reader.result);
            });
        };
    };

    var onError = function(reader, deferred, scope) {
        return function() {
            scope.$apply(function() {
                deferred.reject(reader.result);
            });
        };
    };

    var onProgress = function(reader, scope) {
        return function(event) {
            scope.$broadcast("fileProgress", {
                total: event.total,
                loaded: event.loaded
            });
        };
    };

    var getReader = function(deferred, scope) {
        var reader = new FileReader();
        reader.onload = onLoad(reader, deferred, scope);
        reader.onerror = onError(reader, deferred, scope);
        reader.onprogress = onProgress(reader, scope);
        return reader;
    };

    var readAsDataUrl = function(file, scope) {
        var deferred = $q.defer();

        var reader = getReader(deferred, scope);
        reader.readAsDataURL(file);

        return deferred.promise;
    };

    return {
        fetchAllDresses: fetchAllDresses,
        fetchDressById: fetchDressById,
        fetchCategories: fetchCategories,
        fetchManufacturers: fetchManufacturers,
        fetchColors: fetchColors,
        fetchSizes: fetchSizes,
        fetchTypes: fetchTypes,
        addDress: addDress,
        editDress: editDress,
        removeDress: removeDress,
        getDress: getDress,
        readAsDataUrl: readAsDataUrl,
        addDressToBag: addDressToBag,
        getUserBag: getUserBag,
        deleteDressFromUserBag: deleteDressFromUserBag
    };

    function fetchAllDresses() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function fetchDressById(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                    console.log("this is servise ressponce")
                    dress = response.data;
                },
                function (errResponse) {
                    console.error('Error while fetching user by id');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchCategories() {
        var deferred = $q.defer();
        $http.get('category')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching categories');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchManufacturers() {
        var deferred = $q.defer();
        $http.get('manufacturer')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching manufacturers');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchColors() {
        var deferred = $q.defer();
        $http.get('color')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching colors');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchSizes() {
        var deferred = $q.defer();
        $http.get('size')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching sizes');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchTypes() {
        var deferred = $q.defer();
        $http.get('type')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching sizes');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getDress() {
        console.log("service get dress "+dress);
        return dress;
    }

    function addDress(dress, imageFiles) {
        var deferred = $q.defer();
        Upload.upload({
            url: 'dress_img',
            file: imageFiles[0]
        }).success(function (data, status, headers, config) {
            $http.post(REST_SERVICE_URI, dress)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while creating User');
                        deferred.reject(errResponse);
                    }
                );
            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
        });
        return deferred.promise;
    }

    function editDress(id, dress) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, dress)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function removeDress(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function addDressToBag(dress, quantity) {
        var deferred = $q.defer();
        var dressAndQuantity = {
            dress: dress,
            quantity: quantity
        }
        $http.post('addDressToBag', dressAndQuantity)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while adding dress to bag: ' + errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getUserBag() {
        var deferred = $q.defer();
        $http.get('getUserBag')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while adding dress to bag: ' + errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteDressFromUserBag(dressAndQuantity) {
        var deferred = $q.defer();
        $http.post('deleteDressFromUserBag', dressAndQuantity)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while adding dress to bag: ' + errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
