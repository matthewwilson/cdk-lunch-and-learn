#!/bin/bash

if [[ -z $1 ]]; then
    echo 'ERROR: No target file given.'
    exit 1
fi

if [[ -z $2 ]]; then
    echo 'ERROR: No lambda url given.'
    exit 1
fi

sed -i "s#__LAMBDA_URL__#$LAMBDA_URL#g" "$1"

# Execute all other paramters
exec "${@:2}"
