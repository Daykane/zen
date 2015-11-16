(function(window, angular, _){
    'use strict';

    function productList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/product-list/product-list.html',
            scope: {},
            bindToController: true,
            controller: productListController,
            controllerAs: productList,
        };
    }

    function productListController($scope, productListService){
        // Private variables
        var self = this,
            items;

        // Private methods
        function loadProducts(){
            self.items = productListService.loadProducts();
        }

        // Public variables
        self.items = items;

        // Public methods

        // Init
        productListService.activate();
        loadProducts();
    }

    angular.module('zen.components.productList', [
            'zen.services'
        ])
        .directive('zenStoryList', productList)
        .controller('productListController', productListController);
})(window, window.angular, window._);
