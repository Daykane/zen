(function(window, angular, _){
    'use strict';

    function Events(apiUrl, $http, $resource, authenticationService){
        function crud(callback){
            var resource = $resource(apiUrl + 'Events/:eventId', null, {
            'update': {
                method: 'PUT',
                params: {eventId: '@eventId'}
            }
            });

            return callback(resource);
        }

        // Private methods
        function get(eventId){
            return crud(function(resource){
                return resource.get({userId: userId});
            });
        }

        function getAll(){
            return crud(function(resource){
                return resource.query();
            });
        }

        function subscribe(eventId){
             var subscribe = $resource(apiUrl + 'Events/:eventId/subscribe', null, {
            'get': {
                method: 'GET',
                params: {eventId: '@eventId'},
                headers: {'token': authenticationService.getCurrentUser().token}
            }
            });
            subscribe.get({eventId: eventId});
        }

        function unsubscribe(eventId){
             var unsubscribe = $resource(apiUrl + 'Events/:eventId/unsubscribe', null, {
            'get': {
                method: 'GET',
                params: {eventId: '@eventId'},
                headers: {'token': authenticationService.getCurrentUser().token}
            }
            });
            unsubscribe.get({eventId: eventId});
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
