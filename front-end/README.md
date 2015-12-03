# Front-end

myApp - Airbus Helicopters project.

## Environment

-   [Node.js](http://nodejs.org/)
-   [Ruby](https://www.ruby-lang.org/fr/)
-   [Compass](http://compass-style.org/install/)
-   [Gulp](http://gulpjs.com/)
-   [Bower](http://bower.io/)

## Getting started

From the source code, execute these commands:

    cd myApp/front-end
    npm install
    bower install
    gulp serve

## Check installation
Check that all unit tests are OK.

	gulp test

## Commands

### gulp tool

Start a dev mode server

	gulp

Start a dev mode server (folder app)

	gulp serve

Start a production mode server stubbed (folder dist)

	gulp serve:stub

Start a production mode server for local backend (folder dist)

	gulp serve:dev

Start a production mode server (folder dist)

	gulp serve:production

Start unit and e2e tests (require a proxy server at localhost:9000)

	gulp test

Start unit tests

	gulp test:unit

Package the application

	gulp dist

Package the application for production (app)

	gulp dist:production

Package the application for dev mode (backend :8080)

	gulp dist:dev

Package the application for stub mode

	gulp dist:stub 

### Maven tool

Package the application (same effect of the "gulp dist" command

	mvn clean install
