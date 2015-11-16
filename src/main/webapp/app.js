(function(window, angular, FastClick){
    'use strict';
    
    function zenConfig($urlRouterProvider, $locationProvider){
        $urlRouterProvider.otherwise('/');

        $locationProvider.html5Mode({
          enabled: false,
          requireBase: false
        });

        $locationProvider.hashPrefix('!');
    }

    zenConfig.$inject=["$urlRouterProvider", '$locationProvider'];

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
        'zen.states.root',
        'zen.states.home',
        'zen.states.login',
        'zen.states.register',
        'zen.states.settings',
        'zen.states.store',
        'zen.states.basket',
        'zen.states.myActivities',
        'zen.states.products',
        'zen.states.users',
        'zen.states.activities',
        'zen.states.rooms',
        'zen.states.accessories'
    ])
    .config(zenConfig)
    .run(zenRun);

})(window, window.angular, window.FastClick);
