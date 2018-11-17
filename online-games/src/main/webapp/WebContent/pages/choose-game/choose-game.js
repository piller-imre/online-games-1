controllers.controller('ChooseGameController', [
	'$scope',
	'$rootScope',
	'$http',
	'$localStorage',
	'$state',
	function($scope, $rootScope, $http, $localStorage, $state) {
		var vm = $scope;
		var baseUrl = $rootScope.baseUrl;
		vm.gameTypes = [];
		vm.newMatch = [];
		vm.newMatchAlertVisible = false;
		vm.isMatchesWaiting = false;
		vm.matchesWaiting = [];
		vm.loading = false;
		vm.errorMsg = "";
		vm.error = null;
		vm.selectedMatchId = null;

        
		initController();

		// vm.$watch(vm.newMatch.options, function(newValue, oldValue){
		// 	console.log('options: ' + newValue);
		// });
		
		function initController() {
            // get gameTypes
			$http.get(baseUrl + '/gametypes')
			.then(function(result){

				// init new match object
				vm.gameTypes = result.data;
				vm.newMatch = {
					userid: $localStorage.currentUser.userid,
					username: $localStorage.currentUser.username,
					gameTypeId: vm.gameTypes[0].gameTypeId,
					options: []
				};
				vm.setOptions();
				
				// init matches waiting
				getMatchesWaiting();
			});

		};
		
		vm.createChallange = function() {
			vm.loading = true;
			vm.newMatch.options = [];
			angular.forEach(vm.options, function(op){
				if(op.marked){
					vm.newMatch.options.push(op.id);
				}
			});
			// console.log(vm.newMatch);

			$http.post(baseUrl + "/match", vm.newMatch)
			.then(function(result){
				
				vm.errorMsg = "";
				vm.error = result.data;
				
				if(vm.error == 1){
					vm.errorMsg = "A kihívást szétküldtük.";
				} else if (vm.error == -1){
					vm.errorMsg = "Ne légy mohó, már van egy várakozó kihívásod!";
				} else {
					vm.errorMsg = "Nem sikerült, próbáld újra!";
				}
				vm.newMatchAlertVisible = true;
			});

			vm.loading = false;
		}

		vm.setOptions = function() {
			vm.newMatch.options = [];
			for(var i = 0; i < vm.gameTypes.length; i++){
				if(vm.gameTypes[i].gameTypeId == vm.newMatch.gameTypeId){
					vm.options = vm.gameTypes[i].options;
					break;
				}
			}
			angular.forEach(vm.options, function(op){
				op.marked = false;
			});
		}
			
		function getMatchesWaiting(){
			$http.get(baseUrl + '/match')
			.then(function(result){
				vm.matchesWaiting = result.data;

				if (vm.matchesWaiting == null || vm.matchesWaiting.length == 0) {
					vm.isMatchesWaiting = false;
				} else {
					vm.isMatchesWaiting = true;

					// convert string to int array
					vm.matchesWaiting.forEach(function(match){
						if(match.options != null){
							var op = match.options;
							op = op.substring(1, op.length-1);
							op = op.split(',').map(function(item) {
								return parseInt(item, 10);
							});
							match.options = op;
						}
					});
					
					// get details
					vm.matchesWaiting.forEach(function(match){
						match.gameType = angular.copy(vm.gameTypes.find(function(type){
									return type.gameTypeId == match.gameTypeId;
								}));
						match.gameType.options = match.gameType.options.filter(function(op){
							return match.options.includes(op.id);
						});
					});

				}
			});
		}
		vm.getMatchesWaiting = getMatchesWaiting;

		vm.deleteChallange = function() {
			var userid = $localStorage.currentUser.userid;
			var match = vm.matchesWaiting.find(function(match){
				return match.userid == userid;
			});

			$http.delete(baseUrl + '/match/' + match.id)
			.then(function(result){
				console.log(result);
			});
		}

		function acceptChallange() {
			console.log('vm.selectedMatchId: ' + vm.selectedMatchId);
			var data = {
				userid: $localStorage.currentUser.userid,
				matchId: vm.selectedMatchId
			};

			$http.post(baseUrl + '/match/start', data)
			.then(function(result){
				var matchId = result.data;
				if(matchId > 0){
					$state.go('game-play');
				} else {
					vm.startErrorMsg = "Erorr, erorr, erorr";
					vm.startError = true;
				}
			});
		}

		vm.acceptChallange = acceptChallange;
		
		function setSelected(matchId, index) {
			vm.selectedMatchId = matchId;
			vm.selectedRow = index;
		};

		vm.setSelected = setSelected;
	}
]);
