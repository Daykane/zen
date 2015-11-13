//TODO

(function(window, angular){
    'use strict';

    function accountService(authenticationService, $state){
        // Private variables

        // Private methods
        function activate(){}

        function reset(){}

        function logout(){
            authenticationService.clearToken();
            $state.go('root.home');
            console.log("home45");
        }
        
        function goToSettings(){
            $state.go('root.settings');
        }

        // Public
        return {
            activate: activate,
            reset: reset,
            logout: logout,
            goToSettings: goToSettings
        };
    }
    accountService.$inject = ['authenticationService', '$state'];

    angular.module('zen.components.account')
    .factory('accountService', accountService);
    
})(window, window.angular);
