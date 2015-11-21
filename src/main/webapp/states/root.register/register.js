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
        
        $scope.email="";
        $scope.password="";
        $scope.confirmPassword="";
        $scope.firstName="";
        $scope.lastName="";
        $scope.adress="";
        $scope.additionalAdress="";
        $scope.town="";
        $scope.postalCode="";
        $scope.phoneNumber="";

        $scope.register = function(){
            if(validateEmail($scope.email) && $scope.password == $scope.confirmPassword && $scope.email!= "" && $scope.password!= "" && $scope.firstName!="" && $scope.lastName!=""){
                $scope.passwordMatch=true;
                Users.create($scope.email, $scope.password, $scope.firstName, $scope.lastName, $scope.adress, $scope.additionalAdress, $scope.town, $scope.postalCode, $scope.phoneNumber).then($scope.registerSuccess, $scope.registerFailure);
            }
            else if($scope.password == $scope.confirmPassword){
            	$scope.passwordMatch=true;
            }
            else{
                $scope.passwordMatch=false;
            }
        };



        $scope.registerSuccess= function(){
            $state.go('root.login');
        }

        $scope.registerFailure = function(error){
            $scope.mailAlreadyExists= true;
        }
        
        function validateEmail(email) {
            var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            return re.test(email);
        }

    }
    registerController.$inject = ['$state', '$scope', 'Users'];

    angular.module('zen.states.register', [
        'zen.services',
        'zen.api.users'
    ])
    .config(registerConfig)
    .run(registerRun)
    .controller('registerController', registerController);
})(window, window.angular);
