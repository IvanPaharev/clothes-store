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
        $http.get('dress')
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
        $http.get('dress/type/' + type)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchDressesByCriteria(criteria) {
        var deferred = $q.defer();
        $http.post('dress/dressesByCriteria', criteria)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }
    
    function getQueryCount(criteria) {
        var deferred = $q.defer();
        $http.post('dress/queryCount', criteria)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.resolve(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchDressById(id) {
        var deferred = $q.defer();
        $http.get('dress/'+id)
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

    function addDress(dress, mainImageFile, imageFiles) {
        var deferred = $q.defer();
        var counter = 0;
        if (mainImageFile !== null) {
            Upload.upload({
                url: 'dressImage/main',
                file: mainImageFile
            }).success(function () {
                if (imageFiles !== null) {
                    for (var i = 0; i < imageFiles.length; i++) {
                        Upload.upload({
                            url: 'dressImage/other/' + i,
                            file: imageFiles[i]
                        }).success(function (data, status, headers, config) {
                            counter++;
                            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                            console.log(counter);
                            console.log(imageFiles.length);
                            if (counter === imageFiles.length) {
                                uploadDress(dress, deferred);
                            }
                        })
                    }
                } else {
                    uploadDress(dress, deferred);
                }
            });
        } else if (imageFiles !== null) {
            for (var i = 0; i < imageFiles.length; i++) {
                Upload.upload({
                    url: 'dressImage/other/' + i,
                    file: imageFiles[i]
                }).success(function (data, status, headers, config) {
                    counter++;
                    console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                    console.log(counter);
                    console.log(imageFiles.length);
                    if (counter === imageFiles.length) {
                        uploadDress(dress, deferred);
                    }
                })
            }
        } else {
            uploadDress(dress, deferred);
        }
        return deferred.promise;
    }

    function uploadDress(dress, deferred) {
        $http.post('dress', dress)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating dress');
                    deferred.resolve(errResponse);
                }
            );
    }

    function editDress(dress, imageFiles, mainImageFile) {
        var deferred = $q.defer();
        var counter = 0;
        if (mainImageFile !== null) {
            Upload.upload({
                url: 'dressImage/main',
                file: mainImageFile
            }).success(function () {
                if (imageFiles !== null) {
                    for (var i = 0; i < imageFiles.length; i++) {
                        Upload.upload({
                            url: 'dressImage/other/' + i,
                            file: imageFiles[i]
                        }).success(function (data, status, headers, config) {
                            counter++;
                            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                            console.log(counter);
                            console.log(imageFiles.length);
                            if (counter === imageFiles.length) {
                                $http.put('dress', dress)
                                    .then(
                                        function (response) {
                                            deferred.resolve(response.data);
                                        },
                                        function(errResponse){
                                            console.error('Error while updating dress');
                                            deferred.resolve(errResponse);
                                        }
                                    );
                            }
                        })
                    }
                } else {
                    $http.put('dress', dress)
                        .then(
                            function (response) {
                                deferred.resolve(response.data);
                            },
                            function(errResponse){
                                console.error('Error while updating dress');
                                deferred.resolve(errResponse);
                            }
                        );
                }
            });
        } else if (imageFiles !== null) {
            for (var i = 0; i < imageFiles.length; i++) {
                Upload.upload({
                    url: 'dressImage/other/' + i,
                    file: imageFiles[i]
                }).success(function (data, status, headers, config) {
                    counter++;
                    console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                    console.log(counter);
                    console.log(imageFiles.length);
                    if (counter === imageFiles.length) {
                        $http.put('dress', dress)
                            .then(
                                function (response) {
                                    deferred.resolve(response.data);
                                },
                                function(errResponse){
                                    console.error('Error while updating dress');
                                    deferred.resolve(errResponse);
                                }
                            );
                    }
                })
            }
        } else {
            $http.put('dress', dress)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while updating dress');
                        deferred.resolve(errResponse);
                    }
                );
        }
        return deferred.promise;
    }

    function removeDress(id) {
        var deferred = $q.defer();
        $http.delete('dress/'+id)
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
        $http.post('userOrder/bag', orderDetail)
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
        $http.get('userOrder/bag')
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

    function deleteDressFromUserBag(orderDetailPK) {
        var deferred = $q.defer();
        $http.put('userOrder/bag', orderDetailPK)
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
        $http.get('dress/currencyInfo')
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
