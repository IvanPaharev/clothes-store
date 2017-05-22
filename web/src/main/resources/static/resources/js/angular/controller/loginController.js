angular.module('myApp').controller('loginController', ['$rootScope', '$location', 'userService', function($rootScope, $location, userService) {
    var self = this;

    self.credentials = {};

    self.register = function () {
        userService.register(self.user);
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

}]);