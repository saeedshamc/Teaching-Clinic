#!/usr/bin/env bash

# تمرین ۲: Multi-Service App
# هدف: یک اسکریپت بنویسید که:
# 1. multiple services ایجاد کند
# 2. service configures کند
# 3. health checks اعمال کند

# راه‌حل:
echo "=== Multi-Service Application ==="

# تابع create network
create_network() {
    local network_name=$1
    
    echo "1. Create Overlay Network: $network_name"
    echo "docker network create --driver overlay $network_name"
    echo "✓ Network created"
}

# تابع create service with config
create_service_configured() {
    local service_name=$1
    local image=$2
    local network=$3
    local replicas=$4
    local port=$5
    local health_cmd=$6
    
    echo -e "\n2. Create Service: $service_name"
    echo "docker service create --name $service_name \\"
    echo "  --network $network \\"
    echo "  --replicas $replicas \\"
    echo "  -p $port:80 \\"
    echo "  --health-cmd '$health_cmd' \\"
    echo "  --health-interval 5s \\"
    echo "  --health-retries 3 \\"
    echo "  $image"
    echo "✓ Service created with health check"
}

# تابع create service with resources
create_service_resources() {
    local service_name=$1
    local image=$2
    local network=$3
    local cpu_limit=$4
    local memory_limit=$5
    
    echo -e "\n3. Create Service with Resources: $service_name"
    echo "docker service create --name $service_name \\"
    echo "  --network $network \\"
    echo "  --limit-cpu $cpu_limit \\"
    echo "  --limit-memory $memory_limit \\"
    echo "  $image"
    echo "✓ Service created with resource limits"
}

# تابع configure service
configure_service() {
    local service_name=$1
    
    echo -e "\n4. Configure Service: $service_name"
    echo "docker service update --env-add ENV=production $service_name"
    echo "✓ Service configured"
}

# تابع check health
check_health() {
    local service_name=$1
    
    echo -e "\n5. Check Service Health: $service_name"
    echo "docker service ps $service_name"
    echo "docker service inspect $service_name --format '{{.Spec.TaskTemplate.ContainerSpec.Healthcheck}}'"
}

# اجرا
NETWORK_NAME="app-network"

create_network "$NETWORK_NAME"

create_service_configured "web" "nginx" "$NETWORK_NAME" 3 "8080" "curl -f http://localhost/ || exit 1"
create_service_configured "api" "node:alpine" "$NETWORK_NAME" 2 "3000" "curl -f http://localhost:3000/health || exit 1"
create_service_resources "worker" "python:alpine" "$NETWORK_NAME" "0.5" "512M"

configure_service "web"
configure_service "api"

check_health "web"
check_health "api"

# تابع cleanup
cleanup() {
    echo -e "\n6. Cleanup:"
    echo "docker service rm web api worker"
    echo "docker network rm $NETWORK_NAME"
    echo "✓ Cleanup complete"
}

cleanup
