'use strict';

/**
 * @requirement LM-SETGS-GEN-010-1 LM-SETGS-GEN-020-1 LM-SMPLT-GEN-010-1 LM-SMPLT-GEN-020-1 LM-SMPCT-GEN-010-1 LM-SMPCT-GEN-020-1 LM-THOME-GEN-020-1 LM-SMPTK-GEN-010-1 LM-SMPTK-GEN-020-1 LM-TKVFP-GEN-010-1 LM-TKVFP-GEN-020-1 LM-TKVFR-GEN-010-1 LM-TKVFR-GEN-020-1
 */

angular.module('app.teamcopter', [
    'ui.router',
    'ngResource',
    'pascalprecht.translate',
    'app.security'
]).config(function ($stateProvider) {
        $stateProvider
            .state('teamcopter', {
                abstract: true,
                url: '/teamcopter',
                template: '<div app-menu></div><div ui-view></div>',
                parent: 'main',
                onEnter: function(Me, $security){
                    $security.secure([Me.const.RIGHTS.ACCESS_TEAMCOPTER]);
                }
            })
            .state('teamcopter-home', {
                parent: 'teamcopter',
                url: '/home',
                templateUrl: 'src/main/teamcopter/content/home/view.html',
                controller: 'TeamCopterHomeCtrl',
                resolve: {
                    i18n: function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('teamcopter-home');
                        return $translate.refresh();
                    }
                }
            })
            .state('teamcopter-book-edit', {
                parent: 'teamcopter',
                url: '/form/?id',
                templateUrl: 'src/main/teamcopter/content/form/view.html',
                controller: 'TeamCopterFormCtrl',
                resolve: {
                    i18n: function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('teamcopter-form');
                        return $translate.refresh();
                    }
                }
            })
            .state('teamcopter-book-view', {
                parent: 'teamcopter',
                url: '/view/:id',
                templateUrl: 'src/main/teamcopter/content/view/view.html',
                controller: 'TeamCopterViewCtrl',
                resolve: {
                    i18n: function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('teamcopter-view');
                        return $translate.refresh();
                    }
                }
            });

    }
);