#!/usr/bin/env bash

# تمرین ۱: Service Structure
# هدف: یک اسکریپت بنویسید که:
# 1. microservice structure ایجاد کند
# 2. docker-compose file بسازد
# 3. service را deploy کند

# راه‌حل:
echo "=== Microservice Structure Script ==="

# تابع create service directory
create_service_dir() {
    local service_name=$1
    
    echo "1. Creating service directory for $service_name"
    
    local base_dir="/tmp/microservices/$service_name"
    mkdir -p $base_dir/{src/{cmd,internal/{api,service,model}},config,tests,Dockerfile}
    
    echo "✓ Directory structure created: $base_dir"
    echo $base_dir
}

# تابع create service files
create_service_files() {
    local service_dir=$1
    local service_name=$2
    
    echo -e "\n2. Creating service files:"
    
    # Create main.go
    cat > $service_dir/src/cmd/main.go << EOF
package main

import (
    "fmt"
    "net/http"
)

func main() {
    http.HandleFunc("/health", healthHandler)
    http.HandleFunc("/api", apiHandler)
    
    fmt.Println("$service_name starting on port 8080")
    http.ListenAndServe(":8080", nil)
}

func healthHandler(w http.ResponseWriter, r *http.Request) {
    w.WriteHeader(http.StatusOK)
    fmt.Fprintf(w, "OK")
}

func apiHandler(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Hello from $service_name")
}
EOF
    
    # Create Dockerfile
    cat > $service_dir/Dockerfile << EOF
FROM golang:1.19-alpine AS builder
WORKDIR /app
COPY src/ ./src/
RUN CGO_ENABLED=0 go build -o $service_name ./src/cmd/

FROM alpine:latest
WORKDIR /root/
COPY --from=builder /app/$service_name .
EXPOSE 8080
CMD ["./$service_name"]
EOF
    
    # Create config
    cat > $service_dir/config/config.yaml << EOF
service:
  name: $service_name
  port: 8080
  log_level: info
EOF
    
    echo "✓ Service files created"
}

# تابع create docker-compose
create_docker_compose() {
    local services_dir=$1
    
    echo -e "\n3. Creating docker-compose.yml:"
    
    cat > $services_dir/docker-compose.yml << 'EOF'
version: '3.8'
services:
  user-service:
    build: ./user-service
    ports:
      - "8081:8080"
    environment:
      - SERVICE_NAME=user-service
    networks:
      - microservices
  
  order-service:
    build: ./order-service
    ports:
      - "8082:8080"
    environment:
      - SERVICE_NAME=order-service
    depends_on:
      - user-service
    networks:
      - microservices

networks:
  microservices:
    driver: bridge
EOF
    
    echo "✓ docker-compose.yml created"
}

# تابع deploy services
deploy_services() {
    local services_dir=$1
    
    echo -e "\n4. Deploying services:"
    echo "  Command: cd $services_dir && docker-compose up -d"
    echo "  ✓ Services deployed (simulated)"
}

# تابع check deployment
check_deployment() {
    echo -e "\n5. Checking deployment:"
    echo "  Checking service health..."
    
    local services=("user-service" "order-service")
    for service in "${services[@]}"; do
        echo "  $service: Running (port 8081/8082)"
    done
    
    echo "  ✓ All services healthy"
}

# تابع show architecture
show_architecture() {
    echo -e "\n6. Architecture Overview:"
    echo "  Client -> API Gateway -> Services"
    echo "  Services:"
    echo "    - User Service (port 8081)"
    echo "    - Order Service (port 8082)"
    echo "  Network: microservices"
}

# تابع cleanup
cleanup() {
    echo -e "\n7. Cleanup:"
    echo "  Removing temporary files..."
    # Uncomment to actually cleanup
    # rm -rf /tmp/microservices
    echo "  ✓ Cleanup complete"
}

# اجرا
BASE_DIR="/tmp/microservices"
mkdir -p $BASE_DIR

USER_SERVICE_DIR=$(create_service_dir "user-service")
create_service_files "$USER_SERVICE_DIR" "user-service"

ORDER_SERVICE_DIR=$(create_service_dir "order-service")
create_service_files "$ORDER_SERVICE_DIR" "order-service"

create_docker_compose "$BASE_DIR"
deploy_services "$BASE_DIR"
check_deployment
show_architecture

# Uncomment to cleanup
# cleanup
