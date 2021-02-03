kind create cluster --config kind-cluster.yaml
kubectl apply -k rabbitmq/
kubectl ns rabbitmq-dev
kubectl get pods
