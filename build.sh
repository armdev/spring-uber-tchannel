#!/usr/bin/env bash

set -e
echo "Build Backend"

mvn clean package -U -Dmaven.test.skip=true

