(function(window, angular){
    'use strict';

    function homeConfig($stateProvider){
        $stateProvider.state('root.home', {
            url: '/',
            templateUrl: 'states/root.home/home.html',
            controller: 'homeController as homeState'
        });
    }
    homeConfig.$inject = ['$stateProvider'];

    function homeRun (){}

    function homeController(authenticationService, $scope){
        // Private variables
        var self = this;
        // Private methods

        // Public variables
        $scope.authentication = authenticationService;
        // Public methods

        // Initialization
        authenticationService.activate();
    }
    homeController.$inject = ['authenticationService', '$scope', 'sha1'];


    angular.module('zen.states.home', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(homeConfig)
    .run(homeRun)
    .controller('homeController', homeController);

})(window, window.angular);
