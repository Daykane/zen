(function(window, angular, _){
    

    function Users(apiUrl, $http, $resource){
        // Private methods
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
            var userEvents = $resource(apiUrl + 'Users/:userId/Events', {userId:'@userId'}, {
                'update': {
                    method: 'PUT',
                    params: {userId: '@userId'}
                }
            });
            return userEvents;
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
            create: create,
            crud: crud,
            userEvents: userEvents
        }
    }
    Users.$inject = ['apiUrl', '$http', '$resource'];

    angular.module('zen.api.users', [
        'ngResource',
        'zen.services'
    ])
        .factory('Users', Users)

})(window, window.angular, window._);
