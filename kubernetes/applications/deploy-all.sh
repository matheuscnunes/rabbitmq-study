#!/bin/sh

echo ">>>>>>>>>>>>>>>>>>>>> Generating image for rabbitmq-publisher"
./gradlew -p rabbitmq-publisher jibDockerBuild
docker tag rabbitmq-publisher:0.0.1-SNAPSHOT localhost:5000/rabbitmq-publisher:0.0.1-SNAPSHOT
docker push localhost:5000/rabbitmq-publisher:0.0.1-SNAPSHOT

echo ">>>>>>>>>>>>>>>>>>>>> Generating image for rabbitmq-consumer"
./gradlew -p rabbitmq-consumer jibDockerBuild
docker tag rabbitmq-consumer:0.0.1-SNAPSHOT localhost:5000/rabbitmq-consumer:0.0.1-SNAPSHOT
docker push localhost:5000/rabbitmq-consumer:0.0.1-SNAPSHOT

echo ">>>>>>>>>>>>>>>>>>>>> Creating services and deployments"
kubectl apply -f kubernetes/applications/

kubectl get pods
sleep 10
kubectl get pods