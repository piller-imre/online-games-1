var app = angular.module('app', [
	'ui.router',
	'app.directives',
	'app.controllers'
	]);


// LIST OF MODULES
var directives = angular.module('app.directives', []);
var controllers = angular.module('app.controllers', []);
var services = angular.module('app.services', []);


// ROUTING
app.config(['$urlRouterProvider', '$stateProvider', function($urlRouterProvider, $stateProvider) {
	  
    $stateProvider
	    .state('login', {
	        url: '/login',
	        templateUrl: 'pages/login/login.html',
	        controller: 'LoginController'
	    })
	    
	    .state('signup', {
	        url: '/signup',
	        templateUrl: 'pages/signup/signup.html',
	        controller: 'SignupController'
	    })
	    
        .state('welcome', {
            url: '/welcome',
            templateUrl: 'pages/welcome/welcome.html'
        })

        .state('choose-game', {
            url: '/choose-game',
            templateUrl: 'pages/choose-game/choose-game.html'       
        })
    
	    .state('game-play', {
	        url: '/game-play',
	        templateUrl: 'pages/game-play/game-play.html'
	    })
	    
	    .state('ranks', {
	        url: '/ranks',
	        templateUrl: 'pages/ranks/ranks.html'
	    })
	    
	    .state('credits', {
	        url: '/credits',
	        templateUrl: 'pages/credits/credits.html'
	    });
    
    $urlRouterProvider.otherwise('/pages/login/login.html');
}]);


// MANAGE LOGIN
function run($rootScope, $http, $location, $localStorage) {
    // keep user logged in after page refresh
    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    // redirect to login page if not logged in and trying to access a restricted page
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/login'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        if (restrictedPage && !$localStorage.currentUser) {
            $location.path('/login');
        }
    });
}



// FAKE BACKGROUND
app.run(setupFakeBackend);

// setup fake backend for backend-less development
function setupFakeBackend($httpBackend) {
var testUser = { username: 'test', password: 'test', firstName: 'Test', lastName: 'User' };

// fake authenticate api end point
$httpBackend.whenPOST('/api/authenticate').respond(function (method, url, data) {
    // get parameters from post request
    var params = angular.fromJson(data);

    // check user credentials and return fake jwt token if valid
    if (params.username === testUser.username && params.password === testUser.password) {
        return [200, { token: 'fake-jwt-token' }, {}];
    } else {
        return [200, {}, {}];
    }
});

// pass through any urls not handled above so static files are served correctly
$httpBackend.whenGET(/^\w+.*/).passThrough();
}