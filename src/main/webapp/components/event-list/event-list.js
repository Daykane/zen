(function(window, angular, _){
    

    function eventList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/event-list/event-list.html',
            scope: {},
            controller: eventListController
        };
    }

    function eventListController($scope, eventListService, $filter, Events, Activities, Users, authenticationService, $q, $resource, apiUrl){
        // Private variables
        var userEvents = $resource(apiUrl + 'Users/:userId/Events', {userId: '@userId'}, {});

        var eventCrud = $resource(apiUrl + 'Events/:eventId', {eventId: '@eventId'}, {
            'update': {
                method: 'PUT',
                params: {eventId: '@eventId'}
            }
        });

        // Private methods
        
        
        // Public variables

        $scope.authentication = authenticationService;
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.activityFilter = "";
        $scope.subscriptionFilter = "";
        $scope.activities = Activities.query();
        $scope.allEvents = [];
        $scope.userEvents = [];
        $scope.events = [];

        eventCrud.query(function(allEvents){
            $scope.allEvents = allEvents;
            if (authenticationService.isConnected()){
                userEvents.query({userId: authenticationService.getCurrentUser().id}, function(userEvents){
                    $scope.userEvents = userEvents;
                    $scope.events = events($scope.allEvents, $scope.userEvents);
                });
            }
           $scope.events = $scope.allEvents;
        });
        

        //load events
        function events (allEvents, userEvents){
            var j = 0, i = 0;
            var events = allEvents;
            for (i = 0; i<allEvents.length; i++){
                if (j == userEvents.length ){
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


        // Public methods

        $scope.filterEventsByActivity = function (event) {

            return !$scope.activityFilter ? 
                   event : (event.activityId == $scope.activityFilter);
        };

        $scope.filterEventsBySubscription = function (event) {

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
            console.log('sub eventId=' + event.eventId);
            event.subscribe = true;
            Events.subscribe(event.eventId);
        }

        $scope.unsubscribe = function(event){
           console.log('unsub eventId=' + event.eventId);
           event.subscribe = false;
           Events.unsubscribe(event.eventId);
        }

        // Init
    }
    eventListController.$inject = ['$scope', 'eventListService', '$filter','Events', 'Activities', 'Users', 'authenticationService', '$q', '$resource', 'apiUrl'];

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
