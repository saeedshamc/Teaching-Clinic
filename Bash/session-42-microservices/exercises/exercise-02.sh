#!/usr/bin/env bash

# تمرین ۲: Service Communication
# هدف: یک اسکریپت بنویسید که:
# 1. inter-service communication را implement کند
# 2. service discovery را setup کند
# 3. API gateway را configure کند

# راه‌حل:
echo "=== Service Communication Script ==="

# تابع setup service registry
setup_service_registry() {
    echo "1. Setting up service registry:"
    
    local registry_file="/tmp/service-registry.txt"
    cat > $registry_file << EOF
user-service:192.168.1.10:8081
order-service:192.168.1.11:8082
product-service:192.168.1.12:8083
payment-service:192.168.1.13:8084
EOF
    
    echo "✓ Service registry created"
}

# تابع register service
register_service() {
    local service=$1
    local address=$2
    local port=$3
    
    echo -e "\n2. Registering service:"
    echo "  Service: $service"
    echo "  Address: $address:$port"
    
    # Add to registry
    echo "$service:$address:$port" >> /tmp/service-registry.txt
    echo "✓ Service registered"
}

# تابع discover service
discover_service() {
    local service=$1
    
    echo -e "\n3. Discovering service: $service"
    
    local registry=$(cat /tmp/service-registry.txt)
    local entry=$(echo "$registry" | grep "^$service:")
    
    if [ -n "$entry" ]; then
        echo "  Found: $entry"
        local address=$(echo $entry | cut -d: -f2)
        local port=$(echo $entry | cut -d: -f3)
        echo "  URL: http://$address:$port"
        echo "✓ Service discovered"
    else
        echo "✗ Service not found"
    fi
}

# تابع inter-service REST call
inter_service_call() {
    local from_service=$1
    local to_service=$2
    local endpoint=$3
    
    echo -e "\n4. Inter-service communication:"
    echo "  From: $from_service"
    echo "  To: $to_service"
    echo "  Endpoint: $endpoint"
    
    # Simulate call
    echo "  Method: GET"
    echo "  Headers: X-Service-Name: $from_service"
    echo "  Response: {\"status\":\"ok\",\"data\":\"...\"}"
    echo "✓ Call successful"
}

# تابع async communication
async_communication() {
    local service=$1
    local event=$2
    local payload=$3
    
    echo -e "\n5. Async communication (Event Bus):"
    echo "  Publisher: $service"
    echo "  Event: $event"
    echo "  Payload: $payload"
    
    # Simulate event publishing
    echo "  Published to: order.created topic"
    echo "  Subscribers: notification-service, analytics-service"
    echo "✓ Event published"
}

# تابع configure API gateway
configure_gateway() {
    echo -e "\n6. Configuring API Gateway:"
    
    cat > /tmp/gateway-config.yaml << 'EOF'
gateway:
  port: 8080
  routes:
    - path: /api/users/*
      service: user-service
      strip_prefix: /api/users
    - path: /api/orders/*
      service: order-service
      strip_prefix: /api/orders
    - path: /api/products/*
      service: product-service
      strip_prefix: /api/products
  middlewares:
    - authentication
    - rate_limiting
    - logging
EOF
    
    echo "✓ Gateway configuration created"
}

# تابع route through gateway
route_through_gateway() {
    local path=$1
    
    echo -e "\n7. Routing through API Gateway:"
    echo "  Request: $path"
    
    case "$path" in
        "/api/users"*)
            echo "  Matched route: user-service"
            echo "  Target: http://user-service:8081/"
            ;;
        "/api/orders"*)
            echo "  Matched route: order-service"
            echo "  Target: http://order-service:8082/"
            ;;
        "/api/products"*)
            echo "  Matched route: product-service"
            echo "  Target: http://product-service:8083/"
            ;;
        *)
            echo "  No route matched - 404"
            ;;
    esac
    
    echo "✓ Routing complete"
}

# تابع circuit breaker setup
setup_circuit_breaker() {
    local service=$1
    
    echo -e "\n8. Setting up circuit breaker for $service:"
    echo "  Threshold: 5 failures"
    echo "  Timeout: 30 seconds"
    echo "  Half-open attempts: 3"
    echo "✓ Circuit breaker configured"
}

# تابع test communication
test_communication() {
    echo -e "\n9. Testing communication:"
    
    echo "  Test 1: User -> Order service"
    inter_service_call "user-service" "order-service" "/api/orders"
    
    echo -e "\n  Test 2: Order -> Product service"
    inter_service_call "order-service" "product-service" "/api/products/123"
    
    echo -e "\n  Test 3: Async event"
    async_communication "order-service" "order.created" "{\"orderId\":123}"
}

# تابع show topology
show_topology() {
    echo -e "\n10. Service Topology:"
    echo "  ┌─────────────┐"
    echo "  │ API Gateway │"
    echo "  └──────┬──────┘"
    echo "         │"
    echo "    ┌────┴────┬────┬────┐"
    echo "    │         │    │    │"
    echo "  ┌───┴───┐ ┌───┴───┐ ┌───┴───┐"
    echo "  │ User  │ │ Order │ │Product│"
    echo "  │Service│ │Service│ │Service│"
    echo "  └───────┘ └───┬───┘ └───────┘"
    echo "              │"
    echo "         ┌────┴────┐"
    echo "         │Payment  │"
    echo "         │Service  │"
    echo "         └─────────┘"
}

# اجرا
setup_service_registry
register_service "user-service" "192.168.1.10" "8081"
register_service "order-service" "192.168.1.11" "8082"
discover_service "user-service"
discover_service "order-service"
inter_service_call "user-service" "order-service" "/api/orders"
async_communication "order-service" "order.created" "{\"orderId\":123}"
configure_gateway
route_through_gateway "/api/users/1"
setup_circuit_breaker "payment-service"
test_communication
show_topology
