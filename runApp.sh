#!/bin/sh
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

# Resolve app.jar: prefer co-located, fall back to ./dist/app.jar
if [ -f "./app.jar" ]; then
  APP_DIR="."
elif [ -f "./dist/app.jar" ]; then
  APP_DIR="./dist"
else
  echo "Error: app.jar not found in $SCRIPT_DIR or $SCRIPT_DIR/dist" >&2
  echo "Run ./release.sh first to build and stage the application." >&2
  exit 1
fi

cd "$APP_DIR" || exit 1
java -cp ./app.jar org.springframework.boot.loader.launch.JarLauncher \
  --spring.config.location=./application.properties
