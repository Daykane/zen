(function(window, angular){
    'use strict';
    
    function loginConfig($stateProvider){
        $stateProvider.state('root.login', {
            url: '/login',
            templateUrl: 'states/root.login/login.html',
            controller: 'loginController as loginState'
        });
    }
    loginConfig.$inject = ['$stateProvider'];

    function loginRun(){}

    function loginController(authenticationService, $state, $scope){
        // Private variables
        //var self = this;
    	$scope.email="";
    	$scope.password="";
    	$scope.login= function(){
    		authenticationService.login($scope.email, $scope.password);
    	}

        // Initialization
        authenticationService.activate();
    }
    loginController.$inject = ['authenticationService', '$state', '$scope'];

    angular.module('zen.states.login', [
        'zen.services'
    ])
    .config(loginConfig)
    .run(loginRun)
    .controller('loginController', loginController);
})(window, window.angular);
