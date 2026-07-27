#!/usr/bin/env bash

# تمرین ۲: API Wrapper
# هدف: یک اسکریپت بنویسید که:
# 1. wrapper برای API ایجاد کند
# 2. retry logic داشته باشد
# 3. rate limiting را مدیریت کند

# راه‌حل:
echo "=== API Wrapper ==="

# تنظیمات
API_BASE="https://jsonplaceholder.typicode.com"
MAX_RETRIES=3
RATE_LIMIT_DELAY=1

# تابع API wrapper
api_request() {
    local method=$1
    local endpoint=$2
    local data=$3
    
    local attempt=1
    local success=false
    
    while [ $attempt -le $MAX_RETRIES ] && [ "$success" = false ]; do
        echo "Attempt $attempt/$MAX_RETRIES: $method $endpoint"
        
        local response
        if [ "$method" = "GET" ]; then
            response=$(curl -s -w "\n%{http_code}" "$API_BASE$endpoint")
        elif [ "$method" = "POST" ]; then
            response=$(curl -s -w "\n%{http_code}" -X POST \
                -H "Content-Type: application/json" \
                -d "$data" \
                "$API_BASE$endpoint")
        fi
        
        local http_code=$(echo "$response" | tail -n1)
        local body=$(echo "$response" | head -n-1)
        
        if [ "$http_code" = "200" ] || [ "$http_code" = "201" ]; then
            echo "✓ Success (HTTP $http_code)"
            echo "$body"
            success=true
            return 0
        elif [ "$http_code" = "429" ]; then
            echo "Rate limited, backing off..."
            local wait_time=$(( 2 ** attempt ))
            sleep $wait_time
        else
            echo "Error: HTTP $http_code"
        fi
        
        ((attempt++))
        sleep $RATE_LIMIT_DELAY
    done
    
    echo "✗ Max retries reached"
    return 1
}

# تابع GET wrapper
api_get() {
    api_request "GET" "$1" ""
}

# تابع POST wrapper
api_post() {
    api_request "POST" "$1" "$2"
}

# اجرا
echo "1. GET request:"
api_get "/posts/1"

echo -e "\n2. POST request:"
api_post "/posts" '{"title":"New Post","body":"Post body","userId":1}'

echo -e "\n3. Multiple GET requests:"
for i in {1..3}; do
    echo "Request $i:"
    api_get "/posts/$i"
    echo ""
done
