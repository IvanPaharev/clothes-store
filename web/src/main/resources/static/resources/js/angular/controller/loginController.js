angular.module('myApp').controller('loginController', ['$rootScope', '$location', 'userService', function($rootScope, $location, userService) {
    var self = this;

    self.credentials = {};
    self.confirmPassword = null;
    self.isPasswordsNotMatch = false;
    self.isPasswordNotCorrect = false;
    self.isErrors = false;
    self.isRegistered = false;
    self.user = {
        email: null,
        password: null
    };
    self.errorMessages = [];

    self.register = function () {
        if (self.user.password === self.confirmPassword) {
            self.isPasswordsNotMatch = false;
            if (self.user.password.length >=6 && self.user.password.length <= 20) {
                self.isPasswordNotCorrect = false;
                userService.register(self.user)
                    .then(
                        function (response) {
                            if (response.data) {
                                self.isErrors = response.data.errors;
                                self.errorMessages = response.data.errors;
                                self.isRegistered = false;
                            }
                            if (!response.data) {
                                self.isRegistered = true;
                                self.isErrors = false;
                            }
                        },
                        function (errResponse) {
                            console.log('Error while auth init: ' + errResponse);
                        }
                    );
            } else {
                self.isRegistered = false;
                self.isPasswordNotCorrect = true;
            }
        } else {
            self.isRegistered = false;
            self.isPasswordsNotMatch = true;
        }
    };

    self.logout = function() {
        userService.logout($rootScope, $location);
    };

    self.login = function() {
        userService.authenticate($rootScope, self.credentials, function() {
            if ($rootScope.authenticated) {
                $location.path("/");
                $rootScope.user = userService.getUser();
                $rootScope.isAdmin = false;
                for (var i in $rootScope.user.authorities) {
                    if ($rootScope.user.authorities[i].authority == 'admin') {
                        $rootScope.isAdmin = true;
                    }
                }
                self.error = false;
            } else {
                $location.path("/login");
                self.error = true;
            }
        });
    };

    self.initAuthentication = function () {
        userService.initAuthentication($rootScope)
            .then(
                function (user) {
                    $rootScope.user = user;
                    $rootScope.isAdmin = false;
                    for (var i in $rootScope.user.authorities) {
                        if ($rootScope.user.authorities[i].authority == 'admin') {
                            $rootScope.isAdmin = true;
                        }
                    }
                },
                function (errResponse) {
                    console.log('Error while auth init: ' + errResponse);
                }
            );
    };

    self.log = function () {
        console.log($rootScope.user);
    }

}]);