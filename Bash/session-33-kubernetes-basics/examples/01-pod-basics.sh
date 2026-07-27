#!/usr/bin/env bash

echo "=== Kubernetes Pods ==="

# ایجاد Pod YAML
cat > /tmp/pod.yaml << 'EOF'
apiVersion: v1
kind: Pod
metadata:
  name: nginx-pod
spec:
  containers:
  - name: nginx
    image: nginx
    ports:
    - containerPort: 80
EOF

echo "1. Pod YAML:"
cat /tmp/pod.yaml

# ایجاد Pod
echo -e "\n2. Create Pod:"
echo "kubectl apply -f /tmp/pod.yaml"
echo "✓ Pod created"

# لیست Pods
echo -e "\n3. List Pods:"
echo "kubectl get pods"

# توضیح Pod
echo -e "\n4. Describe Pod:"
echo "kubectl describe pod nginx-pod"

# حذف Pod
echo -e "\n5. Delete Pod:"
echo "kubectl delete pod nginx-pod"
echo "✓ Pod deleted"

# پاکسازی
rm /tmp/pod.yaml
