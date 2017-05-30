'use strict';

angular.module('myApp').controller('dressListController', ['$scope', '$location', '$routeParams', 'dressService', 'Upload', '$timeout',
    function($scope, $location, $routeParams, dressService, Upload, $timeout) {



    var self = this;
    self.dress = {
        id: null,
        manufacturer: null,
        type: null,
        category: null,
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
        sizeSet: null,
        colorSet: null,
        dressImageSet: null
    };
    self.dresses=null;
    self.categories=null;
    self.types=null;
    self.manufacturers=null;
    self.colors=null;
    self.sizes=null;
    self.imageFiles=null;
    self.mainImageFile=null;
    self.userBag=null;
    self.homeDresses=null;

    self.color={};
    self.size={};
    self.dressQuantity=null;
    self.mainImgSrc=null;
    self.imgSrc=[];
    self.sizeTypes=['common', 'uk', 'us', 'italy', 'france', 'russian', 'german', 'japan'];
    self.sizeType='common';

    self.currencyInfo=null;
    self.currentCurrency=null;
    self.sorts=[
        {
            name: 'first expensive',
            parameter: 'price',
            asc: false
        },
        {
            name: 'first cheap',
            parameter: 'price',
            asc: true
        },
        {
            name: 'first new',
            parameter: 'releaseDate',
            asc: false
        },
        {
            name: 'first old',
            parameter: 'releaseDate',
            asc: true
        }
    ];
    self.pageSizes=[12, 24, 60, 100];
    self.queryCount=null;
    self.pages=[];
    self.criteria={
        categories:[],
        manufacturers:[],
        priceFrom:0,
        priceTo:99999,
        sort:null,
        pageNumber:1,
        pageSize:12
    };

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

    self.fetchPage = fetchPage;
    self.fetchDressesByCriteria = fetchDressesByCriteria;
    self.toggleCategorySelection = toggleCategorySelection;
    self.toggleManufacturerSelection = toggleManufacturerSelection;

    function fetchHomeDresses() {
        dressService.fetchHomeDresses();
        self.homeDresses = [];
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
        dressService.getCurrencyInfo()
            .then(
                function (c) {
                    self.currencyInfo = c;
                    self.currentCurrency = 1;
                },
                function (errResponse) {
                    console.error('Error while fetching categories:' + errResponse.toString());
                }
            );
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
        self.fetchDressesByCriteria();
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
        dressService.addDressToBag(self.dress, self.color, self.size, self.dressQuantity);
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

    function deleteDressFromUserBag(orderDetail) {
        dressService.deleteDressFromUserBag(orderDetail);
        getUserBag();
    }

    function fetchDressesByCriteria() {
        self.criteria.pageNumber = 1;
        dressService.getQueryCount(self.criteria, $routeParams.type)
            .then(
                function (c) {
                    var i = 0;
                    self.pages = [];

                    while (i * self.criteria.pageSize < c) {
                        self.pages.push(i + 1);
                        i++;
                    }
                    self.queryCount = c;
                },
                function (errResponse) {
                    console.error('Error while fetching query count:' + errResponse.toString());
                }
            );
        dressService.fetchDressesByCriteria(self.criteria, $routeParams.type)
            .then(
                function(d) {
                    self.dresses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users'+errResponse);
                }
            );
    }

    function fetchPage(index) {
        self.criteria.pageNumber = index;
        dressService.fetchDressesByCriteria(self.criteria, $routeParams.type)
            .then(
                function(d) {
                    self.dresses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users'+errResponse);
                }
            );
    }

    function toggleCategorySelection(category) {
        var idx = self.criteria.categories.indexOf(category);
        if (idx > -1) {
            self.criteria.categories.splice(idx, 1);
        }
        else {
            self.criteria.categories.push(category);
        }
        self.fetchDressesByCriteria();
    }

    function toggleManufacturerSelection(manufacturer) {
        var idx = self.criteria.manufacturers.indexOf(manufacturer);
        if (idx > -1) {
            self.criteria.manufacturers.splice(idx, 1);
        }
        else {
            self.criteria.manufacturers.push(manufacturer);
        }
        self.fetchDressesByCriteria();
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
