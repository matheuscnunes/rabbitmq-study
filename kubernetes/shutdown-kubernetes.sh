#!/bin/sh
set -o errexit

kind delete cluster
docker rm $(docker stop $(docker ps -a -q --filter ancestor=registry:2 --format="{{.ID}}"))
