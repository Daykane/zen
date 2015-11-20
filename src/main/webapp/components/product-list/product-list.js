(function(window, angular, _){
    'use strict';

    function productList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/product-list/product-list.html',
            scope: {},
            controller: productListController,
            link: productListLink
        };
    }

    function productListLink($scope, element, attrs){
        $scope.context = attrs.context;
    }

    function productListController($scope, productListService, productFactory, $filter){
        // Private variables
        var self = this;

        // Private methods

        // Public variables
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.products = productFactory.query();

        // Public methods
        $scope.numberOfPages = function(){
            var products;
            products = $scope.products;
            products = $filter('filter')(products, $scope.searchBar);
            products = $filter('orderBy')(products, $scope.orderProps);
            return Math.max(Math.ceil(products.length/$scope.pageSize),1);
        }
        //Init
    }

    productListController.$inject = ['$scope', 'productListService', 'productFactory', '$filter'];

    angular.module('zen.components.productList', [
            'zen.api.products',
            'zen.services'
        ])
        .directive('zenProductList', productList)
        .controller('productListController', productListController)
        .filter('startFrom', function() {
            return function(input, start) {
                start = +start; //parse to int
                return input.slice(start);
            }
        });
})(window, window.angular, window._);
