(function(window, angular, _){
    'use strict';

    function Categories($resource, apiUrl){

        // Init
        var crud = $resource(apiUrl + 'Categories/:categoryId', null, {
            'update': {
                method: 'PUT',
                params: {categoryId: '@categoryId'}
            }
            // ,
            // 'get': {
            //     method: 'GET' ,
            //     params: {categoryId: '@categoryId'},
            //     headers: { 'token': 'gr15hg7k6getkspieeeptqjs11' }
            // },
            // 'query': {
            //     method: 'GET' ,
            //     params: {categoryId: '@categoryId'},
            //     headers: { 'token': 'gr15hg7ktqjs11' },
            //     isArray: true
            // }


        });

        function get(){
            return crud.get();
        }

        function getAll(){
            return crud.query();
        }

        return {
            getAll: getAll,
            get: get
        }


    }

    Categories.$inject = ['$resource', 'apiUrl'];

    angular.module('zen.api.categories', [
        'ngResource',
        'zen.services'
        ])
    .factory('Categories', Categories)

})(window, window.angular, window._);
