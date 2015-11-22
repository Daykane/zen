//TODO

(function(window, angular){
    'use strict';

    function accountDirective(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/account/account.html',
            scope: false,
            controller: accountController
        };
    }

    function accountController($scope, accountService, authenticationService){
        // Private variables

        // Private methods

        // Public variables
        $scope.authentication = authenticationService;
        $scope.account = accountService;

        $scope.nbArticles = authenticationService.cart.length;
        $scope.currentUserName=authenticationService.getCurrentUser().firstName+" "+authenticationService.getCurrentUser().lastName;

        // Public methods

        // Initialization
        accountService.activate();
    }
    accountController.$inject = ['$scope', 'accountService', 'authenticationService'];

    angular.module('zen.components.account', [
        'ui.router',
        'zen.services'
    ])
    .directive('zenAccount', accountDirective);

})(window, window.angular);
