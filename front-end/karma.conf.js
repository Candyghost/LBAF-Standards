module.exports = function (config) {
    config.set({

        frameworks: ['jasmine'],

        autoWatch: true,
        singleRun: false,

        exclude: [
            '**/e2e/**/*.js'
        ],

        files: [
            'test/bind-polyfill.js',
            'app/bower_components/jquery/dist/jquery.js',
            'app/bower_components/yepnope/yepnope.js',
            'app/bower_components/efactory-ui-chameleon/dist/js/bootstrap.min.js',
            'app/bower_components/angular/angular.min.js',
            'app/bower_components/angular-loading-bar/build/loading-bar.js',
            'app/bower_components/angular-resource/angular-resource.min.js',
            'app/bower_components/angular-mocks/angular-mocks.js',
            'app/bower_components/angular-ui-router/release/angular-ui-router.js',
            'app/bower_components/angular-translate/angular-translate.js',
            'app/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
            'app/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
            'app/bower_components/angular-translate-loader-url/angular-translate-loader-url.js',
            'app/bower_components/angular-animate/angular-animate.js',
            'app/bower_components/angular-strap/dist/angular-strap.js',
            'app/bower_components/fusion-charts/fusioncharts-xt-enterprise-plus/js/fusioncharts.js',
            'app/bower_components/fusion-charts/fusioncharts-xt-enterprise-plus/js/fusioncharts.charts.js',
            'app/bower_components/fusion-charts/powercharts-xt-enterprise-plus/js/fusioncharts.powercharts.js',
            'app/bower_components/fusion-charts/fusionmaps-xt-enterprise-plus/js/fusioncharts.maps.js',
            'app/bower_components/fusion-charts/fusionwidgets-xt-enterprise-plus/js/fusioncharts.gantt.js',
            'app/bower_components/fusion-charts/fusionwidgets-xt-enterprise-plus/js/fusioncharts.widgets.js',

            'app/bower_components/select2/select2.js',
            'app/bower_components/ui-select/dist/select.js',
            'app/bower_components/tinymce/tinymce.min.js',
            'app/bower_components/angular-ui-tinymce/src/tinymce.js',
            'app/bower_components/angular-sanitize/angular-sanitize.js',
            'app/bower_components/hyperagent/dist/hyperagent.js',

            'app/bower_components/efactory-angular-modules/app-hal-resource/src/module.js',
            'app/bower_components/efactory-angular-modules/app-cache/module.js',
            'app/bower_components/efactory-angular-modules/app-notification/module.js',
            'app/bower_components/efactory-angular-modules/app-security/module.js',
            'app/bower_components/efactory-angular-modules/app-dialog/module.js',
            'app/bower_components/efactory-angular-modules/app-interceptor/module-stub.js',
            'app/bower_components/efactory-angular-modules/app-interceptor/module-dev.js',
            'app/bower_components/efactory-angular-modules/app-resource/module.js',
            'app/bower_components/efactory-angular-modules/app-table/module.js',
            'app/bower_components/efactory-angular-modules/app-table/service.js',
            'app/bower_components/efactory-angular-modules/app-graph/module.js',
            'app/bower_components/efactory-angular-modules/app-graph/genericChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-graph/pieChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-graph/barChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-graph/combiBarChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-graph/curveChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-graph/heatMapChart-directive.js',
            'app/bower_components/efactory-angular-modules/app-datepicker/module.js',
            'app/bower_components/efactory-angular-modules/app-richtext/module.js',
            'app/bower_components/efactory-angular-modules/app-i18n/module.js',
            'app/bower_components/efactory-angular-modules/app-i18n/module-stub.js',
            'app/bower_components/efactory-angular-modules/app-html-unsafe/module.js',
            'app/bower_components/efactory-angular-modules/app-interval/module.js',
            'app/bower_components/efactory-angular-modules/app-safequit/module.js',
            'app/bower_components/efactory-angular-modules/app-validator/module.js',
            'app/bower_components/efactory-angular-modules/app-selectgroup/module.js',
            'app/bower_components/efactory-angular-modules/app-file-export/module.js',
            'app/bower_components/efactory-angular-modules/app-file-export/excel-html-service.js',
            'app/bower_components/efactory-angular-modules/app-error-handling/module.js',
            'app/bower_components/efactory-angular-modules/app-truncated-text/module.js',
            'app/bower_components/efactory-angular-modules/app-check-browser/module.js',
            'app/bower_components/efactory-angular-modules/app-keycopter-api/module.js',
            'app/bower_components/efactory-angular-modules/app-action-tooltip/module.js',
            'app/bower_components/efactory-angular-modules/app-mock/module.js',
            'app/bower_components/efactory-angular-modules/app-download-file/module.js',

            'app/src/main/keycopter/module.js',
            'app/src/main/teamcopter/module.js',
            'app/src/app.js',
            'app/src/appStub.js',
            'app/src/resources/**/*.js',
            'app/src/main/**/*.js',
            'test/unit/**/*.js'
        ],

        browsers: ['PhantomJS'],

        reporters: ['progress', 'coverage', 'junit'],

        preprocessors: {
            'app/src/**/*.js': 'coverage'
        },

        coverageReporter: {
            type: 'lcov',
            dir: 'reports/coverage/'
        },

        junitReporter: {
            outputFile: 'reports/tu.xml'
        }

    });
};