#!/usr/bin/env bash

echo "=== Error Handling ==="

# تابع API call با error handling
api_call() {
    local url=$1
    
    echo "API Call: $url"
    
    local response=$(curl -s -w "\n%{http_code}" "$url")
    local http_code=$(echo "$response" | tail -n1)
    local body=$(echo "$response" | head -n-1)
    
    echo "HTTP Code: $http_code"
    echo "Response: $body"
    
    case $http_code in
        200)
            echo "✓ Success"
            return 0
            ;;
        400)
            echo "✗ Bad Request"
            return 1
            ;;
        401)
            echo "✗ Unauthorized"
            return 1
            ;;
        404)
            echo "✗ Not Found"
            return 1
            ;;
        500)
            echo "✗ Server Error"
            return 1
            ;;
        *)
            echo "✗ Unknown Error"
            return 1
            ;;
    esac
}

# تابع retry
api_call_retry() {
    local url=$1
    local max_retries=3
    
    for i in $(seq 1 $max_retries); do
        echo "Attempt $i/$max_retries"
        if api_call "$url"; then
            return 0
        fi
        sleep 2
    done
    
    echo "✗ All attempts failed"
    return 1
}

# اجرا
api_call_retry "https://jsonplaceholder.typicode.com/posts/1"
