(function(window, angular){
    

    function productsConfig($stateProvider){
        $stateProvider.state('root.management.products', {
            url: '/management/products',
            templateUrl: 'states/root.management.products/products.html',
            controller: 'productsController as productsState'
        });
    }
    productsConfig.$inject = ['$stateProvider'];

    function productsRun (){
    }

    function productsController($state, $scope, authenticationService, $http, apiUrl, $filter,Products, Categories){
        
    	$scope.name="";
    	$scope.price="";
    	$scope.category="";
    	$scope.quantity= "";
    	$scope.description="";
    	
    	$scope.createProduct=function(){
    		if($scope.name!="" && $scope.category!="" && $scope.price!="" && $scope.quantity!= "" && $scope.description!="" && !isNaN($scope.price) && !isNaN($scope.quantity)){

    			var data={productName: $scope.name, productDescr: $scope.description, availableQuantity: $scope.quantity, categoryProduct: $scope.category, price: $scope.price};
        		$http({
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "token": authenticationService.getCookieToken
                    },
                    dataType: "json",
                    url: apiUrl+'Products',
                    data: JSON.stringify(data),
                }).then(function successCallback(response) {
                	$scope.name="";
                	$scope.price="";
                	$scope.category="";
                	$scope.quantity= "";
                	$scope.description="";
                	Products.query(function(products){
                        $scope.products = products;
                    });
                  }, function errorCallback(response) {
                	  
                  });
    		}
    		
    	};
    	
    	$scope.deleteProduct=function(product){
    		Products.delete({productId: product.productId}, function(){$scope.products=Products.query();});
    	};
    	
    	
    	
    	$scope.authentication= authenticationService;
    	$scope.searchBar = "";
        $scope.orderProps = "";
        $scope.currentPage = 0;
        $scope.pageSize = 5;
        $scope.categoryFilter = "";

        $scope.categories = Categories.getAll();

        $scope.products = Products.query();

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
    
    productsController.$inject = ['$state', '$scope', 'authenticationService', '$http', 'apiUrl', '$filter','Products', 'Categories',];

    angular.module('zen.states.products', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(productsConfig)
    .run(productsRun)
    .controller('productsController', productsController)
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
