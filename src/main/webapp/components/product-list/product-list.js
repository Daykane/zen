(function(window, angular, _){
    'use strict';

    function productList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/product-list/product-list.html',
            scope: {},
            controller: productListController
        };
    }

    function productListController($scope, productListService, productFactory){
        // Private variables
        var self = this,
            items = null;

        // Private methods
        
        // Public variables
        self.items = items;

        // Public methods

        // Init
    }
    productListController.$inject = ['$scope', 'productListService', 'productFactory'];

    angular.module('zen.components.productList', [
            'zen.api.products',
            'zen.services'
        ])
        .directive('zenProductList', productList)
        .controller('productListController', productListController);
})(window, window.angular, window._);
