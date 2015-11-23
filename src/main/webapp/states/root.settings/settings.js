(function(window, angular){
    

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
    	$scope.wrongPassword=false;
    	
    	$scope.updatePassword = function(){
    		if($scope.password!="" && $scope.newPassword!="" && $scope.newPassword == $scope.newPasswordConfirm){
    			$scope.passwordMatch= true;
    			var data={oldPassword: $scope.password, newPassword: $scope.newPassword};
    			$http({
                    method: 'PUT',
                    headers: {
                        "Content-Type": "application/json",
                        "token": authenticationService.getCookieToken
                    },
                    dataType: "json",
                    url: apiUrl+'Users/'+$scope.id+'/changePassword',
                    data: JSON.stringify(data),
                }).then(function successCallback(response) {
                	$state.go("root.home");
                	$scope.wrongPassword= false;
                  }, function errorCallback(response) {
                	if(response.status==403 && response.data.passwordError=="true"){
                		$scope.wrongPassword= true;
                	}
                  });
    		}
    		else{
    			$scope.passwordMatch= false;
    		}
    	};
    	
    	$scope.passwordMatch= true;
    	
    	function validateEmail(email){
    		var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            return re.test(email);
    	};
    	
    	$scope.updateInfos= function(){
    		if(validateEmail($scope.email) && $scope.email!="" && $scope.firstName!="" && $scope.lastName!=""){
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
