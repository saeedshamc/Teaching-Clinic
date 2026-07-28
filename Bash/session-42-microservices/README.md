# جلسه ۴۲: میکروسرویس‌ها (Microservices)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه microservices
- Service decomposition
- Inter-service communication
- Service discovery
- API Gateway
- Container orchestration
- مثال‌های عملی

## مفاهیم پایه Microservices

Microservices architecture splitting applications به small services است:
- **Service Decomposition** - splitting monolith
- **Inter-service Communication** - services talk to each other
- **Service Discovery** - finding services
- **API Gateway** - single entry point
- **Container Orchestration** - managing containers

## Service Decomposition

Service decomposition splitting monolithic application است.

```bash
# Identify bounded contexts
- Define service boundaries
# Plan migration
```

## مثال ۱: Service Decomposition

فایل [examples/01-service-decomposition.sh](examples/01-service-decomposition.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Service Decomposition ==="

# تابع identify services
identify_services() {
    echo "Identifying potential services:"
    echo "1. User Service - User management"
    echo "2. Order Service - Order processing"
    echo "3. Product Service - Product catalog"
    echo "4. Payment Service - Payment processing"
    echo "5. Notification Service - Notifications"
}

# تابع define boundaries
define_boundaries() {
    echo -e "\nDefining service boundaries:"
    echo "User Service:"
    echo "  - User registration"
    echo "  - Authentication"
    echo "  - Profile management"
    
    echo -e "\nOrder Service:"
    echo "  - Order creation"
    echo "  - Order tracking"
    echo "  - Order history"
}

# تابع plan migration
plan_migration() {
    echo -e "\nMigration strategy:"
    echo "Phase 1: Extract user service"
    echo "Phase 2: Extract product service"
    echo "Phase 3: Extract order service"
    echo "Phase 4: Extract payment service"
}

# تابع create service structure
create_service_structure() {
    local service=$1
    
    echo -e "\nCreating structure for $service:"
    mkdir -p $service/{src,tests,config}
    echo "✓ Structure created"
}

# استفاده
identify_services
define_boundaries
plan_migration
create_service_structure "user-service"
```

### توضیح خط به خط:

- service identification
- boundary definition
- migration planning
- service structure creation
- phased migration

## Inter-service Communication

Inter-service communication برای services talking است.

```bash
# REST API
- gRPC
# Message queues
```

## مثال ۲: Inter-service Communication

فایل [examples/02-inter-service-comm.sh](examples/02-inter-service-comm.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Inter-service Communication ==="

# تابع REST API call
rest_api_call() {
    local service=$1
    local endpoint=$2
    
    echo "Calling $service via REST:"
    curl -s http://$service$endpoint
}

# تابع gRPC call
grpc_call() {
    local service=$1
    local method=$2
    
    echo -e "\nCalling $service via gRPC:"
    echo "Method: $method"
    # grpcurl -plaintext $service $method
}

# تابع message queue
message_queue() {
    local queue=$1
    local message=$2
    
    echo -e "\nSending to queue $queue:"
    echo "Message: $message"
    # Send message to queue
}

# تابع service mesh
service_mesh() {
    local source=$1
    local destination=$2
    
    echo -e "\nService mesh routing:"
    echo "Source: $source"
    echo "Destination: $destination"
    echo "Route: via service mesh"
}

# استفاده
rest_api_call "user-service" "/api/users/1"
grpc_call "order-service" "GetOrder"
message_queue "orders" "New order created"
service_mesh "web" "user-service"
```

### توضیح خط به خط:

- REST API communication
- gRPC calls
- message queue publishing
- service mesh routing
- communication patterns

## Service Discovery

Service discovery برای finding services است.

```bash
# Service registration
- Service lookup
# Health checks
```

## مثال ۳: Service Discovery

فایل [examples/03-service-discovery.sh](examples/03-service-discovery.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Service Discovery ==="

# تابع register service
register_service() {
    local service=$1
    local address=$2
    local port=$3
    
    echo "Registering $service:"
    echo "  Address: $address"
    echo "  Port: $port"
    echo "✓ Service registered"
}

# تابع discover service
discover_service() {
    local service=$1
    
    echo -e "\nDiscovering $service:"
    echo "  Found at: 192.168.1.10:8080"
    echo "  Health: OK"
}

# تابع health check
health_check() {
    local service=$1
    
    echo -e "\nHealth check for $service:"
    curl -s http://$service/health
}

# تابع load balancing
load_balance() {
    local service=$1
    
    echo -e "\nLoad balancing $service:"
    echo "  Instance 1: 192.168.1.10:8080"
    echo "  Instance 2: 192.168.1.11:8080"
    echo "  Selected: Instance 1"
}

# استفاده
register_service "user-service" "192.168.1.10" "8080"
discover_service "user-service"
health_check "user-service"
load_balance "user-service"
```

### توضیح خط به خط:

- service registration
- service discovery
- health checks
- load balancing
- instance selection

## API Gateway

API Gateway single entry point برای clients است.

```bash
# Route requests
- Authentication
- Rate limiting
```

## مثال ۴: API Gateway

فایل [examples/04-api-gateway.sh](examples/04-api-gateway.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== API Gateway ==="

# تابع route request
route_request() {
    local path=$1
    
    echo "Routing request: $path"
    
    case "$path" in
        "/api/users"*)
            echo "  Routing to user-service"
            ;;
        "/api/orders"*)
            echo "  Routing to order-service"
            ;;
        "/api/products"*)
            echo "  Routing to product-service"
            ;;
        *)
            echo "  Unknown path"
            ;;
    esac
}

# تابع authenticate
authenticate() {
    local token=$1
    
    echo -e "\nAuthenticating token:"
    echo "  Token: $token"
    echo "  Valid: ✓"
}

# تابع rate limit
rate_limit() {
    local client=$1
    
    echo -e "\nRate limiting for $client:"
    echo "  Requests: 5/minute"
    echo "  Limit: 10/minute"
    echo "  Status: ✓ Allowed"
}

# تابع aggregate response
aggregate_response() {
    echo -e "\nAggregating responses:"
    echo "  User service: {user data}"
    echo "  Order service: {order data}"
    echo "  Combined: {aggregated data}"
}

# استفاده
route_request "/api/users/1"
authenticate "abc123"
rate_limit "client-1"
aggregate_response
```

### توضیح خط به خط:

- request routing
- authentication
- rate limiting
- response aggregation
- gateway functions

## Container Orchestration

Container orchestration برای managing containers است.

```bash
# Docker Compose
- Kubernetes
# Service deployment
```

## مثال ۵: Container Orchestration

فایل [examples/05-container-orchestration.sh](examples/05-container-orchestration.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Container Orchestration ==="

# تابع create docker-compose
create_docker_compose() {
    cat > docker-compose.yml << 'EOF'
version: '3.8'
services:
  user-service:
    image: user-service:latest
    ports:
      - "8081:8080"
    depends_on:
      - database
  
  order-service:
    image: order-service:latest
    ports:
      - "8082:8080"
    depends_on:
      - database
      - user-service
  
  database:
    image: postgres:13
    environment:
      POSTGRES_DB: microservices
EOF
    
    echo "✓ docker-compose.yml created"
}

# تابع deploy service
deploy_service() {
    local service=$1
    
    echo -e "\nDeploying $service:"
    docker-compose up -d $service
    echo "✓ Service deployed"
}

# تابع scale service
scale_service() {
    local service=$1
    local replicas=$2
    
    echo -e "\nScaling $service to $replicas replicas:"
    docker-compose up -d --scale $service=$replicas
    echo "✓ Service scaled"
}

# تابع check status
check_status() {
    echo -e "\nService status:"
    docker-compose ps
}

# استفاده
create_docker_compose
# deploy_service "user-service"
# scale_service "user-service" 3
check_status
```

### توضیح خط به خط:

- Docker Compose creation
- service deployment
- service scaling
- status checking
- orchestration management

## نکات مهم

### ۱: Independent Deployment

```bash
# Deploy services independently
- Version services separately
# No coupling
```

### ۲: Data Management

```bash
# Database per service
- Shared databases carefully
# Eventual consistency
```

### ۳: Monitoring

```bash
# Monitor each service
- Distributed tracing
# Centralized logging
```

## مثال ۶: Microservices Best Practices

فایل [examples/06-microservices-best-practices.sh](examples/06-microservices-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Microservices Best Practices ==="

# 1. Service Design
echo -e "\n1. Service Design:"
echo "✓ Single responsibility"
echo "✓ Bounded contexts"
echo "✓ API-first design"

# 2. Communication
echo -e "\n2. Communication:"
echo "✓ Use appropriate protocols"
echo "✓ Implement retries"
echo "✓ Circuit breakers"

# 3. Data Management
echo -e "\n3. Data Management:"
echo "✓ Database per service"
echo "✓ Eventual consistency"
echo "✓ Event sourcing"

# 4. Deployment
echo -e "\n4. Deployment:"
echo "✓ Independent deployment"
echo "✓ Blue-green deployment"
echo "✓ Canary releases"

# 5. Observability
echo -e "\n5. Observability:"
echo "✓ Distributed tracing"
echo "✓ Centralized logging"
echo "✓ Metrics collection"
```

### توضیح خط به خط:

- single responsibility
- appropriate communication
- data isolation
- independent deployment
- comprehensive observability

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: Shared Database

❌ اشتباه:
```bash
# shared database
```

✅ درست:
```bash
# database per service
```

### ۲: Synchronous Communication

❌ اشتباه:
```bash
# فقط synchronous
```

✅ درست:
```bash
# async communication
```

### ۳: بدون Monitoring

❌ اشتباه:
```bash
# بدون monitoring
```

✅ درست:
```bash
# distributed tracing
```

## بهترین شیوه‌ها (Best Practices)

1. **Service Design** - single responsibility
2. **Communication** - async where possible
3. **Data** - database per service
4. **Deployment** - independent deployment
5. **Observability** - comprehensive monitoring

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه microservices
- Service decomposition
- Inter-service communication
- Service discovery
- API Gateway
- Container orchestration
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Service Structure

یک اسکریپت بنویسید که:
1. microservice structure ایجاد کند
2. docker-compose file بسازد
3. service را deploy کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Service Communication

یک اسکریپت بنویسید که:
1. inter-service communication را implement کند
2. service discovery را setup کند
3. API gateway را configure کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Serverless را یاد می‌گیریم.
