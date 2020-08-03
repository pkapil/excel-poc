#!/bin/sh
commandline="java -cp  ./app.jar org.springframework.boot.loader.JarLauncher . "
commandline="$commandline --Spring.config.location=./application.properties"
$commandline
