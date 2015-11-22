(function(window, angular, _){
    'use strict';

    function Events(apiUrl, $http, $resource, authenticationService){
        var crud = $resource(apiUrl + 'Events/:eventId', null, {
            'update': {
                method: 'PUT',
                params: {eventId: '@eventId'}
            }
        });

        var unsubscribe = $resource(apiUrl + 'Events/:eventId/unsubscribe', null, {});

        // Private methods
        function get(eventId){
            return crud.get({eventId: eventId});
        }

        function getAll(){
            return crud.query();
        }

        function subscribe(eventId){
             var subscribe = $resource(apiUrl + 'Events/:eventId/subscribe', null, {
            'get': {
                method: 'GET',
                params: {eventId: '@eventId'},
                headers: {'token': authenticationService.getCurrentUser().token}
            }
            });
            subscribe.get();
        }

        function unsubscribe(eventId){
             var unsubscribe = $resource(apiUrl + 'Events/:eventId/unsubscribe', null, {
            'get': {
                method: 'GET',
                params: {eventId: '@eventId'},
                headers: {'token': authenticationService.getCurrentUser().token}
            }
            });
            unsubscribe.get();
        }

        // Public API
        return {
            get: get,
            getAll: getAll,
            subscribe: subscribe,
            unsubscribe: unsubscribe
        }
    }

    Events.$inject = ['apiUrl', '$http', '$resource', 'authenticationService'];

    angular.module('zen.api.events', [
        'ngResource',
        'zen.services'
    ])
    .factory('Events', Events);

})(window, window.angular, window._);
