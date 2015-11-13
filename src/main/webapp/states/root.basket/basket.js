(function(window, angular){
    'use strict';

    function basketConfig($stateProvider){
        $stateProvider.state('root.basket', {
            url: '/basket',
            templateUrl: 'states/root.basket/basket.html',
            controller: 'basketController as basketState'
        });
    }
    basketConfig.$inject = ['$stateProvider'];

    function basketRun (){
    }

    function basketController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.basket', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(basketConfig)
    .run(basketRun)
    .controller('basketController', basketController);

})(window, window.angular);
