(function(window, angular){
    'use strict';
<<<<<<< HEAD

=======
    
>>>>>>> d85c61d8a485298a873b6951852eabcde1c93819
    function registerConfig($stateProvider){
        $stateProvider.state('root.register', {
            url: '/register',
            templateUrl: 'states/root.register/register.html',
            controller: 'registerController as registerState'
        });
    }
    registerConfig.$inject = ['$stateProvider'];

    function registerRun(){}

<<<<<<< HEAD
    function registerController($state, $scope){

        //var self = this;
        $scope.passwordMatch=true;

        $scope.register = function(){
            if($scope.password == $scope.confirmPassword ){
                $scope.passwordMatch=true;
                //POST DATA
            }
            else{
                $scope.passwordMatch=false;
            }
        };



        $scope.registerSuccess= function(){
            $state.go('root.home');
        }

        $scope.registerFailure = function(error){
                self.status = error;
        }

    }
    registerController.$inject = ['$state', '$scope'];
=======
    function registerController(authenticationService, $state){
        // Private variables
        var self = this;

        // Private methods


        // Public variables
        self.credentials = null;

        // Public methods

        // Initialization
        authenticationService.activate();
    }
    registerController.$inject = ['authenticationService', '$state'];
>>>>>>> d85c61d8a485298a873b6951852eabcde1c93819

    angular.module('zen.states.register', [
        'zen.services'
    ])
<<<<<<< HEAD
        .config(registerConfig)
        .run(registerRun)
        .controller('registerController', registerController);
=======
    .config(registerConfig)
    .run(registerRun)
    .controller('registerController', registerController);
>>>>>>> d85c61d8a485298a873b6951852eabcde1c93819
})(window, window.angular);
