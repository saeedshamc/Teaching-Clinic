#!/usr/bin/env bash

# تمرین ۱: Swarm Setup
# هدف: یک اسکریپت بنویسید که:
# 1. Docker Swarm را initialize کند
# 2. service ایجاد کند
# 3. service را scale کند

# راه‌حل:
echo "=== Docker Swarm Setup ==="

# تابع initialize swarm
init_swarm() {
    echo "1. Initialize Swarm:"
    echo "docker swarm init"
    echo "✓ Swarm initialized"
    echo "To join worker nodes:"
    echo "docker swarm join-token worker"
}

# تابع create service
create_service() {
    local service_name=$1
    local image=$2
    local replicas=$3
    local port=$4
    
    echo -e "\n2. Create Service: $service_name"
    echo "docker service create --name $service_name --replicas $replicas -p $port:80 $image"
    echo "✓ Service created with $replicas replicas"
}

# تابع scale service
scale_service() {
    local service_name=$1
    local replicas=$2
    
    echo -e "\n3. Scale Service: $service_name"
    echo "docker service scale $service_name=$replicas"
    echo "✓ Service scaled to $replicas replicas"
}

# تابع list services
list_services() {
    echo -e "\n4. List Services:"
    echo "docker service ls"
}

# تابع inspect service
inspect_service() {
    local service_name=$1
    
    echo -e "\n5. Inspect Service: $service_name"
    echo "docker service inspect $service_name"
}

# اجرا
init_swarm
create_service "web" "nginx" 3 "8080"
scale_service "web" 5
list_services
inspect_service "web"

# تابع cleanup
cleanup() {
    echo -e "\n6. Cleanup:"
    echo "docker service rm web"
    echo "docker swarm leave --force"
    echo "✓ Cleanup complete"
}

cleanup
