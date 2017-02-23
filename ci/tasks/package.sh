#!/bin/bash

set -ex

pushd trapps-api-source
  echo "Packaging JAR"
  ./gradlew clean build -x test
popd
pwd
ls -l trapps-api-source/build/libs
ls ../
jar_count=`find trapps-api-source/build/libs/ -type f -name *.jar | wc -l`

if [ $jar_count -gt 1 ]; then
  echo "More than one jar found, don't know which one to deploy. Exiting"
  exit 1
fi
echo "this is the jar count" $jar_count
find trapps-api-source/ -type f -name *.jar -exec cp "{}" package-output/trapps-api.jar \;
ls -l package-output
echo "Done packaging"
exit 0
