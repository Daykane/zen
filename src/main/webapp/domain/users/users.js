(function(window, angular, _){
    'use strict';

    function Users(apiUrl, $http, $resource){

        function crud(){
            var crud = $resource(apiUrl + 'Users/:userId', null, {
                'update': {
                    method: 'PUT',
                    params: {userId: '@userId'}
                }
            });

            return crud;
        }

        function userEvents(){
             var userEvents = $resource(apiUrl + 'Users/:userId/Events', null, {
                'update': {
                    method: 'PUT',
                    params: {userId: '@userId'}
                }
            });

             return userEvents;
        }

        // Private methods
        function get(userId){
            return crud().get({userId: userId});

        }

        function getAll(){
            return crud().query();
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

        function getEvents(userId){
            userEvents().query({userId: userId});
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
