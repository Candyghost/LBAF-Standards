# eFactory Scaffolding

## Aim
The aim of this project is to propose to eFactory bundle a scaffolding project for the new projects of the bundle.
The projects scaffolding-based are always designed with the same architecture (unified architecture) and with the same features (main features of the eFactory bundle). The features are in this chapter

### Back-end features ###

- Unit tests

All application layers are testable (data, service, web). 
Data layer: all CRUD operations and specific queries are tested through a memory database (dbunit). 
Service layer: to cover all requirements in tests, it is possible to use Mockito framework to adapt any input data to check the output. 
Web layer: even the web layer is testable with the Spring MVC-test. It allows us to check the REST service results.

- SAP Netweaver integration (production environment)

Each application (scaffolding-based) are SAP Netweaver compliant (JEE 6, JPA 1.0, Servlet API 2.5, Authentication API, etc.).

- Apache Tomcat integration (development environment)

To improve the development, Apache Tomcat is used. This lighter container simplifies the development.

- Packaging

The back-end and the front-end are packaged into a unique Web application, in an EAR file.

- External i18n

All messages used by the IHM are stored in the file system and can be changed with hot reloading.

- External configuration

Some parameters of configuration can be stored in the file system and can be changed with hot reloading.

- External logging

The logging configuration of the application is stored in the file system and can be changed with hot reloading. For example, the logging level can be changed without restart the application.

- Front-end error handling

All frontend errors are traced into the back-end server logs files.

- Security layer

All user with his roles are managed by the application easily (with a Spring annotation).

- REST error handler (HTTP status code management)

In case of technical or functional exception, a response with a specific HTTP status code is returns. For example, on query request, if there is no result, the HTTP status code will be 404.

- Crossdomain queries management

In development phase, the cross domain check is disabled (a back-end can be used from a web application that is not in the same domain.
In production phase, for security reasons, the cross domain check is enabled.

- REST compliant

All resources that comprise the application are reachable through a complete REST server.

- Health Monitoring

The application has a health endpoint to check the status of application context and database connection.

[View back-end detail utilisation here](http://gitlab-toulouse.sqli.com/eurocopter/efactory-scaffolding/blob/master/back-end/README.md "View back-end detail")

### Front-end features ###
- Unit tests

Used with Karma & Jasmine frameworks.

- Three application modes
	- Stub (front-end uses standalone without any back-end server)
	- Dev (front-end uses a development back-end server)
	- Prod (front-end uses a production back-end server)

- Packaging

With a Node.js module (Gulp), the application is packaged from source files to compressed files (minimization). A SASS transcription is done too.

- eFactory graphical integration

Any scaffolding-based project integrates the Chameleon framework. Chameleon is a graphical framework, Bootstrap 3-based to provide homogenous graphical interfaces with UI components. 

- Many Angular modules integration

Designed throughout the eFactory's projects, a lot of Angular modules have been capitalized. Indeed, for example, there are an offline module, an excel report module, a Fusion Charts integration module, an advanced tables module, etc.

- i18n

All messages used by the IHM are provided by the back-end.

- HTML5 cache

To improve the DOM loading, the HTML5 cache is used to store the entire application.

- REST compliant

All resources that comprise the application are retrieved through a complete REST client implementation.


[View front-end detail utilisation here](http://gitlab-toulouse.sqli.com/eurocopter/efactory-scaffolding/blob/master/front-end/README.md "View front-end detail")

## Commands

### Initialisation ###
Init the scaffolding project for another efactory project. Sepcify app without special character :

	mvn prepare-package -Pscaffolding -Dapp=<app>

### Packaging ###
Package the application with front-end and back-end in the same application (ear file) :

	mvn clean install 
It is also possible to change the default packaging configuration by modifying theses parameters:

- gulp_target: packaging module of the front-end (default: dist:production)
- profiles: list of profiles used for the back-end packaging (default: package)
- application.context: web context used by the application (default: <app>)
- jndi.name: SQL jndi used by the application (default: jdbc/corp.airbus.helicopters.jdbc.<app>)

## Roadmap 
- Backend : delivery goal correction
