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
        fetchDressesByType: fetchDressesByType,
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
        deleteDressFromUserBag: deleteDressFromUserBag,
        fetchHomeDresses: fetchHomeDresses,
        uploadDress: uploadDress,

        getQueryCount: getQueryCount,
        fetchDressesByCriteria: fetchDressesByCriteria,

        getCurrencyInfo: getCurrencyInfo
    };

    function fetchHomeDresses() {
        return null;
    }

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

    function fetchDressesByType(type) {
        var deferred = $q.defer();
        $http.get('dressList/' + type)
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

    function fetchDressesByCriteria(criteria, type) {
        var deferred = $q.defer();
        $http.post('dressesByCriteria/' + type, criteria)
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
    
    function getQueryCount(criteria, type) {
        var deferred = $q.defer();
        $http.post('getQueryCount/' + type, criteria)
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
                    console.error('Error while fetching dress by id');
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

    function addDress(dress, mainImageFile, imageFiles) {
        var deferred = $q.defer();
        var counter = 0;
        imageFiles.unshift(mainImageFile);
        for (var i = 0; i < imageFiles.length; i++) {
            Upload.upload({
                url: 'image/' + i,
                file: imageFiles[i]
            }).success(function (data, status, headers, config) {
                counter++;
                console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                console.log(counter);
                console.log(imageFiles.length);
                if (counter == imageFiles.length) {
                    uploadDress(dress, deferred);
                }
            });
        }
        imageFiles.splice(0, 1);
        return deferred.promise;
    }

    function uploadDress(dress, deferred) {
        $http.post(REST_SERVICE_URI, dress)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating dress');
                    deferred.reject(errResponse);
                }
            );
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

    function addDressToBag(dress, color, size, quantity) {
        var deferred = $q.defer();
        var orderDetail = {
            dress: dress,
            color: color,
            size: size,
            quantity: quantity
        };
        $http.post('addDressToBag', orderDetail)
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

    function deleteDressFromUserBag(orderDetail) {
        var deferred = $q.defer();
        $http.post('deleteDressFromUserBag', orderDetail)
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

    function getCurrencyInfo() {
        var deferred = $q.defer();
        $http.get('http://api.fixer.io/latest?base=USD')
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
}]);
