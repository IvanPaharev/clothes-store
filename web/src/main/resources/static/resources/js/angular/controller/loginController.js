angular.module('myApp').controller('loginController', ['$rootScope', '$location', 'authService', function($rootScope, $location, authService) {
    var self = this;

    self.user = {
            "id": null,
            "email": '',
            "password": '',
            "userOrderSet": [],
            "roleSet": [
                {
                    "id": 1,
                    "role": "user"
                }
            ]
        };

    self.credentials = {};

    self.register = function () {
        authService.register(self.user);
        $location.path("/");
    };

    self.logout = function() {
        authService.logout($rootScope);
        $location.path("/");
    };

    self.login = function() {
        authService.authenticate($rootScope, self.credentials, function() {
            if ($rootScope.authenticated) {
                $location.path("/");
                self.error = false;
            } else {
                $location.path("/login");
                self.error = true;
            }
        });
    };

/*    var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    self.register = function () {
        console.error("register");
        console.log("register");
        $http.post('user', self.user);
        $location.path("/");
    };

    self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        });
    };

    authenticate();
    self.credentials = {};
    self.login = function() {
        authenticate(self.credentials, function() {
            if ($rootScope.authenticated) {
                $location.path("/");
                self.error = false;
            } else {
                $location.path("/login");
                self.error = true;
            }
        });
    };*/
}]);