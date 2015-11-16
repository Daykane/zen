//MOCK

(function(window, angular, _){
    'use strict';

    function users(){
        // Init
        var users = $resource(apiUrl + 'users', {}, {
            get: {
                method: 'GET'
            },
            post: {
                method: 'POST'
            },
            put: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
        // Private variables

        // Private methods
       function getId(email, password){
            //TODO
       }

       function getUser(id, signature){
            //TODO
       }

       function getUsers(){
            //TODO
       }

       function postUser(lastName, firstName, adr1, adr2, pc, town, phone, email, password){
            //TODO
       }

       function putUser(lastName, firstName, adr1, adr2, pc, town, phone, email, password){
            //TODO
       }

       function deleteUser(){
            //TODO
       }

        // Public API
        return {
            getId: getId,
            getUser: getUser,
            getUsers: getUsers,
            postUser: postUser,
            putUser: putUser,
            deleteUser: deleteUser
        };
    }
    users.$inject = [];

    angular.module('zen.api.users', [
            'zen.services'
        ]).factory('Users', users);
})(window, window.angular, window._);
