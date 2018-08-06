// LOGIN CONTROLLER
controllers.controller('LoginController', [
	'$location',
	'AuthenticationService',
	'$scope',
	function($location, AuthenticationService, $scope) {
		var vm = $scope;

		initController();

        function initController() {
            AuthenticationService.logout();		// reset login status
        };
		
        vm.login = function() {
            vm.loading = true;
            AuthenticationService.login($scope.username, vm.password, function (result) {
                if (result === true) {
                    $location.path('/welcome');		// redirect to welcome page
                } else {
                    vm.error = 'Username or password is incorrect';
                    vm.loading = false;
                }
            });
		};
}]);
