//TODO

(function(window, angular){
    'use strict';

    /**
     * @description The role of this service is to manage authentication data
     */
    function authenticationService(){    

        return {
            activate: function(){
            },

            reset: function(){
            },

            isMember: function(){
                return true // TODO
            },
            
            isAdmin: function(){
                return true // TODO
            },

            isManager: function(){
                return true // TODO
            },

            isContributor: function(){
                return true // TODO
            },

            isConnected: function(){
                return true // TODO
            },

            isNotConnected: function(){
                return true // TODO
            },

            getCurrentUserName: function(){
                return "Toto"; //TODO
            },
            getId: function(){
                return 1; //TODO
            },
            getSignature: function(){
                return "Toto"; //TODO
            }
        };
    }
    authenticationService.$inject = []

    angular.module('zen.services')
        .factory('authenticationService', authenticationService);

})(window, window.angular);
