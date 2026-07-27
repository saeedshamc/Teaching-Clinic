#!/usr/bin/env bash

echo "=== Kubernetes Services ==="

# ایجاد Service YAML
cat > /tmp/service.yaml << 'EOF'
apiVersion: v1
kind: Service
metadata:
  name: nginx-service
spec:
  selector:
    app: nginx
  ports:
  - port: 80
    targetPort: 80
  type: LoadBalancer
EOF

echo "1. Service YAML:"
cat /tmp/service.yaml

# ایجاد Service
echo -e "\n2. Create Service:"
echo "kubectl apply -f /tmp/service.yaml"
echo "✓ Service created"

# لیست Services
echo -e "\n3. List Services:"
echo "kubectl get services"

# توضیح Service
echo -e "\n4. Describe Service:"
echo "kubectl describe service nginx-service"

# پاکسازی
rm /tmp/service.yaml
