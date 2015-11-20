(function(window, angular, _){
    'use strict';

    function Users(apiUrl, $http){

        // Private methods
        function get(userId){
            return userFactory.get({userId: userId});
        }

        function getAll(){
            return userFactory.query();
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
    Users.$inject = ['apiUrl', '$http'];

    angular.module('zen.api.users', [
        'ngResource',
        'zen.services'
    ])
        .factory('Users', Users)

})(window, window.angular, window._);
