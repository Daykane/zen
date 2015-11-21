(function(window, angular, _){
    'use strict';

    function Categories($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'Categories/:categoryId', null, {
            'update': {
                method: 'PUT',
                params: {categoryId: '@categoryId'}
            },

        });
        return data;
    }

    Categories.$inject = ['$resource', 'apiUrl'];

    angular.module('zen.api.categories', [
        'ngResource',
        'zen.services'
        ])
    .factory('Categories', Categories)

})(window, window.angular, window._);
