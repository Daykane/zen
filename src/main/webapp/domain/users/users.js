(function(window, angular, _){
    'use strict';

    function userFactory($resource, apiUrl){

        // Init
        var data = $resource(apiUrl + 'Users/:userId', null, {
            'update': {
                method: 'PUT',
                params: {userId: '@userId'}
            }
        });
        return data;
    }
    userFactory.$inject = ['$resource', 'apiUrl'];

    function Users(authenticationService){

        // Private methods
        function get(userId){
            return userFactory.get({userId: userId});
        }

        function getAll(){
            return userFactory.query();
        }

        function create(email, password, firstName, lastName, adress, additionalAdress, town, postalCode, phoneNumber){
            userFactory.save({mail: email, password: password, firstName: firstName, lastName: lastName, adr1: adress, adr2: additionalAdress, town: town, pc: postalCode, phone: phoneNumber });

        }

        function update(signature, userId, lastName, firstName, adr1, adr2, pc, town, phone, email, password, id){
            // userFactory.update();
            //TODO
        }

        function remove(signature, userId){
            // userFactory.delete();
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
    Users.$inject = ['userFactory', 'apiUrl'];

    angular.module('zen.api.users', [
        'ngResource',
        'zen.services'
    ])
        .factory('Users', Users)
        .factory('userFactory', userFactory);

})(window, window.angular, window._);
