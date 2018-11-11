
app.service('AuthenticationService', ['$http', '$location', '$localStorage', '$rootScope',
	function ($http, $location, $localStorage, $rootScope) {
		var vm = this;
		var baseUrl = $rootScope.baseUrl;
		
		vm.login = function(username, password, callback) {
			var data ={ username: username , password: password};
			var url = baseUrl + '/user/authenticate';
			$http.post(url, data)
				.then(function(response){
					console.log('Response: ' + response.data);
					if (response.data.token) {
						$localStorage.currentUser = { 
							userid: response.data.userid, 
							username: response.data.username, 
							token: response.data.token 
						};
						$http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
					callback(true);			//case of success
					} else {
						callback(false);	// case of failure
					}
				});
		}
	
		vm.logout = function() {
			// remove user from local storage and clear http auth header
			delete $localStorage.currentUser;
			$http.defaults.headers.common.Authorization = '';
		}
}]);
