angular.module('myApp').controller('userController', ['$rootScope', '$location', 'userService', function($rootScope, $location, userService) {
    var self = this;

    self.user = {};
    self.users = [];
    self.newPassword = null;
    self.confirmPassword = null;
    self.isInfoErrors = false;
    self.isPassErrors = false;
    self.errorMessages = [];

    self.updateUser = updateUser;
    self.fetchUser = fetchUser;
    self.getUsers = getUsers;

    function fetchUser() {
        userService.fetchUser()
            .then(
                function(u) {
                    self.user = u;
                },
                function(errResponse){
                    console.error('Error while fetching User ' + errResponse);
                }
            );
    }

    function updateUser() {
        userService.updateUser(self.user)
            .then(
                function (response) {
                    if (response.data) {
                        self.isInfoErrors = response.data.errors;
                        self.errorMessages = response.data.errors;
                    }
                    if (!response.data) {
                        self.user = response;
                        self.isInfoErrors = false;
                    }
                },
                function (errResponse) {
                    console.log('Error while auth init: ' + errResponse);
                }
            );
    }

    function getUsers() {
        userService.getUsers()
            .then(
                function (users) {
                    self.users = users;
                },
                function(errResponse){
                    console.error('Error while fetching Users ' + errResponse);
                }
            );
    }
}]);