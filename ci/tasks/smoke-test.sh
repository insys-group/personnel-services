#!/bin/bash

set -e

apt-get update && apt-get install -y curl

set -x

if [ -z $HEALTH_URL ]; then
  echo "HEALTH_URL not set"
  exit 1
fi

pushd trapps-api-source
  echo "Running smoke tests for Service deployed at $HEALTH_URL"
  smoke-tests/bin/test $HEALTH_URL
popd

exit 0
