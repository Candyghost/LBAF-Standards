'use strict';

var gulp = require('gulp');
var open = require('open');
var app = require('./bower.json');

// Load plugins
var $ = require('gulp-load-plugins')();

var server;

var port = {
	app: 9000,
	dist: 9001,
	test: 9002
};

// Styles
gulp.task('styles', ['clean'], function () {
	return gulp.src('app/src/main/style.scss')
		.pipe($.plumber())
		.pipe($.sass())
		.pipe($.autoprefixer('last 1 version'))
		.pipe(gulp.dest('app/'));
});

// Copy
gulp.task('copy', ['styles'], function () {
	gulp.src(['app/bower_components/jquery-ui/themes/base/images/**/*']).pipe(gulp.dest('dist/jquery-ui/themes/base/images/'));
    gulp.src(['app/bower_components/efactory-ui-chameleon/dist/fonts/*']).pipe(gulp.dest('dist/fonts'));
    gulp.src(['app/bower_components/efactory-ui-chameleon/dist/themes-img/**/*']).pipe(gulp.dest('dist/themes-img'));
    gulp.src(['app/bower_components/tinymce-dist/skins/**/*']).pipe(gulp.dest('dist/scripts/skins'));
    gulp.src(['app/bower_components/select2/*.gif']).pipe(gulp.dest('dist/styles'));
	gulp.src(['app/bower_components/select2/*.png']).pipe(gulp.dest('dist/styles'));
    gulp.src(['app/i18n/**/*.json']).pipe(gulp.dest('dist/i18n'));
	gulp.src(['app/stub/**/*.json']).pipe(gulp.dest('dist/stub'));
	gulp.src(['app/META-INF/**/*.*']).pipe(gulp.dest('dist/META-INF'));
	return gulp.src([
		'app/index.html',
		'app/robots.txt'
	]).pipe(gulp.dest('dist'));
});

// Templates
gulp.task('template', ['html'], function () {
	return gulp.src(['app/src/**/*.html'])
		.pipe($.angularTemplatecache({module: 'app', root: 'src/'}))
		.pipe(gulp.dest('dist/scripts'));
});

// HTML
gulp.task('html', ['copy'], function () {
	var jsFilter = $.filter('**/*.js');
	var cssFilter = $.filter('**/*.css');

	return gulp.src('app/index.html')
		.pipe($.replace(/<html/, '<html manifest="app.manifest"'))
		.pipe($.replace(/ng\-app="appDev"/, 'ng-app="app"'))
		.pipe($.replace(/ng\-app="appStub"/, 'ng-app="app"'))
		.pipe($.useref.assets())
		.pipe(jsFilter)
		.pipe($.ngAnnotate())
		.pipe($.uglify({
			output: {
				ascii_only: true
			}
		}))
		.pipe(jsFilter.restore())
		.pipe(cssFilter)
		.pipe($.csso())
		.pipe(cssFilter.restore())
		.pipe($.useref.restore())
		.pipe($.useref())
		.pipe(gulp.dest('dist'));
});

// Clean
gulp.task('clean', function () {
	return gulp.src(['dist'], { read: false }).pipe($.clean());
});

// Build
gulp.task('dist', ['template'], function () {
	return gulp.src([
		'dist/**/*.js',
		'dist/**/*.css'
	])
		.pipe($.manifest({
			hash: true,
			filename: 'app.manifest',
			exclude: 'app.manifest'
		}))
		.pipe(gulp.dest('dist'));
});

gulp.task('dist:production', ['dist'], function () {
	return gulp.src('dist/index.html')
		.pipe($.replace(/ng\-app="appDev"/, 'ng-app="app"'))
		.pipe($.replace(/ng\-app="appStub"/, 'ng-app="app"'))
		.pipe(gulp.dest('dist'));
});

gulp.task('dist:dev', ['dist'], function () {
	return gulp.src('dist/index.html')
		.pipe($.replace(/ng\-app="app"/, 'ng-app="appDev"'))
		.pipe($.replace(/ng\-app="appStub"/, 'ng-app="appDev"'))
		.pipe(gulp.dest('dist'));
});

gulp.task('dist:stub', ['dist'], function () {
	return gulp.src('dist/index.html')
		.pipe($.replace(/ng\-app="appDev"/, 'ng-app="appStub"'))
		.pipe($.replace(/ng\-app="app"/, 'ng-app="appStub"'))
		.pipe(gulp.dest('dist'));
});

// Default task
gulp.task('default', ['serve']);

gulp.task('server:app', ['styles'], function () {
	$.connect.server({
		root: ['app'],
		port: port.app,
		livereload: true
	});
	open('http://localhost:' + port.app + '/#/auth');
});

gulp.task('serve:e2e', [], function () {
	$.connect.server({
		root: ['app'],
		port: port.test
	});
});

gulp.task('serve:production', ['dist:production'], function () {
	$.connect.server({
		root: ['dist'],
		port: port.dist
	});
	open('http://localhost:' + port.dist);
});

gulp.task('serve:dev', ['dist:dev'], function () {
	$.connect.server({
		root: ['dist'],
		port: port.dist
	});
	open('http://localhost:' + port.dist + '/#/auth');
});

gulp.task('serve:stub', ['dist:stub'], function () {
	$.connect.server({
		root: ['dist'],
		port: port.dist
	});
	open('http://localhost:' + port.dist + '/#/auth');
});

// Test
gulp.task('test:unit', function () {
	return gulp.src('not-exist.js').pipe($.plumber()).pipe($.karma({configFile: 'karma.conf.js'}));
});
gulp.task('test:e2e', ['serve:e2e'], function () {
	return gulp.src('not-exist.js').pipe($.plumber()).pipe($.karma({configFile: 'karma.conf.e2e.js', action: 'run'})).on('end', function () {
		$.connect.serverClose();
	}).on('error', function () {
		$.connect.serverClose();
	});
});

// Watch
gulp.task('serve', ['server:app'], function () {
	// Watch for changes in `app` folder
	gulp.watch([
		'app/index.html',
		'app/style.css',
		'app/src/**/*.html',
		'app/src/**/*.js',
		'app/modules/**/*.js'
	], function (event) {
		return gulp.src(event.path)
			.pipe($.connect.reload());
	});

	// Watch .scss files
	gulp.watch('app/src/**/*.scss', ['styles']);
});

// Test
gulp.task('test', ['test:unit', 'test:e2e']);

