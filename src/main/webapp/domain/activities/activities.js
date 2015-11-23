(function(window, angular, _){
    

    function Activities($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'Activities/:activityId', null, {
            'update': {
                method: 'PUT',
                params: {activityId: '@activityId'}
            },
        });
        return data;
    }

    Activities.$inject = ['$resource', 'apiUrl'];

    angular.module('zen.api.activities', [
        'ngResource',
        'zen.services'
        ])
    .factory('Activities', Activities)

})(window, window.angular, window._);
