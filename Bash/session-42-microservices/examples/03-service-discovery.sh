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
    
    # Simulate registration
    cat > /tmp/service-registry.txt << EOF
$service:$address:$port
EOF
    
    echo "✓ Service registered"
}

# تابع discover service
discover_service() {
    local service=$1
    
    echo -e "\nDiscovering $service:"
    
    if [ -f "/tmp/service-registry.txt" ]; then
        local registry=$(cat /tmp/service-registry.txt)
        echo "  Registry: $registry"
        echo "  Found at: 192.168.1.10:8080"
        echo "  Health: OK"
    else
        echo "  Service not found in registry"
    fi
}

# تابع health check
health_check() {
    local service=$1
    
    echo -e "\nHealth check for $service:"
    
    if command -v curl &> /dev/null; then
        local health=$(curl -s -o /dev/null -w "%{http_code}" "http://$service/health" 2>/dev/null)
        
        if [ "$health" = "200" ]; then
            echo "  Status: HEALTHY"
        else
            echo "  Status: UNHEALTHY"
        fi
    else
        echo "  Status: HEALTHY (simulated)"
    fi
}

# تابع load balancing
load_balance() {
    local service=$1
    
    echo -e "\nLoad balancing $service:"
    echo "  Instance 1: 192.168.1.10:8080 (healthy)"
    echo "  Instance 2: 192.168.1.11:8080 (healthy)"
    echo "  Instance 3: 192.168.1.12:8080 (unhealthy)"
    echo "  Selected: Instance 1 (round-robin)"
}

# تابع service registry
show_registry() {
    echo -e "\nService Registry:"
    if [ -f "/tmp/service-registry.txt" ]; then
        cat /tmp/service-registry.txt
    else
        echo "  Registry is empty"
    fi
}

# استفاده
register_service "user-service" "192.168.1.10" "8080"
discover_service "user-service"
health_check "user-service:8080"
load_balance "user-service"
show_registry
