'use strict';

angular.module('app').service('Dao', function (Resource, settings) {

    return {
        Me: Resource('Me', settings.restEndpoint + 'me', {}),
        Book: Resource('Book', settings.restEndpoint + 'books/:id', {id: '@id'}, {
            getPdf: {
                method: 'GET',
                params:{pdf:true}
            }
        }),
        Error: Resource('Error', settings.restEndpoint + 'errors', {})
    };
});