controllers.controller('RegisterController', [ function(){
	var vm = $scope;
	var url = $rootScope.baseUrl;
	
	vm.register = function() {
		if(vm.password == vm.passwordConfirmed){
			var user = {
				username: vm.username,
				password: vm.password,
				passwordConfirmed: passwordConfirmed,
				email: email
			}
			
			$http.post(url + '/register', {
				username: vm.username,
				password: vm.password,
				passwordConfirmed: passwordConfirmed,
				email: email})
			.then(function (response) {
				console.log('Response: ' + response);
			});
		}
	}
	
}]);