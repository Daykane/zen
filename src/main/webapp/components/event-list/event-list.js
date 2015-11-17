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
        $scope.events = eventFactory.query();
        $scope.event7 = eventFactory.get({eventId: 7});
        $scope.event8 = eventFactory.get({eventId: 8});
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
