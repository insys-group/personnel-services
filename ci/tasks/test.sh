#!/bin/bash

set -ex

pushd trapps-api-source
  echo "Fetching Dependencies"
  ./gradlew clean build -x test > /dev/null

  echo "Running Tests"
  ./gradlew test
popd

exit 0
