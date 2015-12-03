'use strict';

angular.module('app.teamcopter')
    .directive('appMenu', function () {

        return {
            templateUrl: 'src/main/teamcopter/menu/view.html',
            link: function($scope, element){
                element.find('#menu').leftMenu();
            }
        };

    });