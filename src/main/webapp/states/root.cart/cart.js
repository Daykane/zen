(function(window, angular){
    'use strict';

    function cartConfig($stateProvider){
        $stateProvider.state('root.cart', {
            url: '/cart',
            templateUrl: 'states/root.cart/cart.html',
            controller: 'cartController as cartState'
        });
    }
    cartConfig.$inject = ['$stateProvider'];

    function cartRun (){
    }

    function cartController($scope, productListService, $filter, Categories, authenticationService, attrs){

       // Private variables
        var self = this;

        // Private methods

        // Public variables
        $scope.authentication = authenticationService;
        $scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.categoryFilter = "";

        //$scope.categories = Categories.query();
        $scope.categories = [
            {"categoryId":1,"categoryName":"CategoryName","categoryDesc":"Description","validationDate":null}, 
            {"categoryId":2,"categoryName":"Deuxieme","categoryDesc":"Description","validationDate":null}, 
            {"categoryId":3,"categoryName":"cat1","categoryDesc":"test1","validationDate":null}];
        //$scope.products = Products.query();
        $scope.products = authenticationService.cart;

        // Public methods
        $scope.order = function(){
             if (confirm("sure to delete")) {
            // todo code for deletion
                window.alert("Order sent !");
                $scope.products = [];
                authenticationService.cart = [];
            }
        }

        $scope.remove = function(product){
            var deleted = false;
            var results = $filter('filter')($scope.products, function(elem){
                if (deleted){
                    return true;
                }
                else{
                    if (elem == product){
                        deleted=true;
                        return false;
                    }
                    else{
                        return true
                    }
                }
            });

            $scope.products = results;
            authenticationService.cart = results;
        }

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
    cartController.$inject = ['$scope', 'productListService', '$filter', 'Categories', 'authenticationService'];

    angular.module('zen.states.cart', [
        'zen.api.products',
        'zen.api.categories',
        'ui.router',
        'zen.states.root',
        'zen.services'
    ])
    .config(cartConfig)
    .run(cartRun)
    .controller('cartController', cartController)
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

})(window, window.angular);
