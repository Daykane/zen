(function(window, angular){
    'use strict';

    function rootConfig($stateProvider){
        $stateProvider.state('root', {
            abstract: true,
            templateUrl: 'states/root/root.html',
            controller: 'rootController as rootState'
        });
    }
    rootConfig.$inject = ['$stateProvider'];
	
	function rootRun(){}

    function rootController(authenticationService, $scope){
        // Private variables

        // Private methods

        // Public variables
        
        // Public methods

        // Initialization
        authenticationService.activate();
    }
    rootController.$inject = ['authenticationService', '$scope'];

    angular.module('zen.states.root', [
        'ui.router',
        'zen.services',
        'zen.components.navigation',
        'zen.components.account'
    ])
    .config(rootConfig)
	.run(rootRun)
    .controller('rootController', rootController);

})(window, window.angular);
