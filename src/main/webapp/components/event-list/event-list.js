(function(window, angular, _){
    'use strict';

    function eventList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/event-list/event-list.html',
            scope: {},
            controller: eventListController
        };
    }

    function eventListController($scope, eventListService, eventFactory){
        // Private variables
        var self = this,
            items = null;

        // Private methods
        
        // Public variables
        self.items = items;

        // Public methods

        // Init
    }
    eventListController.$inject = ['$scope', 'eventListService', 'eventFactory'];

    angular.module('zen.components.eventList', [
            'zen.api.events',
            'zen.services'
        ])
        .directive('zenEventList', eventList)
        .controller('eventListController', eventListController);
})(window, window.angular, window._);
