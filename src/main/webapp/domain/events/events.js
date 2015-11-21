(function(window, angular, _){
    'use strict';

    function Events($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'Events/:eventId', null, {
            'update': {
                method: 'PUT',
                params: {eventId: '@eventId'}
            },
        });
        return data;
    }

    Events.$inject = ['$resource', 'apiUrl'];

    angular.module('zen.api.events', [
        'ngResource',
        'zen.services'
        ])
    .factory('Events', Events)

})(window, window.angular, window._);
