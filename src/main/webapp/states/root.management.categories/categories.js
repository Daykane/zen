(function(window, angular){
    

    function categoriesConfig($stateProvider){
        $stateProvider.state('root.management.categories', {
            url: '/management/categories',
            templateUrl: 'states/root.management.categories/categories.html',
            controller: 'categoriesController as categoriesState'
        });
    }
    categoriesConfig.$inject = ['$stateProvider'];

    function categoriesRun (){
    }

     function categoriesController($scope, Categories){
        $scope.searchBar = ""
        $scope.categories=Categories.getAll();
    }
    categoriesController.$inject = ['$scope', 'Categories'];

    angular.module('zen.states.categories', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(categoriesConfig)
    .run(categoriesRun)
    .controller('categoriesController', categoriesController);

})(window, window.angular);
