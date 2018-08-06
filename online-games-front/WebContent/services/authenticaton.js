
app.service('AuthenticationService', ['$http', '$location', '$localStorage', 
	function ($http, $location, $localStorage) {
		var vm = this;
		
		vm.login = function(username, password, callback) {
			$http.post('/authenticate', { username: username, password: password })
				.then(function (response) {
					console.log(response);
					// login successful if there's a token in the response
					if (response.data.token) {
						// store username and token in local storage to keep user logged in between page refreshes
						$localStorage.currentUser = { username: username, token: response.token };
	
						// add jwt token to auth header for all requests made by the $http service
						$http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
	
						// execute callback with true to indicate successful login
						callback(true);
					} else {
						// execute callback with false to indicate failed login
						callback(false);
					}
				});
		}
	
		vm.logout = function() {
			// remove user from local storage and clear http auth header
			delete $localStorage.currentUser;
			$http.defaults.headers.common.Authorization = '';
		}
}]);
