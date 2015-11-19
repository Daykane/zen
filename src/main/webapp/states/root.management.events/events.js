(function(window, angular){
    'use strict';

    function eventsConfig($stateProvider){
        $stateProvider.state('root.management.events', {
            url: '/management/events',
            templateUrl: 'states/root.management.events/events.html',
            controller: 'eventsController as eventsState'
        });
    }
    eventsConfig.$inject = ['$stateProvider'];

    function eventsRun (){
    }

    function eventsController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.events', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(eventsConfig)
    .run(eventsRun)
    .controller('eventsController', eventsController);

})(window, window.angular);
