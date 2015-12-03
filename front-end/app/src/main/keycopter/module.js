'use strict';

/**
 * @requirement LM-KHOME-GEN-020-1 LM-EXTFH-GEN-010-1 LM-EXTEV-GEN-010-1
 */
angular.module('app.keycopter', [
    'ui.router',
    'ngResource',
    'pascalprecht.translate',
    'app.security',
    'app.keycopter.portal'
]).config(function ($stateProvider) {
        $stateProvider
            .state('keycopter', {
                abstract: true,
                url: '/keycopter',
                template: '<div ui-view></div>',
                parent: 'main',
                onEnter: function (Me, $security) {
                    $security.secure([Me.const.RIGHTS.ACCESS_KEYCOPTER]);
                }
            })
            .state('keycopter-home', {
                parent: 'keycopter',
                url: '/home',
                templateUrl: 'src/main/keycopter/content/home/view.html',
                controller: 'KeyCopterHomeCtrl',
                resolve: {
                    i18n: function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('keycopter-home');
                        return $translate.refresh();
                    }
                },
                onEnter: function ($keycopterPortal, $translate) {
                    $keycopterPortal.breadcrumb.replace($translate.instant('keycopter.home.breadcrumb.title'));
                }
            });

    }
);