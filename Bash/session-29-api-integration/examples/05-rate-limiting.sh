#!/usr/bin/env bash

echo "=== Rate Limiting ==="

# تابع API call با rate limiting
api_call_rate_limited() {
    local url=$1
    local delay=1
    
    echo "API Call with rate limiting (delay: ${delay}s)"
    
    curl -s "$url"
    echo "✓ Request completed"
    
    echo "Sleeping ${delay}s..."
    sleep $delay
}

# تابع exponential backoff
api_call_backoff() {
    local url=$1
    local max_retries=5
    
    for i in $(seq 1 $max_retries); do
        echo "Attempt $i/$max_retries"
        
        local response=$(curl -s -w "\n%{http_code}" "$url")
        local http_code=$(echo "$response" | tail -n1)
        
        if [ "$http_code" = "200" ]; then
            echo "✓ Success"
            return 0
        elif [ "$http_code" = "429" ]; then
            echo "Rate limited, backing off..."
            local wait_time=$(( 2 ** i ))
            echo "Waiting ${wait_time}s..."
            sleep $wait_time
        else
            echo "Error: $http_code"
            return 1
        fi
    done
    
    echo "✗ Max retries reached"
    return 1
}

# اجرا
api_call_rate_limited "https://jsonplaceholder.typicode.com/posts/1"
