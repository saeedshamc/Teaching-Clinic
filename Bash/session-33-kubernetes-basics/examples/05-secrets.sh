#!/usr/bin/env bash

echo "=== Secrets ==="

# ایجاد secret از literal
echo "1. Create Secret from literal:"
echo "kubectl create secret generic db-secret --from-literal=password=mypass"
echo "✓ Secret created"

# ایجاد Secret YAML
cat > /tmp/secret.yaml << 'EOF'
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
type: Opaque
data:
  username: YWRtaW4=
  password: cGFzc3dvcmQ=
EOF

echo -e "\n2. Secret YAML:"
cat /tmp/secret.yaml

# ایجاد Secret
echo -e "\n3. Create Secret:"
echo "kubectl apply -f /tmp/secret.yaml"
echo "✓ Secret created"

# لیست Secrets
echo -e "\n4. List Secrets:"
echo "kubectl get secrets"

# استفاده در Pod
echo -e "\n5. Use in Pod:"
cat > /tmp/pod-with-secret.yaml << 'EOF'
apiVersion: v1
kind: Pod
metadata:
  name: app-pod
spec:
  containers:
  - name: app
    image: nginx
    env:
    - name: DB_PASSWORD
      valueFrom:
        secretKeyRef:
          name: app-secret
          key: password
EOF
cat /tmp/pod-with-secret.yaml

# پاکسازی
rm /tmp/secret.yaml /tmp/pod-with-secret.yaml
