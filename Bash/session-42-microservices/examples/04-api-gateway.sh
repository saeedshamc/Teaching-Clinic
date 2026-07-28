#!/usr/bin/env bash

echo "=== API Gateway ==="

# تابع route request
route_request() {
    local path=$1
    
    echo "Routing request: $path"
    
    case "$path" in
        "/api/users"*)
            echo "  Routing to user-service"
            echo "  Target: http://user-service:8080$path"
            ;;
        "/api/orders"*)
            echo "  Routing to order-service"
            echo "  Target: http://order-service:8080$path"
            ;;
        "/api/products"*)
            echo "  Routing to product-service"
            echo "  Target: http://product-service:8080$path"
            ;;
        "/api/payments"*)
            echo "  Routing to payment-service"
            echo "  Target: http://payment-service:8080$path"
            ;;
        *)
            echo "  Unknown path - returning 404"
            ;;
    esac
}

# تابع authenticate
authenticate() {
    local token=$1
    
    echo -e "\nAuthenticating token:"
    echo "  Token: ${token:0:10}..."
    
    if [ -n "$token" ]; then
        echo "  Valid: ✓"
        echo "  User ID: user-123"
    else
        echo "  Valid: ✗"
        echo "  Error: Missing token"
    fi
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
    echo "  User service: {\"id\":1,\"name\":\"John\"}"
    echo "  Order service: {\"id\":101,\"status\":\"pending\"}"
    echo "  Combined: {\"user\":{\"id\":1,\"name\":\"John\"},\"order\":{\"id\":101,\"status\":\"pending\"}}"
}

# تابع transform response
transform_response() {
    local format=$1
    
    echo -e "\nTransforming response to $format:"
    echo "  Original: JSON"
    echo "  Transformed: $format"
}

# تابع cache response
cache_response() {
    local key=$1
    local ttl=$2
    
    echo -e "\nCaching response:"
    echo "  Key: $key"
    echo "  TTL: $ttl seconds"
    echo "  ✓ Cached"
}

# استفاده
route_request "/api/users/1"
authenticate "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
rate_limit "client-1"
aggregate_response
transform_response "XML"
cache_response "user-1" 300
