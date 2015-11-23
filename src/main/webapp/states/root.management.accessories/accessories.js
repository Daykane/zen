(function(window, angular){
    

    function accessoriesConfig($stateProvider){
        $stateProvider.state('root.management.accessories', {
            url: '/management/accessories',
            templateUrl: 'states/root.management.accessories/accessories.html',
            controller: 'accessoriesController as accessoriesState'
        });
    }
    accessoriesConfig.$inject = ['$stateProvider'];

    function accessoriesRun (){
    }

    function accessoriesController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.accessories', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(accessoriesConfig)
    .run(accessoriesRun)
    .controller('accessoriesController', accessoriesController);

})(window, window.angular);
