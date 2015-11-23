(function(window, angular){
    
    
    function loginConfig($stateProvider){
        $stateProvider.state('root.login', {
            url: '/login',
            templateUrl: 'states/root.login/login.html',
            controller: 'loginController as loginState'
        });
    }
    loginConfig.$inject = ['$stateProvider'];

    function loginRun(){}

    function loginController(authenticationService, $state, $scope, GooglePlus){
        // Private variables
        //var self = this;
    	$scope.email="";
    	$scope.password="";
    	$scope.passwordFailed=false;
    	function doCallback(){
    		if(authenticationService.isConnected()){
    			$scope.passwordFailed=true;
    			$state.go('root.home');
    		}
    		else if(authenticationService.isPasswordFailed()){
    			$scope.passwordFailed= true;
    		}
    	}
    	$scope.login= function(){
    		if($scope.email!= "" && $scope.password != ""){
    			authenticationService.login($scope.email, $scope.password, doCallback);
    		}
    	}
    	
    	$scope.Glogin = function () {
            GooglePlus.login().then(function (authResult) {
                //console.log(authResult);

                GooglePlus.getUser().then(function (user) {
                    console.log(user);
                });
            }, function (err) {
                console.log(err);
            });
        };

    }
    loginController.$inject = ['authenticationService', '$state', '$scope', 'GooglePlus'];

    angular.module('zen.states.login', [
        'zen.services'
    ])
    .config(loginConfig)
    .run(loginRun)
    .controller('loginController', loginController);
})(window, window.angular);
