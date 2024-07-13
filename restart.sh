#!/usr/bin/env bash

set -e
if [ $# -lt 1 ]
then
        echo "Usage : missing params!!"
        exit
fi
docker rm -f $1
#docker rmi -f $1

mvn clean install -pl $1 -am -DskipTests=true

docker compose up -d --no-deps --build  $1
docker ps -a
docker logs --follow $1
