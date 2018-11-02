
app.service('AuthenticationService', ['$http', '$location', '$localStorage', '$rootScope',
	function ($http, $location, $localStorage, $rootScope) {
		var vm = this;
		var baseUrl = $rootScope.baseUrl;
		
		vm.login = function(username, password, callback) {
//			var data ={ username: username , password: password};
			var data = "username=" + username + "&password=" + password;
			var url = baseUrl + '/authenticate';
//			$http.post(url, jsonMsg)
			$http({
			    method: 'POST',
			    url: url,
			    data: data,
			    headers: {
			    	'Content-Type': 'application/x-www-form-urlencoded',
			    	'Access-Control-Allow-Origin': '*'
			    		}
			})
//			$.post(url, data, function (response, status) {
//					console.log('Response: ' + response);
//					// login successful if there's a token in the response
//					if (response.data.token) {
//						// store username and token in local storage to keep user logged in between page refreshes
//						$localStorage.currentUser = { username: username, token: response.token };
//	
//						// add jwt token to auth header for all requests made by the $http service
//						$http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
//	
//						// execute callback with true to indicate successful login
//						callback(true);
//					} else {
//						// execute callback with false to indicate failed login
//						callback(false);
//					}
//				});
		}
	
		vm.logout = function() {
			// remove user from local storage and clear http auth header
			delete $localStorage.currentUser;
			$http.defaults.headers.common.Authorization = '';
		}
}]);
