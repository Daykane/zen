(function(window, angular, _){
    'use strict';

    function products(){
        // Init

        // Private variables

        // Private methods
        function getProducts(){
            var User = $resource('/user/:userId', {userId:'@id'});
            var user = User.get({userId:123}, function() {
              user.abc = true;
              user.$save();
            });
        }
        // Public API
    }

    angular.module('zen.api.products', [
            'zen.services'
        ]).factory('Products', products);
})(window, window.angular, window._);
