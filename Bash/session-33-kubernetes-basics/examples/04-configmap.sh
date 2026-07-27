#!/usr/bin/env bash

echo "=== ConfigMaps ==="

# ایجاد ConfigMap YAML
cat > /tmp/configmap.yaml << 'EOF'
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  app.env: production
  app.debug: "false"
  app.log.level: info
EOF

echo "1. ConfigMap YAML:"
cat /tmp/configmap.yaml

# ایجاد ConfigMap
echo -e "\n2. Create ConfigMap:"
echo "kubectl apply -f /tmp/configmap.yaml"
echo "✓ ConfigMap created"

# لیست ConfigMaps
echo -e "\n3. List ConfigMaps:"
echo "kubectl get configmaps"

# استفاده در Pod
echo -e "\n4. Use in Pod:"
cat > /tmp/pod-with-config.yaml << 'EOF'
apiVersion: v1
kind: Pod
metadata:
  name: app-pod
spec:
  containers:
  - name: app
    image: nginx
    envFrom:
    - configMapRef:
        name: app-config
EOF
cat /tmp/pod-with-config.yaml

# پاکسازی
rm /tmp/configmap.yaml /tmp/pod-with-config.yaml
