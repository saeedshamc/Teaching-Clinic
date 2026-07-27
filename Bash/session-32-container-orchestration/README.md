# جلسه ۳۲: ارکستراسیون کانتینر (Container Orchestration)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه container orchestration
- Docker Swarm basics
- Service discovery
- Load balancing
- Scaling
- Health checks
- مثال‌های عملی

## مفاهیم پایه Container Orchestration

Container orchestration مدیریت multiple containers است:
- **Service Discovery** - پیدا کردن services
- **Load Balancing** - توزیع ترافیک
- **Scaling** - مقیاس‌دهی services
- **Health Checks** - بررسی سلامت
- **Rolling Updates** - به‌روزرسانی بدون downtime

## Docker Swarm

Docker Swarm یک built-in orchestration tool برای Docker است.

```bash
docker swarm init
docker service create --name web nginx
```

## مثال ۱: Docker Swarm Basics

فایل [examples/01-docker-swarm.sh](examples/01-docker-swarm.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Docker Swarm ==="

# Initialize swarm
echo "1. Initialize Swarm:"
echo "docker swarm init"
echo "✓ Swarm initialized"

# Create service
echo -e "\n2. Create Service:"
echo "docker service create --name web --replicas 3 -p 80:80 nginx"
echo "✓ Service created with 3 replicas"

# List services
echo -e "\n3. List Services:"
echo "docker service ls"

# Scale service
echo -e "\n4. Scale Service:"
echo "docker service scale web=5"
echo "✓ Service scaled to 5 replicas"

# Remove service
echo -e "\n5. Remove Service:"
echo "docker service rm web"
echo "✓ Service removed"
```

### توضیح خط به خط:

- `docker swarm init` - ایجاد swarm
- `docker service create` - ایجاد service
- `--replicas` - تعداد replicas
- `docker service scale` - scaling
- `docker service rm` - حذف service

## Service Discovery

```bash
# Services می‌توانند یکدیگر را پیدا کنند
# DNS-based service discovery
# Internal load balancing
```

## مثال ۲: Service Discovery

فایل [examples/02-service-discovery.sh](examples/02-service-discovery.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Service Discovery ==="

# Create overlay network
echo "1. Create Overlay Network:"
echo "docker network create --driver overlay my-network"
echo "✓ Overlay network created"

# Create web service
echo -e "\n2. Create Web Service:"
echo "docker service create --name web --network my-network -p 80:80 nginx"
echo "✓ Web service created"

# Create app service
echo -e "\n3. Create App Service:"
echo "docker service create --name app --network my-network my-app"
echo "✓ App service created"

# Service discovery
echo -e "\n4. Service Discovery:"
echo "App می‌تواند web را با DNS پیدا کند:"
echo "curl http://web:80"

# List tasks
echo -e "\n5. List Tasks:"
echo "docker service ps web"
```

### توضیح خط به خط:

- `--driver overlay` - overlay network
- services در یک network
- DNS-based discovery
- service name به عنوان hostname

## Load Balancing

```bash
# Docker Swarm automatic load balancing
# VIP (Virtual IP)
- DNS round robin
```

## مثال ۳: Load Balancing

فایل [examples/03-load-balancing.sh](examples/03-load-balancing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Load Balancing ==="

# Create service with multiple replicas
echo "1. Create Service with Load Balancing:"
echo "docker service create --name web --replicas 5 -p 80:80 nginx"
echo "✓ Service created with 5 replicas"

# Check load balancing
echo -e "\n2. Check Load Balancing:"
echo "for i in {1..10}; do curl localhost; done"
echo "ترافیک بین replicas توزیع می‌شود"

# Inspect service
echo -e "\n3. Inspect Service:"
echo "docker service inspect web --format '{{.Endpoint.Ports}}'"

# Check VIP
echo -e "\n4. Check VIP:"
echo "docker service inspect web --format '{{.Endpoint.VirtualIPs}}'"
```

### توضیح خط به خط:

- multiple replicas برای load balancing
- automatic load balancing
- VIP برای service
- ترافیک توزیع می‌شود

## Scaling

```bash
# Horizontal scaling
# Vertical scaling
- Auto-scaling
```

## مثال ۴: Scaling

فایل [examples/04-scaling.sh](examples/04-scaling.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Scaling ==="

# Create service
echo "1. Create Service:"
echo "docker service create --name web --replicas 3 -p 80:80 nginx"
echo "✓ Service created with 3 replicas"

# Scale up
echo -e "\n2. Scale Up:"
echo "docker service scale web=10"
echo "✓ Scaled to 10 replicas"

# Scale down
echo -e "\n3. Scale Down:"
echo "docker service scale web=5"
echo "✓ Scaled to 5 replicas"

# Auto-scaling script
echo -e "\n4. Auto-scaling:"
echo "#!/bin/bash"
echo "CPU_USAGE=\$(docker stats --no-stream --format \"{{.CPUPerc}}\" web)"
echo "if [ \$CPU_USAGE > 80 ]; then"
echo "  docker service scale web=\$((current + 2))"
echo "fi"
```

### توضیح خط به ligne:

- `docker service scale` - scaling
- scale up و down
- auto-scaling بر اساس metrics
- horizontal scaling

## Health Checks

```bash
# Health checks برای containers
- Automatic recovery
# Rolling updates
```

## مثال ۵: Health Checks

فایل [examples/05-health-checks.sh](examples/05-health-checks.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Health Checks ==="

# Create service with health check
echo "1. Create Service with Health Check:"
echo "docker service create --name web --replicas 3 \\"
echo "  --health-cmd 'curl -f http://localhost/ || exit 1' \\"
echo "  --health-interval 5s \\"
echo "  --health-retries 3 \\"
echo "  -p 80:80 nginx"
echo "✓ Service created with health check"

# Check health status
echo -e "\n2. Check Health Status:"
echo "docker service ps web"

# Inspect health
echo -e "\n3. Inspect Health:"
echo "docker inspect web --format '{{.Spec.TaskTemplate.ContainerSpec.Healthcheck}}'"

# Rolling update
echo -e "\n4. Rolling Update:"
echo "docker service update --image nginx:latest web"
echo "✓ Rolling update started"
```

### توضیح خط به خط:

- `--health-cmd` - health check command
- `--health-interval` - interval
- `--health-retries` - retries
- rolling update بدون downtime

## نکات مهم

### ۱. High Availability

```bash
# Multiple nodes برای HA
# Replicas برای redundancy
```

### ۲. Resource Limits

```bash
# CPU و memory limits
- Resource reservations
```

### ۳. Secrets Management

```bash
# Docker secrets برای sensitive data
- Encrypted secrets
```

## مثال ۶: Orchestration Best Practices

فایل [examples/06-orchestration-best-practices.sh](examples/06-orchestration-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Orchestration Best Practices ==="

# 1. High Availability
echo -e "\n1. High Availability:"
echo "✓ Multiple manager nodes"
echo "✓ Replicas برای services"
echo "✓ Spread across nodes"

# 2. Resource Management
echo -e "\n2. Resource Management:"
echo "✓ Set CPU limits"
echo "✓ Set memory limits"
echo "✓ Resource reservations"

# 3. Security
echo -e "\n3. Security:"
echo "✓ Use Docker secrets"
echo "✓ Private networks"
echo "✓ TLS encryption"

# 4. Monitoring
echo -e "\n4. Monitoring:"
echo "✓ Monitor service health"
echo "✓ Track resource usage"
echo "✓ Log aggregation"

# 5. Updates
echo -e "\n5. Updates:"
echo "✓ Rolling updates"
echo "✓ Health checks"
echo "✓ Rollback capability"
```

### توضیح خط به خط:

- HA با multiple nodes
- Resource limits برای stability
- Security با secrets
- Monitoring برای awareness
- Rolling updates برای zero-downtime

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Health Checks

❌ اشتباه:
```bash
# بدون health checks
```

✅ درست:
```bash
# health checks برای automatic recovery
```

### ۲: بدون Resource Limits

❌ اشتباه:
```bash
# بدون resource limits
```

✅ درست:
```bash
# CPU و memory limits
```

### ۳: Single Node

❌ اشتباه:
```bash
# single node deployment
```

✅ درست:
```bash
# multiple nodes برای HA
```

## بهترین شیوه‌ها (Best Practices)

1. **High Availability** - multiple nodes
2. **Resource Limits** - CPU و memory
3. **Health Checks** - automatic recovery
4. **Security** - secrets و encryption
5. **Monitoring** - health و metrics

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه container orchestration
- Docker Swarm basics
- Service discovery
- Load balancing
- Scaling
- Health checks
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Swarm Setup

یک اسکریپت بنویسید که:
1. Docker Swarm را initialize کند
2. service ایجاد کند
3. service را scale کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Multi-Service App

یک اسکریپت بنویسید که:
1. multiple services ایجاد کند
2. service configures کند
3. health checks اعمال کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Kubernetes Basics را یاد می‌گیریم.
