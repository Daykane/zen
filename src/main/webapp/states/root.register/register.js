(function(window, angular){
    'use strict';

    function registerConfig($stateProvider){
        $stateProvider.state('root.register', {
            url: '/register',
            templateUrl: 'states/root.register/register.html',
            controller: 'registerController as registerState'
        });
    }
    registerConfig.$inject = ['$stateProvider'];

    function registerRun(){}

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

    angular.module('zen.states.register', [
        'zen.services'
    ])
        .config(registerConfig)
        .run(registerRun)
        .controller('registerController', registerController);
})(window, window.angular);
