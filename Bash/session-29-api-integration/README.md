# جلسه ۲۹: یکپارچه‌سازی API (API Integration)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه REST API
- استفاده از curl برای API calls
- JSON processing با jq
- Authentication با API
- Error Handling
- Rate Limiting
- مثال‌های عملی

## مفاهیم پایه REST API

REST API یک معماری برای web services است:
- **GET** - دریافت داده
- **POST** - ایجاد داده
- **PUT/PATCH** - به‌روزرسانی داده
- **DELETE** - حذف داده

## curl برای API Calls

```bash
curl -X GET https://api.example.com/data
curl -X POST https://api.example.com/data -d '{"key":"value"}'
```

## مثال ۱: curl Basics

فایل [examples/01-curl-basics.sh](examples/01-curl-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== curl برای API ==="

# GET request
echo "1. GET Request:"
curl -X GET https://jsonplaceholder.typicode.com/posts/1

# POST request
echo -e "\n2. POST Request:"
curl -X POST https://jsonplaceholder.typicode.com/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","body":"Test body","userId":1}'

# PUT request
echo -e "\n3. PUT Request:"
curl -X PUT https://jsonplaceholder.typicode.com/posts/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated"}'

# DELETE request
echo -e "\n4. DELETE Request:"
curl -X DELETE https://jsonplaceholder.typicode.com/posts/1
```

### توضیح خط به خط:

- `curl -X GET` - GET request
- `curl -X POST` - POST request
- `-H` - header
- `-d` - data
- JSONPlaceholder برای تست

## JSON Processing با jq

```bash
# نصب jq
sudo apt install jq

# استفاده از jq
curl api | jq '.key'
```

## مثال ۲: JSON Processing

فایل [examples/02-json-processing.sh](examples/02-json-processing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== JSON Processing با jq ==="

# بررسی نصب jq
if ! command -v jq &>/dev/null; then
    echo "jq نصب نیست"
    echo "نصب با: sudo apt install jq"
    exit 1
fi

# دریافت و پردازش JSON
echo "1. دریافت و پردازش JSON:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '.'

# استخراج فیلد خاص
echo -e "\n2. استخراج فیلد خاص:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '.title'

# استخراج چند فیلد
echo -e "\n3. استخراج چند فیلد:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '{title, userId}'

# فیلتر کردن
echo -e "\n4. فیلتر کردن:"
curl -s https://jsonplaceholder.typicode.com/posts | jq '.[] | select(.userId == 1)'

# تبدیل به CSV
echo -e "\n5. تبدیل به CSV:"
curl -s https://jsonplaceholder.typicode.com/posts | jq -r '.[] | [.id, .title] | @csv'
```

### توضیح خط به خط:

- `jq '.'` - نمایش JSON
- `jq '.key'` - استخراج فیلد
- `jq '{key1, key2}'` - چند فیلد
- `jq 'select(...)'` - فیلتر
- `jq -r '@csv'` - تبدیل به CSV

## Authentication

```bash
# Basic Auth
curl -u user:pass https://api.example.com

# Bearer Token
curl -H "Authorization: Bearer TOKEN" https://api.example.com

# API Key
curl -H "X-API-Key: KEY" https://api.example.com
```

## مثال ۳: API Authentication

فایل [examples/03-api-auth.sh](examples/03-api-auth.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== API Authentication ==="

# Basic Auth
echo "1. Basic Authentication:"
echo "curl -u username:password https://api.example.com/data"

# Bearer Token
echo -e "\n2. Bearer Token:"
TOKEN="your_token_here"
echo "curl -H \"Authorization: Bearer \$TOKEN\" https://api.example.com/data"

# API Key
echo -e "\n3. API Key:"
API_KEY="your_api_key"
echo "curl -H \"X-API-Key: \$API_KEY\" https://api.example.com/data"

# OAuth2
echo -e "\n4. OAuth2:"
echo "1. دریافت access token"
echo "2. استفاده از token در requests"
echo "curl -H \"Authorization: Bearer \$ACCESS_TOKEN\" https://api.example.com/data"
```

### توضیح خط به خط:

- `curl -u` - basic auth
- `Authorization: Bearer` - token auth
- `X-API-Key` - API key
- OAuth2 flow برای token

## Error Handling

```bash
# بررسی HTTP status code
curl -s -o /dev/null -w "%{http_code}" https://api.example.com

# retry logic
for i in {1..3}; do curl ... && break; done
```

## مثال ۴: Error Handling

فایل [examples/04-error-handling.sh](examples/04-error-handling.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `-w "%{http_code}"` - HTTP status code
- case statement برای status codes
- retry loop برای تلاش مجدد
- error handling کامل

## Rate Limiting

```bash
# احترام به rate limits
sleep بین requests
# استفاده از exponential backoff
```

## مثال ۵: Rate Limiting

فایل [examples/05-rate-limiting.sh](examples/05-rate-limiting.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- delay بین requests
- exponential backoff برای rate limits
- HTTP 429 برای rate limit
- افزایش wait time

## نکات مهم

### ۱. امنیت Credentials

```bash
# هرگز credentials را در کد ذخیره نکنید
# از environment variables استفاده کنید
export API_KEY="your_key"
```

### ۲. Validation

```bash
# همیشه response را validate کنید
# بررسی structure و data types
```

### ۳. Logging

```bash
# همه API calls را log کنید
# برای debugging و monitoring
```

## مثال ۶: API Integration Best Practices

فایل [examples/06-api-best-practices.sh](examples/06-api-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== API Integration Best Practices ==="

# 1. Environment Variables
echo -e "\n1. Environment Variables:"
echo "✓ API keys در environment variables"
echo "✓ Secrets در secret managers"
echo "✓ Configuration در config files"

# 2. Error Handling
echo -e "\n2. Error Handling:"
echo "✓ بررسی HTTP status codes"
echo "✓ Retry logic با backoff"
echo "✓ Graceful degradation"

# 3. Rate Limiting
echo -e "\n3. Rate Limiting:"
echo "✓ احترام به rate limits"
echo "✓ Exponential backoff"
echo "✓ Request queuing"

# 4. Validation
echo -e "\n4. Validation:"
echo "✓ Validate response structure"
echo "✓ Check data types"
echo "✓ Handle edge cases"

# 5. Logging
echo -e "\n5. Logging:"
echo "✓ Log تمام API calls"
echo "✓ Log errors و warnings"
echo "✓ Log response times"
```

### توضیح خط به خط:

- Environment variables برای secrets
- Error handling کامل
- Rate limiting احترام
- Validation response
- Logging برای monitoring

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. Hardcoded Credentials

❌ خطرناک:
```bash
API_KEY="secret"  - خطرناک
```

✅ درست:
```bash
export API_KEY=$ENV_API_KEY  - امن
```

### ۲. بدون Error Handling

❌ اشتباه:
```bash
# بدون error handling
```

✅ درست:
```bash
# error handling و retry
```

### ۳. نادیده گرفتن Rate Limits

❌ اشتباه:
```bash
# بدون rate limiting
```

✅ درست:
```bash
# احترام به rate limits
```

## بهترین شیوه‌ها (Best Practices)

1. **Environment variables** - برای secrets
2. **Error handling** - retry و backoff
3. **Rate limiting** - احترام به limits
4. **Validation** - بررسی response
5. **Logging** - ثبت تمام calls

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه REST API
- curl برای API calls
- JSON processing با jq
- Authentication
- Error Handling
- Rate Limiting
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): API Client

یک اسکریپت بنویسید که:
1. API call انجام دهد
2. JSON response را پردازش کند
3. error handling داشته باشد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): API Wrapper

یک اسکریپت بنویسید که:
1. wrapper برای API ایجاد کند
2. retry logic داشته باشد
3. rate limiting را مدیریت کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Logging Systems را یاد می‌گیریم.
