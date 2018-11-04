controllers.controller('RegisterController', ['$scope', '$rootScope', '$http', function($scope, $rootScope, $http){
	var vm = $scope;
	var url = $rootScope.baseUrl;
	vm.errorText = "";
	
	vm.register = function() {
		if(vm.password == vm.passwordConfirmed){
			var user = {
				username: vm.username,
				password: vm.password,
				passwordConfirmed: vm.passwordConfirmed,
				email: vm.email
			}
			
			$http.post(url + '/user', user)
			.then(function (response) {
				console.log('result: ' + response.data);
				if(response.data > 0){
					vm.errorText = "Sikerült, ügyes voltál! Jelentkezz be a mókához!";
				} else if(response.data == 0){
					vm.errorText = "A név és/vagy emailcím foglalt. Válassz egy menõbbet!";
				} else {
					vm.errorText = "Helytelen adatok, ne helytelenkedj, te kis huncut!";
				}
			});
		}
	}
	
}]);