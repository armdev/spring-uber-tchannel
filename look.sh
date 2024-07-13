#!/bin/bash


if [ $# -lt 1 ]
then
        echo "Usage : missing params!!"
        exit
fi


docker logs --follow $1


