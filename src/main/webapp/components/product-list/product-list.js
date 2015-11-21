(function(window, angular, _){
    'use strict';

    function productList(){
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'components/product-list/product-list.html',
            scope: {},
            controller: productListController,
            link: productListLink,
            comple: productListCompile
        };
    }

    function productListCompile($scope, element, attrs){
         $scope.products = Products.query();
        $scope.categories = [{"categoryId":1,"categoryName":"CategoryName","categoryDesc":"Description","validationDate":null},{"categoryId":2,"categoryName":"Deuxieme","categoryDesc":"Description","validationDate":null},{"categoryId":3,"categoryName":"cat1","categoryDesc":"test1","validationDate":null}];
    }

    function productListLink($scope, element, attrs){
        $scope.context = attrs.context;
    }

    function productListController($scope, productListService, Products, $filter, Categories){
        // Private variables
        var self = this;

        // Private methods

        // Public variables
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.categoryFilter = "";
        $scope.products = Products.query();
        $scope.categories = Categories.query();

        //$scope.categories = ProductCategories.query();

        // Public methods
        $scope.filterProducts = function (product) {
        return !$scope.categoryFilter ? 
                   product : (product.categoryProduct == $scope.categoryFilter);
        };

        $scope.prevPage = function() {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.setPage = function(n) {
            $scope.currentPage=n;
        };

        $scope.prevPageDisabled = function() {
            return $scope.currentPage === 0 ? "disabled" : "";
        };
        
        $scope.productsFiltered = function (){


        }

        $scope.pageCount = function() {
            productsFiltered = $scope.products;
            productFiltered = $filter('')
            return Math.ceil($scope.products.length/$scope.pageSize)-1;
        };

        $scope.nextPage = function() {
            if ($scope.currentPage < $scope.pageCount()) {
                $scope.currentPage++;
            }
        };

        $scope.nextPageDisabled = function() {
            return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
        };
        //Init
    }

    productListController.$inject = ['$scope', 'productListService', 'Products', '$filter', 'Categories'];


    angular.module('zen.components.productList', [
        'zen.api.products',
        'zen.api.categories',
        'zen.services'
        ])
    .directive('zenProductList', productList)
    .controller('productListController', productListController)
    .filter('startFrom', function() {
        return function(input, start) {
            start = +start; //parse to int
            return input.slice(start);
        }
    })
    .filter('range', function() {
        return function(input) {
            var lowBound, highBound;
            switch (input.length) {
                case 1:
                lowBound = 0;
                highBound = parseInt(input[0]) - 1;
                break;
                case 2:
                lowBound = parseInt(input[0]);
                highBound = parseInt(input[1]);
                break;
                default:
                return input;
            }
            var result = [];
            for (var i = lowBound; i <= highBound; i++)
                result.push(i);
            return result;
        };
    });
})(window, window.angular, window._);
