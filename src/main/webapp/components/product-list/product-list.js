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

    $scope.pageCount = function() {
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
