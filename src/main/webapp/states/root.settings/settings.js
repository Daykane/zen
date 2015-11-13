(function(window, angular){
    'use strict';

    function settingsConfig($stateProvider){
        $stateProvider.state('root.settings', {
            url: '/settings',
            templateUrl: 'states/root.settings/settings.html',
            controller: 'settingsController as settingsState'
        });
    }
    settingsConfig.$inject = ['$stateProvider'];

    function settingsRun (){
    }

    function settingsController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Initialization
    }

    angular.module('zen.states.settings', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(settingsConfig)
    .run(settingsRun)
    .controller('settingsController', settingsController);

})(window, window.angular);
