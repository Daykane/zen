(function(window, angular){
    'use strict';
    
    function loginConfig($stateProvider){
        $stateProvider.state('root.login', {
            url: '/login',
            templateUrl: 'states/root.login/login.html',
            controller: 'loginController as loginState'
        });
    }
    loginConfig.$inject = ['$stateProvider'];

    function loginRun(){}

    function loginController(authenticationService, $state){
        // Private variables
        var self = this;

        // Private methods
        function loginSuccess(){
            $state.go('root.home');
        }

        function loginFailure(error){
            if (error.status === 401){
                self.status = 'invalidCredentials';
            } else {
                self.status = 'error';
            }
        }

        function login(){
            if (self.credentials.email && self.credentials.password){
                authenticationService.login(self.credentials.email, self.credentials.password, self.rememberMe).then(loginSuccess, loginFailure);
            } else {
                self.status = 'invalidCredentials';
            }
        }

        // Public variables
        self.credentials = {
            email: "toto@toto.com",
            password: "toto"
        }

        self.rememberMe = true;
        self.status = 'ok';
        self.isLogout = false;

        // Public methods
        self.login = login;

        // Initialization
        authenticationService.activate();
    }
    loginController.$inject = ['authenticationService', '$state'];

    angular.module('zen.states.login', [
        'zen.services'
    ])
    .config(loginConfig)
    .run(loginRun)
    .controller('loginController', loginController);
})(window, window.angular);
