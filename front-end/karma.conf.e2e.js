module.exports = function (config) {
    config.set({

        basePath: 'test/e2e/',

        browserNoActivityTimeout: 60000,

        logLevel: config.LOG_INFO,

        files: [
            //'../../app/src/modules/app-table/test/**/*.js',
            '**/*.js'
        ],

        autoWatch: false,

        urlRoot: '/_karma_/',

        browsers: [
            //'IE',
            //'Chrome',
            //'Firefox',
            'PhantomJS'
        ],

        frameworks: ['ng-scenario'],

        singleRun: false,

        proxies: {
            '/': 'http://localhost:9002/'
        },

        plugins: [
            'karma-ng-scenario',
            'karma-ie-launcher',
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-phantomjs-launcher',
            'karma-junit-reporter'
        ],

        reporters: ['progress', 'junit'],

        junitReporter: {
            outputFile: '../../reports/e2e.xml',
            suite: 'e2e'
        }

    });
};