//TODO

(function(window, angular, _){
    'use strict';

    function accountService(authenticationService, $state){
        // Private variables

        // Private methods
        function activate(){}

        function reset(){}

        function logout(){
            authenticationService.reset();
            $state.go('root.home');
        }
        
        function goToSettings(){
            $state.go('root.settings');
        }
        function goToCart(){
            $state.go('root.cart');
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
    
})(window, window.angular, window._);
