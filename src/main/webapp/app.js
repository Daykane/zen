(function(window, angular, FastClick){
    'use strict';
    
    function zenConfig($urlRouterProvider, $locationProvider, $resourceProvider){
        $urlRouterProvider.otherwise('/');

        $locationProvider.html5Mode({
          enabled: false,
          requireBase: false
        });

        $locationProvider.hashPrefix('!');

        $resourceProvider.defaults.stripTrailingSlashes = false;
    }
    zenConfig.$inject=["$urlRouterProvider", '$locationProvider', '$resourceProvider'];

    function zenRun($state, $rootScope){

        FastClick.attach(document.body);

        $rootScope.$on('$stateChangeStart', function(e, toState) {
            var isRoot = toState.name === 'root';
            if(isRoot) {
                e.preventDefault();
                $state.go('root.home');
            }
        });
    }
    zenRun.$inject=['$state', '$rootScope'];

    angular.module('zen.helpers', [
        'sha1'
    ]);

    angular.module('zen.services', [
        'zen.helpers',
        'zen.api.users'
    ]);

    angular.module('zen', [
        'ipCookie',
        'ui.router',
        'ui.bootstrap',
        'ngResource',
        'zen.states.root',
        'zen.states.home',
        'zen.states.login',
        'zen.states.register',
        'zen.states.settings',
        'zen.states.store',
        'zen.states.cart',
        'zen.states.myActivities',
        'zen.states.products',
        'zen.states.categories',
        'zen.states.users',
        'zen.states.activities',
        'zen.states.events',
        'zen.states.rooms',
        'zen.states.accessories'
    ])
    //.constant('apiUrl', 'http://aiop-alaboureur.rhcloud.com/api/')
    .constant('apiUrl', 'http://localhost:8080/zen/api/')
    .config(zenConfig)
    .run(zenRun);

})(window, window.angular, window.FastClick);
