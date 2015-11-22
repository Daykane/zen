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

    function eventListController($scope, eventListService, $filter, Events, Activities, authenticationService, attrs){
        // Private variables
        var allEvents = [
            {"eventId":1,"eventName":"Kendo senior","eventPrice":10.0,"durationHours":2.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0},
            {"eventId":2,"eventName":"Kendo Junior","eventPrice":10.7,"durationHours":2.5,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0},
            {"eventId":3,"eventName":"Yoga Loisir 1","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":2,"contributor":0},
            {"eventId":4,"eventName":"Yoga Master","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":2,"contributor":0},
            {"eventId":5,"eventName":"Yoga Debutant","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":2,"contributor":0},
            {"eventId":6,"eventName":"Yoga Yolo","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":2,"contributor":0}
        ];
        var userEvents = [];
        if (authenticationService.isConnected()){
                userEvents = [
                {"eventId":1,"eventName":"event1","eventPrice":10.0,"durationHours":2.0,"maxNubr":30,"eventType":null,"activityId":1,"contributor":0},
                {"eventId":5,"eventName":"Yoga Debutant","eventPrice":5.5,"durationHours":10.0,"maxNubr":30,"eventType":null,"activityId":2,"contributor":0}
            ];
        }
        // Private methods
        function events (allEvents, userEvents){
            var j = 0, i = 0;
            var events = allEvents;
            for (i = 0; i<allEvents.length; i++){
                if (j == userEvents.length){
                    events[i].subscribe = false;
                }
                else{
                    if (allEvents[i].eventId == userEvents[j].eventId){
                        events[i].subscribe = true;
                        j++;
                    }
                    else{
                        events[i].subscribe = false;
                    }
                }
            }
            return events

        }
        
        // Public variables

        $scope.authentication = authenticationService;
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.activityFilter = "";
        $scope.subscriptionFilter = "";

        //$scope.categories = Categories.query();
        $scope.activities=[
            {"activityId":1,"activityName":"Kendo","activityLongDesc":"LongDesc","listEvent":null,"activityShortDescr":"ShortDesc"},
            {"activityId":2,"activityName":"Yoga","activityLongDesc":"LongDesc","listEvent":null,"activityShortDescr":"ShortDesc"}
        ];
        //$scope.events = Events.query();
        $scope.events = events(allEvents, userEvents);

        // Public methods

        $scope.filterEventsByActivity = function (event) {
            console.log("event activity=" + event.activityId + "filter=" + $scope.activityFilter);

            return !$scope.activityFilter ? 
                   event : (event.activityId == $scope.activityFilter);
        };

        $scope.filterEventsBySubscription = function (event) {
            console.log("event subscribe=" + event.subscribe + "filter=" + $scope.subscriptionFilter);

            var subscribe = $scope.subscriptionFilter=='subscribed';

            return !$scope.subscriptionFilter ? 
                   event : (event.subscribe == subscribe);
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
            eventsFiltered = $filter('filter')(eventsFiltered, $scope.filterEventsByActivity);
            eventsFiltered = $filter('filter')(eventsFiltered, $scope.filterEventsBySubscription);
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

        $scope.subscribe = function(event){
            Users.subscribe(event.eventId);
        }

        $scope.unsubscribe = function(event){
            Users.unsubscribe(event.eventId);
        }

        // Init
    }
    eventListController.$inject = ['$scope', 'eventListService', '$filter','Events', 'Activities', 'authenticationService'];

    angular.module('zen.components.eventList', [
        'zen.api.activities',
        'zen.api.events',
        'zen.services'
    ])
    .directive('zenEventList', eventList)
    .controller('eventListController', eventListController)
    .filter('startFrom', function() {
        return function(input, start) {
            start = +start; //parse to int
            return input.slice(start);
        }
    })
    .filter('range', function() {
        return function(input) {
            var lowBound, highBound;
            switch (input.length) {
                case 1:
                lowBound = 0;
                highBound = parseInt(input[0]) - 1;
                break;
                case 2:
                lowBound = parseInt(input[0]);
                highBound = parseInt(input[1]);
                break;
                default:
                return input;
            }
            var result = [];
            for (var i = lowBound; i <= highBound; i++)
                result.push(i);
            return result;
        };
    });
})(window, window.angular, window._);
