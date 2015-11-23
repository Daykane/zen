(function(window, angular){
    

    function managementConfig($stateProvider){
        $stateProvider.state('root.management', {
            abstract: true,
            templateUrl: 'states/root.management/management.html',
            controller: 'managementController as managementState'
        });
    }
    managementConfig.$inject = ['$stateProvider'];

    function managementRun (){
    }

    function managementController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.management', [
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(managementConfig)
    .run(managementRun)
    .controller('managementController', managementController);

})(window, window.angular);
