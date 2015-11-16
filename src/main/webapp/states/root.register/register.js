(function(window, angular){
    'use strict';
    
    function registerConfig($stateProvider){
        $stateProvider.state('root.register', {
            url: '/register',
            templateUrl: 'states/root.register/register.html',
            controller: 'registerController as registerState'
        });
    }
    registerConfig.$inject = ['$stateProvider'];

    function registerRun(){}

    function registerController(authenticationService, $state){
        // Private variables
        var self = this;

        // Private methods


        // Public variables
        self.credentials = null;

        // Public methods

        // Initialization
        authenticationService.activate();
    }
    registerController.$inject = ['authenticationService', '$state'];

    angular.module('zen.states.register', [
        'zen.services'
    ])
    .config(registerConfig)
    .run(registerRun)
    .controller('registerController', registerController);
})(window, window.angular);
