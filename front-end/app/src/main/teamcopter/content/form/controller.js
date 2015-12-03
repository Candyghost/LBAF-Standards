'use strict';

angular.module('app.teamcopter').controller('TeamCopterFormCtrl', function ($scope, Book, $stateParams, $state) {

    if($stateParams.id){
        $scope.book = Book.get({id: $stateParams.id});
    } else{
        $scope.book = new Book();
    }

    $scope.submitBookForm = function(){
        $scope.book.$save(function(){
            $state.go('teamcopter-home');
        });
    };

});