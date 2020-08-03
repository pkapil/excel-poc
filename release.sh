#!/bin/sh
rm -fr ./dist
mvn clean install package -U -DskipTests
mkdir ./dist
cp ./target/excel-poc-0.0.1-SNAPSHOT.jar ./dist/app.jar
cp ./src/main/resources/application.properties ./dist/application.properties
cp ./runApp.sh ./dist/runApp.sh
