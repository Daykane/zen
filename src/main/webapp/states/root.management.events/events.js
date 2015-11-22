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

    function eventsController($state, $scope, authenticationService, $http, apiUrl){
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
                  }, function errorCallback(response) {
                	  
                  });
    		}
    		
    	};
        
        $scope.getNumber = function(start, end) {
        	var res= [start];
        	for(var i=start+1; i<= end; i++){
        		res.push(i);
        	}
        	return res;
        }
    }
    eventsController.$inject = ['$state', '$scope', 'authenticationService', '$http', 'apiUrl'];

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
