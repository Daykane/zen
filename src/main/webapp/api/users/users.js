//MOCK

(function(window, angular, _){
    'use strict';

    function users(){
        // Init

        // Private variables

        // Private methods
       function getId(email, password){
            return 1;
       }

       function getUser(id, signature){

            var deferred = $q.defer();

            var user = {
                userName : 'Toto',
                userEmail : 'toto@toto.com',
                userId: 1,
                isMember : true,
                isAdmin : true,
                isContributor : true,
                isManager : true
            }
            deferred.resolve(user);
            return deferred.promise;
       }

        // Public API
        return {
            getId: getId,
            getUser: getUser
        };
    }
    users.$inject = [];

    angular.module('zen.api.users', [
            'zen.services'
        ]).factory('Users', users);
})(window, window.angular, window._);
