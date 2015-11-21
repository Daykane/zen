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
    }

    function productListLink($scope, element, attrs){
        $scope.context = attrs.context;

    }

    function productListController($scope, productListService, $filter, Products, Categories, authenticationService, attrs){
        // Private variables
        var self = this;

        // Private methods

        // Public variables
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.categoryFilter = "";

        //$scope.categories = Categories.query();
        $scope.categories = [{"categoryId":1,"categoryName":"CategoryName","categoryDesc":"Description","validationDate":null}, {"categoryId":2,"categoryName":"Deuxieme","categoryDesc":"Description","validationDate":null}, {"categoryId":3,"categoryName":"cat1","categoryDesc":"test1","validationDate":null}];
        $scope.products = Products.query();

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
            var productsFiltered = $scope.products;
            productsFiltered = $filter('filter')(productsFiltered, $scope.searchBar);
            productsFiltered = $filter('filter')(productsFiltered, $scope.filterProducts);
            productsFiltered = $filter('orderBy')(productsFiltered, $scope.orderProps);
            return productsFiltered
                //product in products | filter:searchBar | filter: filterProducts | orderBy:orderProps | startFrom:currentPage*pageSize | limitTo:pageSize"
        }

        $scope.pageCount = function() {
            var products = $scope.productsFiltered();
            return Math.ceil(products.length/$scope.pageSize)-1;
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

    productListController.$inject = ['$scope', 'productListService', '$filter','Products', 'Categories', 'authenticationService'];


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
