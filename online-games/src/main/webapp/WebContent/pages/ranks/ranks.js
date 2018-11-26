controllers.controller('RanksController', [
    '$scope',
    '$rootScope',
    '$localStorage',
    '$http',
    function($scope, $rootScope, $localStorage, $http){
        var vm = $scope;
        var baseUrl = $rootScope.baseUrl;
        
        vm.tab = 0;
        vm.gameIndex = 0;

        
        $http.get(baseUrl + '/stats/' + $localStorage.currentUser.userid)
        .then(function(result){
            console.log(result.data);
        });
        
        vm.setGameIndex = function (index){
            vm.gameIndex = index;
        }
    }
]);

/*
        vm.globalStats = {
            allMatches: 320,
            allMatchesbyGameType: [
                {
                    gameType: "Amőba",
                    matches: 300
                },
                {
                    gameType: "Type2",
                    matches: 10
                },
                {
                    gameType: "Type3",
                    matches: 10
                },
            ],
            globalRanks: [
                {
                    userid: 11,
                    username: "eleven",
                    allMatches: 200,
                    winMatches: 190,
                },
                {
                    userid: 12,
                    username: "twelve",
                    allMatches: 120,
                    winMatches: 110,
                }
            ]
        }
        
        vm.statsByGameType  = [
            {
                gameType: "Amőba",
                results: [
                    {
                        userid: 11,
                        username: "eleven",
                        allMatches: 50,
                        winMatches: 25
                    },
                    {
                        userid: 12,
                        username: "twelve",
                        allMatches: 70,
                        winMatches: 20
                    }
                ]
            },
            {
                gameType: "type2",
                results: [
                    {
                        userid: 11,
                        username: "eleven",
                        allMatches: 45,
                        winMatches: 2
                    },
                    {
                        userid: 12,
                        username: "twelve",
                        allMatches: 70,
                        winMatches: 2
                    }
                ]
            },
            {
                gameType: "type3",
                results: [
                    {
                        userid: 11,
                        username: "eleven",
                        allMatches: 30,
                        winMatches: 30
                    },
                    {
                        userid: 12,
                        username: "twelve",
                        allMatches: 30,
                        winMatches: 30
                    }
                ]
            }
        ];

        vm.personalStats = {
            allMatches: 134,
            winMatches: 99,
            statsByGameType: [
                {
                    gameType: "Amőba",
                    allMatches: 50,
                    winMatches: 25,
                    rank: 1
                },
                {
                    gameType: "type2",
                    allMatches: 70,
                    winMatches: 2,
                    rank: 34
                },
                {
                    gameType: "type3",
                    allMatches: 30,
                    winMatches: 30,
                    rank: 10
                }
            ]
        };
        */

        // vm.$watch('tab', function(newValue, oldValue){
        //     console.log(newValue);
        // });