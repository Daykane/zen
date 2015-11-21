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

    function eventListController($scope, productListService, $filter, Events, Activities, authenticationService, attrs){
        // Private variables

        // Private methods
        
        // Public variables


        [{"eventId":1,"eventName":"event1","eventPrice":10.0,"durationHours":2.0,"maxNubr":30,"eventType":null,"activityId":0,"contributor":0},{"eventId":2,"eventName":"event1","eventPrice":10.7,"durationHours":2.5,"maxNubr":30,"eventType":null,"activityId":0,"contributor":0},{"eventId":3,"eventName":"event1","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0}];
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.activityFilter = "";

        //$scope.categories = Categories.query();
        $scope.activities=[{"activityId":1,"activityName":"Kendo","activityLongDesc":"LongDesc","listEvent":null,"activityShortDescr":"ShortDesc"}];
        //$scope.events = Events.query();
        $scope.events = [{"eventId":1,"eventName":"Kendo senior","eventPrice":10.0,"durationHours":2.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0},{"eventId":2,"eventName":"Kendo Junior","eventPrice":10.7,"durationHours":2.5,"maxNubr":30,"eventType":null,"activityId":0,"contributor":0},{"eventId":3,"eventName":"Yoga Loisir","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0}];
        $scope.userEvents = [{"eventId":1,"eventName":"event1","eventPrice":10.0,"durationHours":2.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0}];
        

        // Public methods

         $scope.filterEvents = function (event) {
        return !$scope.activityFilter ? 
                   event : (event.activityEvent == $scope.activityFilter);
        };

        $scope.prevPage = function() {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.setPage = function(n) {
            $scope.currentPage=n;
        };

        $scope.prevPageDisabled = function() {
            return $scope.currentPage === 0 ? "disabled" : "";
        };
        
        $scope.eventsFiltered = function (){
            var eventsFiltered = $scope.events;
            eventsFiltered = $filter('filter')(eventsFiltered, $scope.searchBar);
            eventsFiltered = $filter('filter')(eventsFiltered, $scope.filterEvents);
            eventsFiltered = $filter('orderBy')(eventsFiltered, $scope.orderProps);
            return eventsFiltered
                //event in events | filter:searchBar | filter: filterEvents | orderBy:orderProps | startFrom:currentPage*pageSize | limitTo:pageSize"
        }

        $scope.pageCount = function() {
            var events = $scope.eventsFiltered();
            return Math.ceil(events.length/$scope.pageSize)-1;
        };

        $scope.nextPage = function() {
            if ($scope.currentPage < $scope.pageCount()) {
                $scope.currentPage++;
            }
        };

        $scope.nextPageDisabled = function() {
            return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
        };

        // Init
    }
    eventListController.$inject = ['$scope', 'productListService', '$filter','Events', 'Activities', 'authenticationService'];

    angular.module('zen.components.eventList', [
            'zen.api.activities',
            'zen.api.events',
            'zen.services'
        ])
        .directive('zenEventList', eventList)
        .controller('eventListController', eventListController);
})(window, window.angular, window._);
