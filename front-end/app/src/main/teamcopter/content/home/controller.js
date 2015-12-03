'use strict';

angular.module('app.teamcopter').controller('TeamCopterHomeCtrl', function ($scope, Book, $translate, DownloadService) {

    $scope.books = Book.query();

    $scope.options = {
        filter :{
            global: true
        }
    };

    $scope.columns = [
        {
            'sTitle': $translate.instant('teamcopter.home.book.table.column.title'),
            'mDataProp': 'title'
        },
        {
            'sTitle': $translate.instant('teamcopter.home.book.table.column.description'),
            'mDataProp': 'description'
        },
        {
            'sTitle': $translate.instant('teamcopter.home.book.table.column.action'),
            'mDataProp': null,
            'bSortable': false,
            'fnRender': function (row) {
                var html = '<a ui-sref="teamcopter-book-view({id: ' + row.aData.id + '})"><span class="icon icon-search"></span></a>&nbsp;&nbsp;';
                html += '<a ui-sref="teamcopter-book-edit({id: ' + row.aData.id + '})"><span class="icon icon-edit"></span></a>&nbsp;&nbsp;';
                html += '<a ng-click="deleteBook(' + row.aData.id + ')"><span class="icon icon-delete"></span></a>&nbsp;&nbsp;';
                html += '<a ng-click="downloadBookAsPdf(' + row.aData.id + ')"><span class="icon icon-pdf"></span></a>&nbsp;&nbsp;';
                return html;
            }
        }
    ];

    $scope.deleteBook = function(id){
        var book = new Book({id: id});
        book.$delete().then(function(){
            $scope.books = $scope.books.filter(function(book){
                return book.id !== id;
            });
        });
    };

    $scope.downloadBookAsPdf = function(id){
        Book.getPdf({id: id}).$promise.then(function (response) {
            DownloadService.fromData(response.content, response.name);
        });
    };

});