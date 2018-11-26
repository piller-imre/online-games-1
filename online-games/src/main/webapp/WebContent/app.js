var app = angular.module('app', [ 
	'ui.router', 
	'app.directives', 
	'app.logic', 
	'app.controllers', 
	'ngMessages', 
	'ngStorage', 
	'ngMockE2E'
	]);


// LIST OF MODULES
var directives = angular.module('app.directives', []);
var logic = angular.module('app.logic', []);
var controllers = angular.module('app.controllers', []);
//var services = angular.module('app.services', []);


// ROUTING
app.config([ '$urlRouterProvider', '$stateProvider',
		function($urlRouterProvider, $stateProvider) {

			$stateProvider
			.state('login', {
				url : '/login',
				templateUrl : 'pages/login/login.html',
				controller: 'LoginController'
			})

			.state('register', {
				url : '/register',
				templateUrl : 'pages/register/register.html',
				controller : 'RegisterController'
			})

			.state('welcome', {
				url : '/welcome',
				templateUrl : 'pages/welcome/welcome.html'
			})

			.state('choose-game', {
				url : '/choose-game',
				templateUrl : 'pages/choose-game/choose-game.html',
				controller: 'ChooseGameController'
			})

			.state('game-play', {
				url : '/game-play',
				templateUrl : 'pages/game-play/game-play.html',
				controller : 'GamePlayController',
				params : {
					match: null
				}
			})

			.state('ranks', {
				url : '/ranks',
				templateUrl : 'pages/ranks/ranks.html',
				controller : 'RanksController'
			})

			.state('credits', {
				url : '/credits',
				templateUrl : 'pages/credits/credits.html'
			});

			$urlRouterProvider.otherwise('/pages/login/login.html');
		} ]);

// USER AUTHORISATION
app.run(function($rootScope, $http, $location, $localStorage) {
	
	$rootScope.baseUrl = 'http://localhost:9000/online-games';
	
	// keep user logged in after page refresh
	if ($localStorage.currentUser) {
		$http.defaults.headers.common.Authorization = 'Bearer '
				+ $localStorage.currentUser.token;
	}

	// redirect to login page if not logged in and trying to access a restricted
	// page
	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		var publicPages = [ '/login', '/register', '/credits' ];
		var restrictedPage = publicPages.indexOf($location.path()) === -1;
		if (restrictedPage && !$localStorage.currentUser) {
			$location.path('/login');
		}
	});
});
