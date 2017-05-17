angular.module('myApp').controller('userController', ['$rootScope', '$location', 'userService', function($rootScope, $location, userService) {
    var self = this;

    self.user = {};

    self.updateUser = updateUser;
    self.fetchUser = fetchUser;

    function fetchUser() {
        userService.fetchUser()
            .then(
                function(u) {
                    self.user = u;
                },
                function(errResponse){
                    console.error('Error while fetching Users ' + errResponse);
                }
            );
    }

    function updateUser() {
        userService.updateUser(self.user);
    }
}]);