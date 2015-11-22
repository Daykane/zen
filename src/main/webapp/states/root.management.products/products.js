(function(window, angular){
    'use strict';

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

    function productsController($state, $scope, authenticationService, $http, apiUrl){
        
    	$scope.name="";
    	$scope.price="";
    	$scope.category="";
    	$scope.quantity= "";
    	$scope.description="";
    	
    	$scope.createProduct=function(){
    		
    		if($scope.name!="" && $scope.price!="" && $scope.category!="" && $scope.category!="" && $scope.quantity!= "" && $scope.description!="" && !isNaN($scope.price) && !isNaN($scope.quantity)){
    			var data={productName: $scope.name, productDescr: $scope.description, availableQuantity: $scope.quantity, categoryProduct: 1, price: $scope.price};
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
                  }, function errorCallback(response) {
                	  
                  });
    		}
    		
    	};
    	
    }
    
    productsController.$inject = ['$state', '$scope', 'authenticationService', '$http', 'apiUrl'];

    angular.module('zen.states.products', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(productsConfig)
    .run(productsRun)
    .controller('productsController', productsController);

})(window, window.angular);
