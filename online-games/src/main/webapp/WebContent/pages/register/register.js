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
				// console.log('result: ' + response.data);
				if(response.data > 0){
					vm.errorText = "Siker�lt, �gyes volt�l! Jelentkezz be a m�k�hoz!";
				} else if(response.data == 0){
					vm.errorText = "A n�v �s/vagy emailc�m foglalt. V�lassz egy men�bbet!";
				} else {
					vm.errorText = "Helytelen adatok, ne helytelenkedj, te kis huncut!";
				}
			});
		}
	}
	
}]);