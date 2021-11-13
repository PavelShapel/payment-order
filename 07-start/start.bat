@echo off

set CLOUD_API=cloud-api

pushd ..\07-%CLOUD_API%
call mvn clean install -DskipTests
if errorlevel 1 goto error
popd

set CLOUD_PROVIDER=cloud-provider

pushd ..\07-%CLOUD_PROVIDER%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%CLOUD_PROVIDER%:1.00 .
if errorlevel 1 goto error
popd

set CLOUD_CONSUMER=cloud-consumer

pushd ..\07-%CLOUD_CONSUMER%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%CLOUD_CONSUMER%:1.00 .
if errorlevel 1 goto error
popd

set CLOUD_GATEWAY=cloud-gateway

pushd ..\07-%CLOUD_GATEWAY%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%CLOUD_GATEWAY%:1.00 .
if errorlevel 1 goto error
popd

call docker-compose up -d

goto exit

:error
echo error
echo  
pause

:exit
echo ok
echo  
