#!/usr/bin/env bash

#sudo chmod 666 /var/run/docker.sock
docker rm -f $(docker ps -a -q)
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
docker ps -a
