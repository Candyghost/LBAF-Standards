'use strict';

angular.module('app').service('Book', function(Dao) {

    var Model = Dao.Book;

    return Model;

});