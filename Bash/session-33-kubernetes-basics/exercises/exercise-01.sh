#!/usr/bin/env bash

# تمرین ۱: K8s Deployment
# هدف: یک اسکریپت بنویسید که:
# 1. Deployment YAML ایجاد کند
# 2. Service YAML ایجاد کند
# 3. resources را apply کند

# راه‌حل:
echo "=== Kubernetes Deployment ==="

# تابع ایجاد Deployment YAML
create_deployment_yaml() {
    local name=$1
    local image=$2
    local replicas=$3
    local port=$4
    
    cat > /tmp/deployment.yaml << EOF
apiVersion: apps/v1
kind: Deployment
metadata:
  name: $name
spec:
  replicas: $replicas
  selector:
    matchLabels:
      app: $name
  template:
    metadata:
      labels:
        app: $name
    spec:
      containers:
      - name: $name
        image: $image
        ports:
        - containerPort: $port
        resources:
          requests:
            memory: "64Mi"
            cpu: "250m"
          limits:
            memory: "128Mi"
            cpu: "500m"
EOF
    
    echo "✓ Deployment YAML ایجاد شد: /tmp/deployment.yaml"
}

# تابع ایجاد Service YAML
create_service_yaml() {
    local name=$1
    local port=$2
    
    cat > /tmp/service.yaml << EOF
apiVersion: v1
kind: Service
metadata:
  name: ${name}-service
spec:
  selector:
    app: $name
  ports:
  - port: $port
    targetPort: $port
  type: LoadBalancer
EOF
    
    echo "✓ Service YAML ایجاد شد: /tmp/service.yaml"
}

# تابع apply resources
apply_resources() {
    echo "1. Apply Deployment:"
    echo "kubectl apply -f /tmp/deployment.yaml"
    echo "✓ Deployment applied"
    
    echo -e "\n2. Apply Service:"
    echo "kubectl apply -f /tmp/service.yaml"
    echo "✓ Service applied"
    
    echo -e "\n3. Check status:"
    echo "kubectl get deployments"
    echo "kubectl get services"
    echo "kubectl get pods"
}

# تابع cleanup
cleanup() {
    echo -e "\n4. Cleanup:"
    echo "kubectl delete -f /tmp/service.yaml"
    echo "kubectl delete -f /tmp/deployment.yaml"
    rm /tmp/deployment.yaml /tmp/service.yaml
    echo "✓ Cleanup complete"
}

# اجرا
create_deployment_yaml "web-app" "nginx" 3 80
create_service_yaml "web-app" 80

echo -e "\nDeployment YAML:"
cat /tmp/deployment.yaml

echo -e "\nService YAML:"
cat /tmp/service.yaml

apply_resources
cleanup
