(function(window, angular, _){
    'use strict';

    function productFactory($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'products/:productId', null, {
            'update': {
                method: 'PUT',
                params: {productId: '@productId'}
            }
            
        });

        return data;
    }
    productFactory.$inject = ['$resource', 'apiUrl'];

    function Products(authenticationService){

     // Private methods
       function get(productId){
          return productFactory.get({productId: productId});
       }

       function getAll(){
           return productFactory.query();
       }

       function create(signature, userId, lastName, firstName, adr1, adr2, pc, town, phone, email, password){
            // productFactory.save();
            //TODO
       }

       function update(signature, userId, lastName, firstName, adr1, adr2, pc, town, phone, email, password, id){
            // productFactory.update();
            //TODO
       }

       function remove(signature, userId, productId){
            // productFactory.delete();
            //TODO
       }

        // Public API
        return {
            get: get,
            getAll: getAll,
            create: create,
            update: update,
            remove: remove
        }
    }
    Products.$inject = ['productFactory', 'apiUrl'];

    angular.module('zen.api.products', [
            'ngResource',
            'zen.services'
        ])
    .factory('Products', Products)
    .factory('productFactory', productFactory);

})(window, window.angular, window._);
