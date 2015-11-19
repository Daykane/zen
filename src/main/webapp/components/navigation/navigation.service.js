//TODO

(function(window, angular, _){
    'use strict';

    function navigationService($state){
        // Private variables

        // Private functions
        function activate(){}

        function reset(){}

        function goToMyActivities(){
            $state.go('root.myActivities');
        }

        function goToStore(){
            $state.go('root.store');
        }

        function goToUsers(){
            $state.go('root.management.users');
        }

        function goToActivities(){
            $state.go('root.management.activities');
        }
        function goToEvents(){
            $state.go('root.management.events');
        }

        function goToCategories(){
            $state.go('root.management.categories');
        }

        function goToProducts(){
            $state.go('root.management.products');
        }

        function goToRooms(){
            $state.go('root.management.rooms');
        }

        function goToAccessories(){
            $state.go('root.management.accessories');
        }

        function login(){
            $state.go('root.login');
        }

        function logout(){
            $state.go('root.home');
        }
        
        function goToSettings(){
            $state.go('root.settings');
        }
        
        // Public
        return {
            activate: activate,
            reset: reset,
            login: login,
            logout: logout,
            goToSettings: goToSettings,
            goToStore: goToStore,
            goToMyActivities: goToMyActivities,
            goToEvents: goToEvents,
            goToProducts: goToProducts,
            goToAccessories: goToAccessories,
            goToUsers: goToUsers,
            goToProducts: goToProducts,
            goToRooms: goToRooms,
            goToCategories: goToCategories,
            goToActivities: goToActivities,
        };
    }
    navigationService.$inject = ['$state'];

    angular.module('zen.components.navigation')
    .factory('navigationService', navigationService);

})(window, window.angular, window._);
