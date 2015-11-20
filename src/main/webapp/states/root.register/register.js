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

    function registerController($state, $scope, Users){

        //var self = this;
        $scope.passwordMatch=true;
        $scope.mailAlreadyExists = false;

        $scope.register = function(){
            if($scope.password == $scope.confirmPassword ){
                $scope.passwordMatch=true;
                Users.create($scope.email, $scope.password, $scope.firstName, $scope.lastName, $scope.adress, $scope.additionalAdress, $scope.town, $scope.postalCode, $scope.phoneNumber).then($scope.registerSuccess, $scope.registerFailure);
            }
            else{
                $scope.passwordMatch=false;
            }
        };



        $scope.registerSuccess= function(){
            $state.go('root.login');
        }

        $scope.registerFailure = function(error){
            console.log(error);
            $scope.mailAlreadyExists= true;
        }

    }
    registerController.$inject = ['$state', '$scope', 'Users'];

    angular.module('zen.states.register', [
        'zen.services'
    ])
    .config(registerConfig)
    .run(registerRun)
    .controller('registerController', registerController);
})(window, window.angular);
