controllers.controller('ChooseGameController', [
	'$scope',
	'$rootScope',
	'$http',
	'$localStorage',
	function($scope, $rootScope, $http, $localStorage) {
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

				if (vm.matchesWaiting == null) {
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
						match.gameType = vm.gameTypes.find(function(type){
							return type.gameTypeId == match.gameTypeId;
						});
						match.gameType.options = match.gameType.options.filter(function(op){
							return match.options.includes(op.id);
						});
					});

				}
			});
		}
		vm.getMatchesWaiting = getMatchesWaiting;

		vm.deleteChallange = function() {
			var match = vm.matchesWaiting.find(function(match){
				return match.userid == $localStorage.currentUser.userid;
			});

			$http.delete(baseUrl + '/match/' + match.id)
			.then(function(result){
				console.log(result);
			});
		}

		vm.acceptChallange = function() {

		}
		
		vm.setSelected = function(match, index) {
			vm.selectedMatchId = match.id;
			vm.selectedRow = index;
		};
	}
]);

/*

{
"id": 1,
"userid": 1,
"username": "test",
"gameTypeId": 2,
"optionIds": "[6, 7]"
}


{
	"gameTypeId": 1,
	"gameTypeName": "Amőba",
	"options": [
	  {
		"id": 1,
		"name": "Véletlenszerű csapdák",
		"description": "A pályán véletleszerűen elhelyez 50 csapdát. Ha valaki rá akar tenni egy karaktert, a csapda aktiválódik, a karaktert nem helyezi el, és a következő játékos jön."
	  },
	  {
		"id": 3,
		"name": "Kirakható csapdák",
		"description": "A játék kezdetekor mindkét játékos kap 10-10 csapdát, amelyet játék közben a saját körében kitehet a karaktere helyett. A karaktert utána egyik játékos sem látja."
	  },
	  {
		"id": 4,
		"name": "Eltűnő karakterek",
		"description": "A játék minden körben véletlenszerűen üresre állít egy mezőt, akár csapda, akár fal, akár karakter van rajta."
	  },
	  {
		"id": 2,
		"name": "Tiltott mezők",
		"description": "A pályán véletlenszerűen elhelyez 50 tiltott mezőt, amelyre nem lehet karaktert tenni."
	  }
	]
}
*/
