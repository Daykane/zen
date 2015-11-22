(function(window, angular, _){
    'use strict';

    function Events(apiUrl, $http, $resource, authenticationService){
        function crud(){
            var resource = $resource(apiUrl + 'Events/:eventId', null, {
                'update': {
                    method: 'PUT',
                    params: {eventId: '@eventId'}
                }
            });
            return resource;
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
            subscribe: subscribe,
            unsubscribe: unsubscribe,
            crud: crud
        }
    }

    Events.$inject = ['apiUrl', '$http', '$resource', 'authenticationService'];

    angular.module('zen.api.events', [
        'ngResource',
        'zen.services'
    ])
    .factory('Events', Events);

})(window, window.angular, window._);
