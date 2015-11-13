(function(window, angular){
    'use strict';

    function storeConfig($stateProvider){
        $stateProvider.state('root.store', {
            url: '/store',
            templateUrl: 'states/root.store/store.html',
            controller: 'storeController as storeState'
        });
    }
    storeConfig.$inject = ['$stateProvider'];

    function storeRun (){}

    function storeController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.store', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(storeConfig)
    .run(storeRun)
    .controller('storeController', storeController);

})(window, window.angular);
