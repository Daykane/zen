(function(window, angular){
    'use strict';

    function productsConfig($stateProvider){
        $stateProvider.state('root.management.products', {
            url: '/management/products',
            templateUrl: 'states/root.management.products/products.html',
            controller: 'productsController as productsState'
        });
    }
    productsConfig.$inject = ['$stateProvider'];

    function productsRun (){
    }

    function productsController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.products', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(productsConfig)
    .run(productsRun)
    .controller('productsController', productsController);

})(window, window.angular);
