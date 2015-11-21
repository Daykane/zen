//TODO

(function(window, angular){
    'use strict';

    /**
     * @description The role of this service is to manage authentication data
     */
    function authenticationService($http, apiUrl){    

        return {
            login: function(email, password){
            	var data = {mail: email, password: password};
                return $http({
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                    },
                    dataType: "json",
                    url: apiUrl+'login',
                    data: JSON.stringify(data),
                });
            },
        	activate: function(){
        		console.log("initialized");
        	}
        };
    }
    authenticationService.$inject = ['$http', 'apiUrl']

    angular.module('zen.services')
        .factory('authenticationService', authenticationService);

})(window, window.angular);
