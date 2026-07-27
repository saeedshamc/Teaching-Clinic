#!/usr/bin/env bash

echo "=== Kubernetes Deployments ==="

# ایجاد Deployment YAML
cat > /tmp/deployment.yaml << 'EOF'
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
EOF

echo "1. Deployment YAML:"
cat /tmp/deployment.yaml

# ایجاد Deployment
echo -e "\n2. Create Deployment:"
echo "kubectl apply -f /tmp/deployment.yaml"
echo "✓ Deployment created"

# لیست Deployments
echo -e "\n3. List Deployments:"
echo "kubectl get deployments"

# لیست Pods
echo -e "\n4. List Pods:"
echo "kubectl get pods -l app=nginx"

# Scale Deployment
echo -e "\n5. Scale Deployment:"
echo "kubectl scale deployment nginx-deployment --replicas=5"
echo "✓ Scaled to 5 replicas"

# پاکسازی
rm /tmp/deployment.yaml
