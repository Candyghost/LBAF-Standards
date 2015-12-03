@echo off

:: ASK for the project name
set /p appname=what is you application name (No Spaces)?

:: Contextualize Application in scaffolding
echo ===========================================================
echo Application contextualization for %appname% is in progress
echo ===========================================================
	:: prepare package for scaffolding
	call mvn prepare-package -Pscaffolding -Dapp=%appname%

:: ALL Done
echo ===========================================================
if %ERRORLEVEL% GTR 0 echo Application contextualization for %appname% has failed
if %ERRORLEVEL% EQU 0 echo Application contextualization for %appname% is successful
echo ===========================================================
pause
if %ERRORLEVEL% GTR 0 exit

::Initialize backend
echo ===========================================================
echo Initializing backend for %appname%
echo ===========================================================
	::Go to the backend parent folder
	cd back-end/%appname%-parent
	
	::Get libraries
	call mvn clean install -DskipTests -Partifactory
	
	::Go back to project root
	cd ../..
	
:: ALL Done
echo ===========================================================
if %ERRORLEVEL% GTR 0 echo Backend initialization for %appname% has failed
if %ERRORLEVEL% EQU 0 echo Backend initialization for %appname% is successful
echo ===========================================================
pause
if %ERRORLEVEL% GTR 0 exit

::Generate Eclipse config file
echo ===========================================================
echo Generating Eclipse config file for %appname%
echo ===========================================================
	::Go to the backend parent folder
	cd back-end/%appname%-parent
	
	::Generate Eclipse file
	call mvn eclipse:eclipse -Partifactory
	
	::Go back to project root
	cd ../..
	
:: ALL Done
echo ===========================================================
if %ERRORLEVEL% GTR 0 echo Eclipse Config File for %appname% has failed
if %ERRORLEVEL% EQU 0 echo Eclipse Config File for %appname% is successful
echo ===========================================================
pause
if %ERRORLEVEL% GTR 0 exit

:: Initialize front-end
echo ===========================================================
echo Initializing Front-End
echo ===========================================================
	::Go to the front-end folder
	cd front-end
	
	::Install bower
	echo installing Bower...
	call npm install bower
	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo Bower installation failed
	if %ERRORLEVEL% EQU 0 echo Bower installation successful
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit
	
	::Install node modules
	echo installing node modules...
	call npm install
	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo node modules installation has failed
	if %ERRORLEVEL% EQU 0 echo node modules installation successful
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit
	
	::Install Gulp
	echo installing Gulp...
	call npm install gulp --global
	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo Gulp installation has failed
	if %ERRORLEVEL% EQU 0 echo Gulp installation successful
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit
	
	::Install Bower Modules
	echo installing Bower Modules...
	call bower install
	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo Bower Modules installation has failed
	if %ERRORLEVEL% EQU 0 echo Bower Modules installation successful
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit
	
	::Set the context to stub mode
	echo Seting Front-End to Stub mode...
	cd app
	SET "file=index.html"
	SET /a Line#ToSearch=24
	SET "Replacement=	<body ng-app="appStub">"
	(FOR /f "tokens=1*delims=:" %%a IN ('findstr /n "^" "%file%"') DO (
    SET "Line=%%b"
    IF %%a equ %Line#ToSearch% SET "Line=%Replacement%"
    SETLOCAL ENABLEDELAYEDEXPANSION
    ECHO(!Line!
    ENDLOCAL
	))>"%file%.new"
	TYPE "%file%.new"
	move "%file%.new" "%file%"

	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo Front-End could not be set to Stub Mode
	if %ERRORLEVEL% EQU 0 echo Your application now uses stubs
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit
	
	::test stub application
	echo Starting test...
	cd ..
	call gulp serve
	echo ===========================================================
	if %ERRORLEVEL% GTR 0 echo Application can't be started
	if %ERRORLEVEL% EQU 0 echo You should now see the demo application within your browser
	echo ===========================================================
	pause
	if %ERRORLEVEL% GTR 0 exit