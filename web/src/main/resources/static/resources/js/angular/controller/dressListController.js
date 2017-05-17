'use strict';

angular.module('myApp').controller('dressListController', ['$scope', '$location', '$routeParams', 'dressService', 'Upload', '$timeout',
    function($scope, $location, $routeParams, dressService, Upload, $timeout) {



    var self = this;
    self.dress = {
        id: null,
        manufacturer: {},
        type: {},
        category: {},
        description: {
            dressId: null,
            english: null,
            russian:null
        },
        price: null,
        amount: null,
        imageSource: null,
        releaseDate: null,
        orderDetailSet: null,
        sizeSet: [],
        colorSet: [],
        dressImageSet: []
    };
    self.dresses=[];
    self.categories=[];
    self.types=[];
    self.manufacturers=[];
    self.colors=[];
    self.sizes=[];
    self.imageFiles=[];
    self.mainImageFile=null;
    self.userBag=[];
    self.homeDresses=[];

    self.dressQuantity=null;
    self.mainImgSrc=null;
    self.imgSrc=[];

    self.fetchDressById = fetchDressById;
    self.addDress = addDress;
    self.fetchDressProperties = fetchDressProperties;
    self.readAsDataUrl = readAsDataUrl;
    self.editDress = editDress;
    self.removeDress = removeDress;
    self.addDressToBag = addDressToBag;
    self.getUserBag = getUserBag;
    self.deleteDressFromUserBag = deleteDressFromUserBag;
    self.fetchHomeDresses = fetchHomeDresses;
    self.validateInput = validateInput;
    self.fetchAllDresses = fetchAllDresses;
    self.fetchDressesByType = fetchDressesByType;

    function fetchHomeDresses() {
        dressService.fetchHomeDresses();
        self.homeDresses.push("resources/images/jackets/nigel_cabourn/1.jpg");
        self.homeDresses.push("resources/images/welcome/1.jpg");
        self.homeDresses.push("resources/images/welcome/2.jpg");
        self.homeDresses.push("resources/images/welcome/3.jpg");
    }

    function fetchAllDresses(){
        dressService.fetchAllDresses()
            .then(
            function(d) {
                self.dresses = d;
            },
            function(errResponse){
                console.error('Error while fetching Users'+errResponse);
            }
        );
    }

    function fetchDressesByType() {
        dressService.fetchDressesByType($routeParams.type)
            .then(
                function(d) {
                    self.dresses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users'+errResponse);
                }
            );
    }

    function fetchDressProperties() {
        if ($routeParams.id) {
            dressService.fetchDressById($routeParams.id)
                .then(
                    function (d) {
                        self.dress = d;
                    },
                    function (errResponse) {
                        console.error('Error while fetching dress by id:' + errResponse.toString());
                    }
                );
        }
        dressService.fetchCategories()
            .then(
                function (c) {
                    self.categories = c;
                },
                function (errResponse) {
                    console.error('Error while fetching categories:' + errResponse.toString());
                }
            );
        dressService.fetchManufacturers()
            .then(
                function (m) {
                    self.manufacturers = m;
                },
                function (errResponse) {
                    console.error('Error while fetching manufacturers:' + errResponse.toString());
                }
            );
        dressService.fetchColors()
            .then(
                function (c) {
                    self.colors = c;
                },
                function (errResponse) {
                    console.error('Error while fetching colors:' + errResponse.toString());
                }
            );
        dressService.fetchSizes()
            .then(
                function (s) {
                    self.sizes = s;
                },
                function (errResponse) {
                    console.error('Error while fetching sizes:' + errResponse.toString());
                }
            );
        dressService.fetchTypes()
            .then(
                function (t) {
                    self.types = t;
                },
                function (errResponse) {
                    console.error('Error while fetching types:' + errResponse.toString());
                }
            );
    }

    function validateInput() {
        if (self.dressQuantity){
            console.log(self.dressQuantity);
        }
    }

    function fetchDressById() {
        dressService.fetchDressById($routeParams.id)
            .then(
                function (d) {
                    self.dress = d;
                    self.sizes = d.sizeSet;
                    self.colors = d.colorSet;
                },
                function (errResponse) {
                    console.error('Error while fetching dress by id:' + errResponse.toString());
                }
            );
    }

    function addDress() {
        dressService.addDress(self.dress, self.mainImageFile, self.imageFiles);
    }

    function editDress(id) {
        dressService.editDress(id, self.dress)
            .then(
                function(){
                    self.dresses = [];
                    fetchAllDresses();
                    self.dresses[id] = self.dress;
                },
                function(errResponse){
                    console.error('Error while updating User'+errResponse);
                }
            );
    }

    function removeDress(id) {
        dressService.removeDress(id)
            .then(
                fetchAllDresses(),
                function(errResponse){
                    console.error('Error while deleting User'+errResponse);
                }
            );
    }

    function readAsDataUrl(file, flag) {
        dressService.readAsDataUrl(file, $scope)
            .then(
                function (result) {
                    if (flag === 'mainImage') {
                        self.mainImgSrc = result;
                    } else {
                        self.imgSrc[flag] = result;
                    }
                }
            );
    }

    function addDressToBag() {
        dressService.addDressToBag(self.dress, self.dressQuantity);
    }

    function getUserBag() {
        dressService.getUserBag()
            .then(
                function (d) {
                    console.log(d);
                    self.userBag = d;
                }
            );
    }

    function deleteDressFromUserBag(dressAndQuantity) {
        dressService.deleteDressFromUserBag(dressAndQuantity);
        getUserBag();
    }

    $scope.$watch('imageFiles', function () {
        $scope.upload($scope.imageFiles);
    });

    $scope.upload = function (imageFiles) {
        if (imageFiles && imageFiles.length) {
            for (var i = 0; i < imageFiles.length; i++) {
                var file = imageFiles[i];
                if (!file.$error) {
                    Upload.upload({
                        url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                        data: {
                            username: $scope.username,
                            file: file
                        }
                    }).then(function (resp) {
                        $timeout(function() {
                            $scope.log = 'file: ' +
                                resp.config.data.file.name +
                                ', Response: ' + JSON.stringify(resp.data) +
                                '\n' + $scope.log;
                        });
                    }, null, function (evt) {
                        var progressPercentage = parseInt(100.0 *
                            evt.loaded / evt.total);
                        $scope.log = 'progress: ' + progressPercentage +
                            '% ' + evt.config.data.file.name + '\n' +
                            $scope.log;
                    });
                }
            }
        }
    };
}]);
