(function(window, angular, _){
    

    function eventListService(){
        // Private variables
        
        // Private methods
        function activate(){}

        function reset(){}
        // Public variables

        // Public methods

        return{
            activate: activate
        }

    }
    eventListService.$inject = [];

    angular.module('zen.components.eventList')
    .factory('eventListService', eventListService);

})(window, window.angular, window._);
