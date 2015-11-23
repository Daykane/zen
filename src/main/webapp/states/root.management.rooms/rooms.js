(function(window, angular){
    

    function roomsConfig($stateProvider){
        $stateProvider.state('root.management.rooms', {
            url: '/management/rooms',
            templateUrl: 'states/root.management.rooms/rooms.html',
            controller: 'roomsController as roomsState'
        });
    }
    roomsConfig.$inject = ['$stateProvider'];

    function roomsRun (){
    }

    function roomsController(){
        // Private variables

        // Private methods

        // Public variables

        // Public methods

        // Init
    }

    angular.module('zen.states.rooms', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(roomsConfig)
    .run(roomsRun)
    .controller('roomsController', roomsController);

})(window, window.angular);
