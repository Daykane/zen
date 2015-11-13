(function(window, angular){
    'use strict';
    function navigation(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/navigation/navigation.html',
            scope: {},
            controller: navigationController
        };
    }

    function navigationController($scope, navigationService, authenticationService){
        // Private variables

        // Private methods

        // Public variables
        $scope.navigation = navigationService;
        $scope.authentication = authenticationService;
        // Public methods

        // Init
        navigationService.activate();
        authenticationService.activate();
    }
    navigationController.$inject = ['$scope', 'navigationService', 'authenticationService'];

    angular.module('zen.components.navigation', [
            'ui.router',
            'zen.services',
            'zen.components.account'
        ]).directive('zenNavigation', navigation);
})(window, window.angular);
