directives.directive('sideBar', ['$localStorage', function($localStorage){
	return {
		templateUrl: "directives/sidebar/sidebar.html",
		link: function(scope){
			var vm = scope;
			vm.currentUser = $localStorage.currentUser;

			vm.$watch(function(){
				return $localStorage.currentUser;
			}, function(newValue, oldValue){
				vm.currentUser = $localStorage.currentUser;
			});
		}
	}
}]);