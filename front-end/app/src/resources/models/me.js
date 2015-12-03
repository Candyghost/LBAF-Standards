'use strict';

angular.module('app').service('Me', function(Dao) {

    var Model = Dao.Me;

    Model.const = {
        ROLES: {
            ADMIN                      : 'ADMIN',
            CUSTOMER                   : 'CUSTOMER'
        },
        RIGHTS: {
            GET_ME                     : 'GET_ME',
            GET_ALL_BOOK               : 'GET_ALL_BOOK',
            ACCESS_KEYCOPTER           : 'ACCESS_KEYCOPTER',
            ACCESS_TEAMCOPTER          : 'ACCESS_TEAMCOPTER'
        }
    };

    Model.prototype.hasRight = function(right){
        return this.rights.indexOf(right) !== -1;
    };

    return Model;
});