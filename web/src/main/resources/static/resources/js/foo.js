/**
 * Created by A-one on 06.06.2017.
 */
//         <div>
// <span>Change password:</span>
// <div ng-show="ctrl.isPassErrors" class="alert alert-danger">
//     <p ng-repeat="error in ctrl.errorMessages">{{error.defaultMessage}}</p>
// </div>
// <span>Old password:</span>
// <input type="password" ng-model="ctrl.user.password" class="form-control input-sm" placeholder="Enter your old password"/>
//     <span>New password:</span>
// <input type="password" ng-model="ctrl.newPassword" class="form-control input-sm" placeholder="Enter your new password"/>
//     <span>Confirm new password:</span>
// <input type="password" ng-model="ctrl.confirmPassword" class="form-control input-sm" placeholder="Confirm your new password"/>
//     <button ng-click="ctrl.changePassword()" class="btn btn-success">Change</button>
//     </div>
/*    function changePassword() {
 if (self.newPassword.length >= 5 && self.newPassword.length <= 20) {
 if (self.newPassword === self.confirmPassword) {
 userService.changePassword(self.user, self.newPassword)
 .then(
 function (response) {
 if (response.data) {
 self.isPassErrors = response.data.errors;
 self.errorMessages = response.data.errors;
 }
 if (!response.data) {
 self.user = response;
 self.isPassErrors = false;
 }
 },
 function (errResponse) {
 console.log('Error while auth init: ' + errResponse);
 }
 );
 } else {
 self.isPassErrors = true;
 self.errorMessages = [{defaultMessage: "Passwords doesn't match!"}]
 }
 } else {
 self.isPassErrors = true;
 self.errorMessages = [{defaultMessage: "Passwords length must be from 6 to 20!"}]
 }

 }*/

/*    function changePassword(user, newPassword) {
 var scope = {
 authenticated: false
 };
 var credentials = {
 username: user.email,
 password: user.password
 };
 var deferred = $q.defer();
 authenticate(scope, credentials, function() {
 if (scope.authenticated) {
 user.password = newPassword;
 $http.post('user/password', user)
 .then(
 function(response) {
 deferred.resolve(response.data);
 },
 function (errResponse) {
 console.error('Error while auth initialization: ' + errResponse);
 deferred.resolve(errResponse)
 }
 );
 } else {
 deferred.resolve({
 data: {
 errors: [{defaultMessage: "You input incorrect old password!"}]
 }
 });
 }
 });
 return deferred.promise;
 }*/

/*    @RequestMapping(value = "/password", method = RequestMethod.POST)
 public ResponseEntity<User> changePassword(@RequestBody User user) {
 user.setPassword(passwordEncoder.encode(user.getPassword()));
 userService.update(user);
 user.setPassword("");
 return new ResponseEntity<>(user, HttpStatus.OK);
 }*/