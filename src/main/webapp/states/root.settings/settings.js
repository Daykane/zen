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

    function settingsController($state, $scope, authenticationService, $http, apiUrl){
    	
    	$scope.wantToUpdateProfile=true;
    	$scope.wantToUpdatePassword=false;
    	
    	$scope.wantChangePassword=function(){
    		$scope.wantToUpdateProfile=false;
        	$scope.wantToUpdatePassword=true;
    	}
    	
    	$scope.wantChangeProfile=function(){
    		$scope.wantToUpdateProfile=true;
        	$scope.wantToUpdatePassword=false;
    	}
    	
    	$scope.email= authenticationService.getCurrentUser().email;
    	$scope.id= authenticationService.getCurrentUser().id;
    	$scope.firstName= authenticationService.getCurrentUser().firstName;
    	$scope.lastName= authenticationService.getCurrentUser().lastName;
    	$scope.adr1= authenticationService.getCurrentUser().adr1;
    	$scope.adr2= authenticationService.getCurrentUser().adr2;
    	$scope.postalCode= authenticationService.getCurrentUser().postalCode;
    	$scope.town= authenticationService.getCurrentUser().town;
    	$scope.phone= authenticationService.getCurrentUser().phone;
    	
    	$scope.mailAlreadyUsed= false;
    	$scope.newPassword= "";
    	$scope.newPasswordConfirm="";
    	
    	$scope.updatePassword = function(){
    		if($scope.newPassword == $scope.newPasswordConfirm){
    			console.log("hello");
    			console.log($scope.newPassword);
    			console.log($scope.newPasswordConfirm);
    		}
    		else{
    			$scope.passwordMatch= false;
    			console.log("not hello");
    		}
    	};
    	
    	$scope.passwordMatch= true;
    	
    	$scope.updateInfos= function(){
    		var data= {mail: $scope.email, id: $scope.id, firstName: $scope.firstName, lastName: $scope.lastName, adr1: $scope.adr1, adr2: $scope.adr2, pc: $scope.postalCode, town: $scope.town, phone: $scope.phone};
    		$http({
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                    "token": authenticationService.getCookieToken
                },
                dataType: "json",
                url: apiUrl+'Users/'+$scope.id,
                data: JSON.stringify(data),
            }).then(function successCallback(response) {
            	$scope.mailAlreadyUsed= false;
            	$state.go("root.home");
              }, function errorCallback(response) {
            	if(response.status==400 && response.data.mailError=="true"){
            		$scope.mailAlreadyUsed= true;
            	}
            	$scope.email= authenticationService.getCurrentUser().email;
            	$scope.id= authenticationService.getCurrentUser().id;
            	$scope.firstName= authenticationService.getCurrentUser().firstName;
            	$scope.lastName= authenticationService.getCurrentUser().lastName;
            	$scope.adr1= authenticationService.getCurrentUser().adr1;
            	$scope.adr2= authenticationService.getCurrentUser().adr2;
            	$scope.postalCode= authenticationService.getCurrentUser().postalCode;
            	$scope.town= authenticationService.getCurrentUser().town;
            	$scope.phone= authenticationService.getCurrentUser().phone;
              });
    	}
    	
    	

    }
    
    settingsController.$inject = ['$state', '$scope', 'authenticationService', '$http', 'apiUrl'];

    angular.module('zen.states.settings', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(settingsConfig)
    .run(settingsRun)
    .controller('settingsController', settingsController);

})(window, window.angular);
