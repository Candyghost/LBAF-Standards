'use strict';

angular.module('app').service('Company', function(Dao) {

    var Model = Dao.Company;

    return Model;
});