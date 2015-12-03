'use strict';

angular.module('app.teamcopter').controller('TeamCopterViewCtrl', function ($scope, Book, $stateParams) {

    $scope.book = Book.get({id: $stateParams.id});

});