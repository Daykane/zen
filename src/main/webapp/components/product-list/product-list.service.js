(function(window, angular, _){
    

    function productListService(){
        // Private variables
        status = null;
        
        // Private methods
        function activate(){

            
        }

        function reset(){}
        // Public variables

        // Public methods

        return{
            activate: activate
        }

    }
    productListService.$inject = [];

    angular.module('zen.components.productList')
    .factory('productListService', productListService);

})(window, window.angular, window._);
