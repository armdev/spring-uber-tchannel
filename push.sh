#!/usr/bin/env bash

if [ $# -lt 1 ]
then
    echo "Usage: $0 <commit_message>"
    exit 1
fi

commit_message="$1"

git pull
git add .
git commit -m "$commit_message"
git push
