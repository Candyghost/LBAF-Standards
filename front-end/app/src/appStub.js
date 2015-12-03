'use strict';

angular.module('appStub', [
    'app.mock',
    'app.interceptor.stub',
    'app',
    'app.i18n.stub'
]).config(function ($httpProvider, $urlRouterProvider, $stateProvider) {
    /** Routes */
    $stateProvider
        .state('fake-auth', {
            url: '/auth',
            templateUrl: 'src/main/fake-auth/view.html',
            onEnter: function ($rootScope) {
                $rootScope.ready = true;
            },
            controller: function ($scope, $state, Me) {
                $scope.roles = Me.const.ROLES;
                $scope.loadMe = function (role) {
                    role = role.toLowerCase();
                    sessionStorage.setItem('role', role);
                    location.reload();
                };
                if (sessionStorage.getItem('role')) {
                    $state.go('main');
                }
            }
        });
    /** END Routes */

    /** Interceptor **/
    $httpProvider.interceptors.push('appInterceptorStub');
    /** END OF Interceptor **/

}).run(
    function ($rootScope, mock, $state) {

        $rootScope.$on('$stateChangeSuccess', function (ev, to) {
            if (to.name) {
                sessionStorage.setItem('from', to.name);
            }
        });

        /** Mocked Resources */
        //User
        var role = sessionStorage.getItem('role');
        if (role) {
            mock.get(/rest-api\/me$/, 'stub/app/me/GET-' + role + '.json');
            var $logout = $('<a href style="position: absolute;right: 0;"><span class="icon icon-logout icon-x3"></span></a>');
            $logout.click(function () {
                sessionStorage.removeItem('role');
                $state.go('fake-auth');
            });
            $('body').prepend($logout);
        }

        // Sinequa
        mock.get(/partnumer\?mpn\=[a-zA-Z0-9]+\&searchRequest\=true/, 'stub/sinequa/partnumber/GET.json');

        // ERA
        mock.get(/efactory-rest-api\/efactory\/rest\/api\/$/, 'stub/era/GET-endpoint.json');

        // Book
        mock.get(/rest-api\/books$/, 'stub/app/book/GET.json');
        mock.get(/rest-api\/books\/[0-9]+$/, 'stub/app/book/GET-1.json');
        mock.get(/rest-api\/books\/[0-9]+\?pdf\=true$/, 'stub/app/book/GET-pdf.json');
        mock.delete(/rest-api\/books\/[0-9]+$/, 200);
        mock.post(/rest-api\/books$/, 'stub/app/book/POST.json');
        mock.put(/rest-api\/books\/[0-9]+$/, 200);

        // Error
        mock.post(/rest-api\/errors$/, 'stub/app/error/POST.json');

        mock.else();
        /** END OF Mocked Resources */


    });
