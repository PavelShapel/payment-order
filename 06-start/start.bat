@echo off

set KAFKA_CLIENT=kafka-client

pushd ..\06-%KAFKA_CLIENT%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%KAFKA_CLIENT%:1.00 .
if errorlevel 1 goto error
popd

set KAFKA_PALMETTO=kafka-palmetto

pushd ..\06-%KAFKA_PALMETTO%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%KAFKA_PALMETTO%:1.00 .
if errorlevel 1 goto error
popd

set KAFKA_COURIER=kafka-courier

pushd ..\06-%KAFKA_COURIER%
call mvn clean install -DskipTests
if errorlevel 1 goto error
call docker build -t pavelshapel/%KAFKA_COURIER%:1.00 .
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
