(function(window, angular){
    'use strict';

    function cartConfig($stateProvider){
        $stateProvider.state('root.cart', {
            url: '/cart',
            templateUrl: 'states/root.cart/cart.html',
            controller: 'cartController as cartState'
        });
    }
    cartConfig.$inject = ['$stateProvider'];

    function cartRun (){
    }

    function cartController($scope, authenticationService){

        $scope.products = authenticationService.cart;
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }
    cartController.$inject = ['$scope', 'authenticationService'];

    angular.module('zen.states.cart', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(cartConfig)
    .run(cartRun)
    .controller('cartController', cartController);

})(window, window.angular);
