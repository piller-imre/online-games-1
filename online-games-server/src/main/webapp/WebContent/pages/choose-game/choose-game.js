controllers.controller('ChooseGameController', [
	'$scope',
	'$rootScope',
	'$http',
	'$localStorage',
	function($scope, $rootScope, $http, $localStorage) {
		var vm = $scope;
        var baseUrl = $rootScope.baseUrl;
        
		initController();

        

		// vm.$watch(vm.newMatch.options, function(newValue, oldValue){
		// 	console.log('options: ' + newValue);
		// });
		
		function initController() {
            // get gameTypes, init new match object
			$http.get(baseUrl + '/gametypes')
			.then(function(result){
				vm.gameTypes = result.data;
				vm.newMatch = {
					userid: $localStorage.currentUser.userid,
					username: $localStorage.currentUser.username,
					gameTypeId: vm.gameTypes[0].gameTypeId,
					options: []
				};
				vm.setOptions();
			});
		};
		
		vm.createChallange = function() {
			vm.loading = true;
			console.log(vm.newMatch);


			vm.loading = false;
		};

		vm.setOptions = function() {
			vm.newMatch.options = [];
			for(var i = 0; i < vm.gameTypes.length; i++){
				if(vm.gameTypes[i].gameTypeId == vm.newMatch.gameTypeId){
					vm.options = vm.gameTypes[i].options;
					break;
				}
			}
			angular.forEach(vm.options, function(op){
				vm.newMatch.options[op.name] = false;
			});
		}
	}
]);
