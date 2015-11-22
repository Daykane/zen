(function(window, angular, _){
    'use strict';

    function Users(apiUrl, $http, $resource){
        // Private methods
        function crud(callback){
            var resource = $resource(apiUrl + 'Users/:userId', null, {
                'update': {
                    method: 'PUT',
                    params: {userId: '@userId'}
                }
            });

            return callback(resource);
        }
        function userEvents(callback){
             var resource = $resource(apiUrl + 'Users/:userId/Events', null, {
                'update': {
                    method: 'PUT',
                    params: {userId: '@userId'}
                }
            });
            return callback(resource);
        }

        
        function get(userId){
            return crud(function(resource){
                return resource.get({userId: userId});
            });
        }

        function getAll(){
            return crud(function(resource){
                return resource.query();
            });
        }
        
        function getEvents(userId){
             return userEvents(function(resource){
                return resource.query({userId: userId});
            });
        }

        function create(email, password, firstName, lastName, adress, additionalAdress, town, postalCode, phoneNumber){
            var user = {mail: email, password: password, firstName: firstName, lastName: lastName, adr1: adress, adr2: additionalAdress, town: town, pc: postalCode, phone: phoneNumber };
            console.log(JSON.stringify(user));
            return $http({
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                dataType: "json",
                url: apiUrl+'Users',
                data: JSON.stringify(user),
            });
        }

        // Public API
        return {
            get: get,
            getAll: getAll,
            create: create,
            getEvents: getEvents

        }
    }
    Users.$inject = ['apiUrl', '$http', '$resource'];

    angular.module('zen.api.users', [
        'ngResource',
        'zen.services'
    ])
        .factory('Users', Users)

})(window, window.angular, window._);
