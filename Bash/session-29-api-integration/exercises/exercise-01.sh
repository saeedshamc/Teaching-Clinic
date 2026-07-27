#!/usr/bin/env bash

# تمرین ۱: API Client
# هدف: یک اسکریپت بنویسید که:
# 1. API call انجام دهد
# 2. JSON response را پردازش کند
# 3. error handling داشته باشد

# راه‌حل:
echo "=== API Client ==="

# تابع API call
api_get() {
    local endpoint=$1
    
    echo "GET: $endpoint"
    
    local response=$(curl -s -w "\n%{http_code}" "https://jsonplaceholder.typicode.com$endpoint")
    local http_code=$(echo "$response" | tail -n1)
    local body=$(echo "$response" | head -n-1)
    
    if [ "$http_code" = "200" ]; then
        echo "✓ Success"
        echo "$body"
        return 0
    else
        echo "✗ Error: HTTP $http_code"
        return 1
    fi
}

# تابع پردازش JSON
process_json() {
    local json=$1
    
    if command -v jq &>/dev/null; then
        echo "پردازش با jq:"
        echo "$json" | jq '{id, title, userId}'
    else
        echo "jq نصب نیست، نمایش raw:"
        echo "$json"
    fi
}

# اجرا
echo "1. دریافت post:"
response=$(api_get "/posts/1")
if [ $? -eq 0 ]; then
    process_json "$response"
fi

echo -e "\n2. دریافت user:"
response=$(api_get "/users/1")
if [ $? -eq 0 ]; then
    process_json "$response"
fi

echo -e "\n3. دریافت comments:"
response=$(api_get "/posts/1/comments")
if [ $? -eq 0 ]; then
    echo "تعداد comments: $(echo "$response" | jq 'length' 2>/dev/null || echo "N/A")"
fi
