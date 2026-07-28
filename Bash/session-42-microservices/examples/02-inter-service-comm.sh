#!/usr/bin/env bash

echo "=== Inter-service Communication ==="

# تابع REST API call
rest_api_call() {
    local service=$1
    local endpoint=$2
    
    echo "Calling $service via REST:"
    echo "  Endpoint: $endpoint"
    
    if command -v curl &> /dev/null; then
        local response=$(curl -s -w "\n%{http_code}" "http://$service$endpoint" 2>/dev/null)
        local http_code=$(echo "$response" | tail -1)
        local body=$(echo "$response" | head -n -1)
        
        echo "  HTTP Status: $http_code"
        echo "  Response: $body"
    else
        echo "  curl not available, simulating call"
        echo "  Response: {\"status\":\"ok\"}"
    fi
}

# تابع gRPC call
grpc_call() {
    local service=$1
    local method=$2
    
    echo -e "\nCalling $service via gRPC:"
    echo "  Method: $method"
    echo "  Note: grpcurl required for actual gRPC calls"
    echo "  Response: {\"result\":\"success\"}"
}

# تابع message queue
message_queue() {
    local queue=$1
    local message=$2
    
    echo -e "\nSending to queue $queue:"
    echo "  Message: $message"
    echo "  ✓ Message queued"
}

# تابع service mesh
service_mesh() {
    local source=$1
    local destination=$2
    
    echo -e "\nService mesh routing:"
    echo "  Source: $source"
    echo "  Destination: $destination"
    echo "  Route: via service mesh (Istio/Linkerd)"
    echo "  ✓ Request routed"
}

# تابع circuit breaker
circuit_breaker() {
    local service=$1
    local failures=$2
    
    echo -e "\nCircuit breaker for $service:"
    echo "  Failures: $failures"
    
    if [ $failures -gt 5 ]; then
        echo "  Circuit: OPEN (blocking requests)"
    else
        echo "  Circuit: CLOSED (allowing requests)"
    fi
}

# استفاده
rest_api_call "user-service:8080" "/api/users/1"
grpc_call "order-service:8080" "GetOrder"
message_queue "orders" "New order created"
service_mesh "web" "user-service"
circuit_breaker "payment-service" 3
