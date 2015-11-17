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
            console.log(user);
            $http({
                method: 'POST',
                url: apiUrl+'Users',
                data: user,
            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available
                alert("done");
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                alert("error");
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
