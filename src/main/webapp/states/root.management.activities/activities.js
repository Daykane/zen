(function(window, angular){
    

    function activitiesConfig($stateProvider){
        $stateProvider.state('root.management.activities', {
            url: '/management/activities',
            templateUrl: 'states/root.management.activities/activities.html',
            controller: 'activitiesController as activitiesState'
        });
    }
    activitiesConfig.$inject = ['$stateProvider'];

    function activitiesRun (){
    }

    function activitiesController($scope, Activities){
        $scope.searchBar = ""
        $scope.activities=Activities.query();
    }
    activitiesController.$inject = ['$scope', 'Activities'];

    angular.module('zen.states.activities', [
        'ui.router',
        'zen.states.root',
        'zen.states.management',
        'zen.services'
    ])
    .config(activitiesConfig)
    .run(activitiesRun)
    .controller('activitiesController', activitiesController);

})(window, window.angular);
