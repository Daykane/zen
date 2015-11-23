(function(window, angular){
    

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

    function eventsController($state, $scope, authenticationService, $http, apiUrl, $filter,Events, Activities){
        $scope.name= "";
        $scope.activity="";
        $scope.day="";
        $scope.startTime="";
        $scope.endTime="";
        $scope.maxParticipants="";
        
        $scope.createEvent=function(){
        	console.log("clicked")
    		if($scope.name!="" && $scope.activity!="" && $scope.day!="" && $scope.startTime!= "" && $scope.endTime!="" && $scope.maxParticipants!=""){

    			var data={eventName: $scope.name, activityId: $scope.activity, eventDay: $scope.day, startTime: $scope.startTime, endTime: $scope.endTime, maxNubr: $scope.maxParticipants};
        		$http({
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "token": authenticationService.getCookieToken
                    },
                    dataType: "json",
                    url: apiUrl+'Events',
                    data: JSON.stringify(data),
                }).then(function successCallback(response) {
                    $scope.name= "";
                    $scope.activity="";
                    $scope.day="";
                    $scope.startTime="";
                    $scope.endTime="";
                    $scope.maxParticipants="";
                    Events.crud().query(function(allEvents){
                        $scope.events = allEvents;
                    });
                  }, function errorCallback(response) {
                	  
                  });
    		}
    		
    	};
    	
    	$scope.deleteEvent = function(event){
    		Events.crud().delete({eventId: event.eventId}, function(){$scope.events=Events.crud().query();} );
    	};
        
        $scope.getNumber = function(start, end) {
        	var res= [start];
        	for(var i=start+1; i<= end; i++){
        		res.push(i);
        	}
        	return res;
        }
        
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.activityFilter = "";
        $scope.subscriptionFilter = "";
        $scope.activities = Activities.query();
        $scope.events = [];

        Events.crud().query(function(allEvents){
            $scope.events = allEvents;
        });
       
        //$scope.events = events($scope.allEvents, $scope.userEvents);
        

        


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
    }
    eventsController.$inject = ['$state', '$scope', 'authenticationService', '$http', 'apiUrl', '$filter','Events', 'Activities'];

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
