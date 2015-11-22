(function(window, angular, _){
    'use strict';

    function Products($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'Products/:productId', {productId: '@productId'}, {
            'update': {
                method: 'PUT',
                params: {productId: '@productId'}
            },
        });
        return data;
    }

    Products.$inject = ['$resource', 'apiUrl'];

    angular.module('zen.api.products', [
        'ngResource',
        'zen.services'
        ])
    .factory('Products', Products)

})(window, window.angular, window._);
