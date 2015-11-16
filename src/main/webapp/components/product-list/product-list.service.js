(function(window, angular, _){
    'use strict';

    function productListService(Products){
        // Private variables
        function activate(){}
        // Private methods

        // Public variables

        // Public methods

        function loadStories(items){

            items = Products.getProducts()
        }

    angular.module('zen.components.storyList')
        .config(duplicateExceptionHandler)
        .factory('productListService', productListService);
})(window, window.angular, window._);
