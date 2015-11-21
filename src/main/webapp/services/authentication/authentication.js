//TODO

(function(window, angular){
    'use strict';

    /**
     * @description The role of this service is to manage authentication data
     */
    function authenticationService($http, apiUrl){    
    	var currentUser= {id: "", email: "", firstName: "", lastName: "", adr1: "", adr2: "", postalCode: "", town: "", phone: ""};
        var cart;
        var loginSuccess= false;
        var passwordFailed=false;
    	return {
            login: function(email, password, callback){
            	var data = {mail: email, password: password};
            	function doLogin(){
            		$http({
	                    method: 'POST',
	                    headers: {
	                        "Content-Type": "application/json"
	                    },
	                    dataType: "json",
	                    url: apiUrl+'login',
	                    data: JSON.stringify(data),
	                }).then(function successCallback(response) {
	                	currentUser.id= response.data.id;
	                	currentUser.email = response.data.mail;
	                	currentUser.firstName = response.data.firstName;
	                	currentUser.lastName= response.data.lastName;
	                	currentUser.adr1= response.data.adr1;
	                	currentUser.adr2= response.data.adr2;
	                	currentUser.postalCode = response.data.pc;
	                	currentUser. town = response.data.town;
	                	currentUser.phone = response.data.phone;
	                	
	                	loginSuccess=true;
	                	callback();
	                  }, function errorCallback(response) {
	                	loginSuccess= false;
	                	if(response.status == 400 && response.data.UserError=="true"){
	                		passwordFailed= true;
	                	}
	                	callback();
	                  });
            	}
            	
            	doLogin();
        	},
            getCurrentUser: function(){
            	return currentUser;
            },
        	cart: cart,
        	isPasswordFailed: function(){
        		return passwordFailed;
        	},
        	isConnected: function(){
        			return loginSuccess;
        		},
        	activate: function(){
        		console.log("initialized");
        	},
        	getId: function(){
        		return
        	}
        };
    }
    authenticationService.$inject = ['$http', 'apiUrl']

    angular.module('zen.services')
        .factory('authenticationService', authenticationService);

})(window, window.angular);
