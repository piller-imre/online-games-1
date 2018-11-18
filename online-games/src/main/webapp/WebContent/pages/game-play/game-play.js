controllers.controller('GamePlayController', [
    '$scope',
    '$stateParams',
    '$http',
    '$rootScope',
    '$localStorage',
    function($scope, $stateParams, $http, $rootScope, $localStorage){
        var vm = $scope;
		var baseUrl = $rootScope.baseUrl;
        vm.match = null;
        vm.gameType = 0;

        init();

        function init(){
            if($stateParams.match){
                vm.match = $stateParams.match;
            } else {
                $http.get(baseUrl + '/match/start/' + $localStorage.currentUser.userid)
                .then(function(result){
                    vm.match = result.data;
                    if(vm.match == null){
                        vm.gameType = 0;
                        vm.error = true;
                        vm.errorMsg = "Eltûnt a meccs! Hm, fura...";
                    } else {
                        // convert string to int array
                        if(vm.match.options != null){
                            var op = vm.match.options;
                            op = op.substring(1, op.length-1);
                            op = op.split(',').map(function(item) {
                                return parseInt(item, 10);
                            });
                            vm.match.options = op;
                        }

                        vm.gameType = vm.match.gameType.gameTypeId;
                    }
                });
            }
        }

        
    }
]);
