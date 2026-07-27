# جلسه ۳۳: مبانی Kubernetes (Kubernetes Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه Kubernetes
- Pods و Deployments
- Services و Ingress
- ConfigMaps و Secrets
- Scaling
- Rolling Updates
- مثال‌های عملی

## مفاهیم پایه Kubernetes

Kubernetes یک platform برای container orchestration است:
- **Pod** - کوچک‌ترین واحد در K8s
- **Deployment** - مدیریت pods
- **Service** - exposure و load balancing
- **ConfigMap** - configuration data
- **Secret** - sensitive data

## Pods

Pod کوچک‌ترین واحد قابل اجرا در Kubernetes است.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
  - name: nginx
    image: nginx
```

## مثال ۱: Pod Basics

فایل [examples/01-pod-basics.sh](examples/01-pod-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `apiVersion: v1` - version API
- `kind: Pod` - نوع resource
- `containers` - لیست containers
- `kubectl apply` - ایجاد resource
- `kubectl get` - لیست resources

## Deployments

Deployment مدیریت pods و replicas را انجام می‌دهد.

```yaml
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
```

## مثال ۲: Deployment Basics

فایل [examples/02-deployment-basics.sh](examples/02-deployment-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `replicas: 3` - تعداد replicas
- `selector` - label selector
- `template` - pod template
- `kubectl scale` - scaling
- label-based selection

## Services

Service pods را expose می‌کند و load balancing انجام می‌دهد.

```yaml
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
```

## مثال ۳: Service Basics

فایل [examples/03-service-basics.sh](examples/03-service-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `selector` - انتخاب pods
- `port` - service port
- `targetPort` - container port
- `type: LoadBalancer` - external access
- load balancing اتوماتیک

## ConfigMaps

ConfigMap configuration data را نگه می‌دارد.

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  app.env: production
  app.debug: "false"
```

## مثال ۴: ConfigMap Basics

فایل [examples/04-configmap.sh](examples/04-configmap.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `data` - key-value pairs
- `envFrom` - inject environment variables
- `configMapRef` - reference to ConfigMap
- configuration separation

## Secrets

Secret sensitive data را نگه می‌دارد.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
type: Opaque
data:
  password: cGFzc3dvcmQ=
```

## مثال ۵: Secrets Basics

فایل [examples/05-secrets.sh](examples/05-secrets.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `kubectl create secret` - ایجاد secret
- base64 encoded data
- `secretKeyRef` - reference to secret
- sensitive data protection

## Rolling Updates

Rolling updates بدون downtime انجام می‌شود.

```bash
kubectl set image deployment/nginx-deployment nginx=nginx:1.19
kubectl rollout status deployment/nginx-deployment
```

## مثال ۶: Rolling Updates

فایل [examples/06-rolling-updates.sh](examples/06-rolling-updates.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Rolling Updates ==="

# Update image
echo "1. Update Deployment Image:"
echo "kubectl set image deployment/nginx-deployment nginx=nginx:1.19"
echo "✓ Image update started"

# Check rollout status
echo -e "\n2. Check Rollout Status:"
echo "kubectl rollout status deployment/nginx-deployment"

# Check rollout history
echo -e "\n3. Check Rollout History:"
echo "kubectl rollout history deployment/nginx-deployment"

# Rollback
echo -e "\n4. Rollback:"
echo "kubectl rollout undo deployment/nginx-deployment"
echo "✓ Rollback completed"

# Rollback to specific revision
echo -e "\n5. Rollback to Revision:"
echo "kubectl rollout undo deployment/nginx-deployment --to-revision=2"
```

### توضیح خط به خط:

- `set image` - update image
- `rollout status` - check status
- `rollout history` - history
- `rollout undo` - rollback
- zero-downtime updates

## نکات مهم

### ۱. Resource Limits

```yaml
resources:
  requests:
    memory: "64Mi"
    cpu: "250m"
  limits:
    memory: "128Mi"
    cpu: "500m"
```

### ۲. Health Checks

```yaml
livenessProbe:
  httpGet:
    path: /health
    port: 80
readinessProbe:
  httpGet:
    path: /ready
    port: 80
```

### ۳. Namespaces

```bash
kubectl create namespace dev
kubectl config set-context --current --namespace=dev
```

## مثال ۷: K8s Best Practices

فایل [examples/07-k8s-best-practices.sh](examples/07-k8s-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Kubernetes Best Practices ==="

# 1. Resource Limits
echo -e "\n1. Resource Limits:"
echo "✓ همیشه resource limits تنظیم کنید"
echo "✓ requests و limits تعریف کنید"
echo "✓ از resource quotas استفاده کنید"

# 2. Health Checks
echo -e "\n2. Health Checks:"
echo "✓ liveness probe تعریف کنید"
echo "✓ readiness probe تعریف کنید"
echo "✓ startup probe برای slow-starting apps"

# 3. Security
echo -e "\n3. Security:"
echo "✓ از secrets برای sensitive data استفاده کنید"
echo "✓ non-root user در containers"
echo "✓ RBAC برای access control"

# 4. Scalability
echo -e "\n4. Scalability:"
echo "✓ Horizontal Pod Autoscaler"
echo "✓ Cluster Autoscaler"
echo "✓ Resource-based scaling"

# 5. Monitoring
echo -e "\n5. Monitoring:"
echo "✓ Prometheus و Grafana"
echo "✓ Logging با ELK یا Loki"
echo "✓ Tracing با Jaeger"
```

### توضیح خط به خط:

- Resource limits برای stability
- Health checks برای reliability
- Security best practices
- Autoscaling برای scalability
- Monitoring stack

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Resource Limits

❌ اشتباه:
```yaml
# بدون resource limits
```

✅ درست:
```yaml
resources:
  requests:
    memory: "64Mi"
    cpu: "250m"
  limits:
    memory: "128Mi"
    cpu: "500m"
```

### ۲: بدون Health Checks

❌ اشتباه:
```yaml
# بدون health checks
```

✅ درست:
```yaml
livenessProbe:
  httpGet:
    path: /health
    port: 80
```

### ۳: Hardcoded Configs

❌ اشتباه:
```yaml
# hardcoded configs
```

✅ درست:
```yaml
envFrom:
- configMapRef:
    name: app-config
```

## بهترین شیوه‌ها (Best Practices)

1. **Resource Limits** - همیشه تعریف کنید
2. **Health Checks** - liveness و readiness
3. **Secrets** - برای sensitive data
4. **Namespaces** - برای separation
5. **Monitoring** - Prometheus و Grafana

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه Kubernetes
- Pods و Deployments
- Services و Ingress
- ConfigMaps و Secrets
- Scaling
- Rolling Updates
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): K8s Deployment

یک اسکریپت بنویسید که:
1. Deployment YAML ایجاد کند
2. Service YAML ایجاد کند
3. resources را apply کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Multi-Resource App

یک اسکریپت بنویسید که:
1. ConfigMap و Secret ایجاد کند
2. Deployment با config استفاده کند
3. Service ایجاد کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Terraform Basics را یاد می‌گیریم.
