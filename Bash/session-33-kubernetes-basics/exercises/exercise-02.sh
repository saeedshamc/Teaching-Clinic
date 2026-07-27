#!/usr/bin/env bash

# تمرین ۲: Multi-Resource App
# هدف: یک اسکریپت بنویسید که:
# 1. ConfigMap و Secret ایجاد کند
# 2. Deployment با config استفاده کند
# 3. Service ایجاد کند

# راه‌حل:
echo "=== Multi-Resource Application ==="

# تابع ایجاد ConfigMap
create_configmap() {
    cat > /tmp/configmap.yaml << 'EOF'
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  APP_ENV: production
  APP_DEBUG: "false"
  APP_LOG_LEVEL: info
  DB_HOST: postgres-service
  DB_PORT: "5432"
EOF
    
    echo "✓ ConfigMap YAML ایجاد شد"
}

# تابع ایجاد Secret
create_secret() {
    cat > /tmp/secret.yaml << 'EOF'
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
type: Opaque
data:
  DB_USERNAME: YWRtaW4=
  DB_PASSWORD: cGFzc3dvcmQ=
  API_KEY: YXBpa2V5MTIzNDU2
EOF
    
    echo "✓ Secret YAML ایجاد شد"
}

# تابع ایجاد Deployment با config
create_deployment_with_config() {
    cat > /tmp/deployment.yaml << 'EOF'
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: app
        image: nginx
        ports:
        - containerPort: 80
        envFrom:
        - configMapRef:
            name: app-config
        env:
        - name: DB_PASSWORD
          valueFrom:
            secretKeyRef:
              name: app-secret
              key: DB_PASSWORD
        - name: API_KEY
          valueFrom:
            secretKeyRef:
              name: app-secret
              key: API_KEY
        resources:
          requests:
            memory: "64Mi"
            cpu: "250m"
          limits:
            memory: "128Mi"
            cpu: "500m"
        livenessProbe:
          httpGet:
            path: /
            port: 80
          initialDelaySeconds: 30
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /
            port: 80
          initialDelaySeconds: 5
          periodSeconds: 5
EOF
    
    echo "✓ Deployment YAML با config ایجاد شد"
}

# تابع ایجاد Service
create_service() {
    cat > /tmp/service.yaml << 'EOF'
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    app: myapp
  ports:
  - port: 80
    targetPort: 80
  type: LoadBalancer
EOF
    
    echo "✓ Service YAML ایجاد شد"
}

# تابع apply همه resources
apply_all() {
    echo "1. Apply ConfigMap:"
    echo "kubectl apply -f /tmp/configmap.yaml"
    
    echo -e "\n2. Apply Secret:"
    echo "kubectl apply -f /tmp/secret.yaml"
    
    echo -e "\n3. Apply Deployment:"
    echo "kubectl apply -f /tmp/deployment.yaml"
    
    echo -e "\n4. Apply Service:"
    echo "kubectl apply -f /tmp/service.yaml"
    
    echo -e "\n5. Check status:"
    echo "kubectl get configmaps"
    echo "kubectl get secrets"
    echo "kubectl get deployments"
    echo "kubectl get services"
    echo "kubectl get pods"
}

# تابع cleanup
cleanup() {
    echo -e "\n6. Cleanup:"
    echo "kubectl delete -f /tmp/service.yaml"
    echo "kubectl delete -f /tmp/deployment.yaml"
    echo "kubectl delete -f /tmp/secret.yaml"
    echo "kubectl delete -f /tmp/configmap.yaml"
    rm /tmp/configmap.yaml /tmp/secret.yaml /tmp/deployment.yaml /tmp/service.yaml
    echo "✓ Cleanup complete"
}

# اجرا
create_configmap
create_secret
create_deployment_with_config
create_service

echo -e "\n=== YAML Files ==="
echo "ConfigMap:"
cat /tmp/configmap.yaml

echo -e "\nSecret:"
cat /tmp/secret.yaml

echo -e "\nDeployment:"
cat /tmp/deployment.yaml

echo -e "\nService:"
cat /tmp/service.yaml

apply_all
cleanup
