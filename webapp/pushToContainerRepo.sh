#!/usr/bin/env bash

IMAGE_VERSION=${1:-1.0.0}

set -e

cd $(dirname "${BASH_SOURCE[0]}")

repositoryUrl=$(aws ecr describe-repositories --repository-names twitter-profile-viewer --query "repositories[0].repositoryUri" --output text)

if [ -z "$repositoryUrl" ]
then
  echo "unable to get repository url"
  exit 1
fi

docker build --no-cache --force-rm -f ./Dockerfile -t twitter-profile-viewer .

eval $(aws ecr get-login --no-include-email --region eu-west-1)

docker tag twitter-profile-viewer:latest "$repositoryUrl":"$IMAGE_VERSION"
docker push "$repositoryUrl":"$IMAGE_VERSION"
