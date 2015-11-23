(function(window, angular){
    

    function usersConfig($stateProvider){
        $stateProvider.state('root.management.users', {
            url: '/management/users',
            templateUrl: 'states/root.management.users/users.html',
            controller: 'usersController as usersState'
        });
    }
    usersConfig.$inject = ['$stateProvider'];

    function usersRun (){
    }

    function usersController($scope, Users){
        $scope.searchBar = ""
        $scope.users=Users.crud().query();
    }
    usersController.$inject = ['$scope', 'Users'];

    angular.module('zen.states.users', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(usersConfig)
    .run(usersRun)
    .controller('usersController', usersController);

})(window, window.angular);
