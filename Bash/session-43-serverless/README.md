# جلسه ۴۳: سرورلس (Serverless)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه serverless
- Function as a Service (FaaS)
- Serverless frameworks
- Event-driven architecture
- Database services
- Best practices
- مثال‌های عملی

## مفاهیم پایه Serverless

Serverless بدون managing servers است:
- **FaaS** - Function as a Service
- **BaaS** - Backend as a Service
- **Event-driven** - trigger بر اساس events
- **Auto-scaling** - automatic scaling
- **Pay-per-use** - pay برای actual usage

## Function as a Service

FaaS running code بدون managing servers است.

```bash
# AWS Lambda
- Azure Functions
# Google Cloud Functions
```

## مثال ۱: FaaS Basics

فایل [examples/01-faas-basics.sh](examples/01-faas-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== FaaS Basics ==="

# تابع create Lambda function
create_lambda() {
    local function_name=$1
    local runtime=$2
    
    echo "Creating Lambda function:"
    echo "  Name: $function_name"
    echo "  Runtime: $runtime"
    echo "  Handler: index.handler"
    echo "✓ Function created"
}

# تابع deploy function
deploy_function() {
    local function=$1
    local zip_file=$2
    
    echo -e "\nDeploying function $function:"
    echo "  Package: $zip_file"
    echo "  Uploading to cloud..."
    echo "✓ Function deployed"
}

# تابع invoke function
invoke_function() {
    local function=$1
    local payload=$2
    
    echo -e "\nInvoking function $function:"
    echo "  Payload: $payload"
    echo "  Response: {\"statusCode\":200,\"body\":\"Hello\"}"
}

# تابع list functions
list_functions() {
    echo -e "\nAvailable functions:"
    echo "  - hello-world"
    echo "  - process-order"
    echo "  - send-email"
}

# استفاده
create_lambda "hello-world" "python3.9"
deploy_function "hello-world" "function.zip"
invoke_function "hello-world" '{"name":"World"}'
list_functions
```

### توضیح خط به خط:

- Lambda function creation
- function deployment
- function invocation
- listing functions
- FaaS basics

## Serverless Frameworks

Serverless frameworks برای deploying serverless apps هستند.

```bash
# Serverless Framework
- SAM (AWS)
# Terraform
```

## مثال ۲: Serverless Framework

فایل [examples/02-serverless-framework.sh](examples/02-serverless-framework.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Serverless Framework ==="

# تابع create serverless.yml
create_serverless_config() {
    cat > serverless.yml << 'EOF'
service: my-service

provider:
  name: aws
  runtime: python3.9

functions:
  hello:
    handler: handler.hello
    events:
      - http:
          path: hello
          method: get
  
  processOrder:
    handler: handler.processOrder
    events:
      - sqs:
          arn: arn:aws:sqs:region:account:queue
EOF
    
    echo "✓ serverless.yml created"
}

# تابع deploy with framework
deploy_serverless() {
    echo -e "\nDeploying with Serverless Framework:"
    echo "  Command: serverless deploy"
    echo "  Packaging service..."
    echo "  Deploying to AWS..."
    echo "✓ Service deployed"
}

# تابع remove service
remove_service() {
    echo -e "\nRemoving service:"
    echo "  Command: serverless remove"
    echo "  Removing all resources..."
    echo "✓ Service removed"
}

# تابع local invoke
local_invoke() {
    local function=$1
    
    echo -e "\nLocal invoke of $function:"
    echo "  Command: serverless invoke local --function $function"
    echo "  Response: {\"result\":\"success\"}"
}

# استفاده
create_serverless_config
deploy_serverless
local_invoke "hello"
# remove_service
```

### توضیح خط به خط:

- serverless.yml creation
- framework deployment
- service removal
- local invocation
- Serverless Framework

## Event-Driven Architecture

Event-driven برای trigger بر اساس events است.

```bash
# S3 events
- SQS messages
# SNS notifications
```

## مثال ۳: Event-Driven Architecture

فایل [examples/03-event-driven.sh](examples/03-event-driven.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Event-Driven Architecture ==="

# تابع setup S3 trigger
setup_s3_trigger() {
    local bucket=$1
    local function=$2
    
    echo "Setting up S3 trigger:"
    echo "  Bucket: $bucket"
    echo "  Event: s3:ObjectCreated:*"
    echo "  Function: $function"
    echo "✓ Trigger configured"
}

# تابع setup SQS trigger
setup_sqs_trigger() {
    local queue=$1
    local function=$2
    
    echo -e "\nSetting up SQS trigger:"
    echo "  Queue: $queue"
    echo "  Function: $function"
    echo "✓ Trigger configured"
}

# تابع setup SNS trigger
setup_sns_trigger() {
    local topic=$1
    local function=$2
    
    echo -e "\nSetting up SNS trigger:"
    echo "  Topic: $topic"
    echo "  Function: $function"
    echo "✓ Trigger configured"
}

# تابع setup API Gateway
setup_api_gateway() {
    local function=$1
    local path=$2
    
    echo -e "\nSetting up API Gateway:"
    echo "  Path: $path"
    echo "  Function: $function"
    echo "  Method: POST"
    echo "✓ API configured"
}

# استفاده
setup_s3_trigger "my-bucket" "process-file"
setup_sqs_trigger "order-queue" "process-order"
setup_sns_trigger "notifications" "send-notification"
setup_api_gateway "hello" "/api/hello"
```

### توضیح خط به خط:

- S3 event triggers
- SQS message triggers
- SNS notification triggers
- API Gateway integration
- event sources

## Database Services

Serverless database services هستند.

```bash
# DynamoDB
- Aurora Serverless
# Firebase
```

## مثال ۴: Database Services

فایل [examples/04-database-services.sh](examples/04-database-services.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Services ==="

# تابع create DynamoDB table
create_dynamodb_table() {
    local table=$1
    local key=$2
    
    echo "Creating DynamoDB table:"
    echo "  Table: $table"
    echo "  Partition key: $key"
    echo "  Billing mode: PAY_PER_REQUEST"
    echo "✓ Table created"
}

# تابع put item
put_item() {
    local table=$1
    local item=$2
    
    echo -e "\nPutting item into $table:"
    echo "  Item: $item"
    echo "✓ Item inserted"
}

# تابع get item
get_item() {
    local table=$1
    local key=$2
    
    echo -e "\nGetting item from $table:"
    echo "  Key: $key"
    echo "  Item: {\"id\":\"$key\",\"data\":\"...\"}"
}

# تابع query table
query_table() {
    local table=$1
    local filter=$2
    
    echo -e "\nQuerying $table:"
    echo "  Filter: $filter"
    echo "  Results: [{...}, {...}]"
}

# استفاده
create_dynamodb_table "users" "userId"
put_item "users" '{"userId":"1","name":"John"}'
get_item "users" "1"
query_table "users" "name=John"
```

### توضیح خط به خط:

- DynamoDB table creation
- item operations
- querying
- serverless database
- pay-per-request billing

## نکات مهم

### ۱: Cold Starts

```bash
# Minimize cold starts
- Keep functions warm
# Optimize initialization
```

### ۲: Statelessness

```bash
# Functions should be stateless
- Use external storage
# No local state
```

### ۳: Timeouts

```bash
# Watch execution time
- Set appropriate timeouts
# Handle long-running tasks
```

## مثال ۵: Serverless Best Practices

فایل [examples/05-serverless-best-practices.sh](examples/05-serverless-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Serverless Best Practices ==="

# 1. Function Design
echo -e "\n1. Function Design:"
echo "✓ Single responsibility"
echo "✓ Short execution time"
echo "✓ Stateless functions"

# 2. Performance
echo -e "\n2. Performance:"
echo "✓ Minimize cold starts"
echo "✓ Optimize package size"
echo "✓ Use appropriate memory"

# 3. Security
echo -e "\n3. Security:"
echo "✓ Least privilege IAM"
echo "✓ Encrypt sensitive data"
echo "✓ Use VPC when needed"

# 4. Monitoring
echo -e "\n4. Monitoring:"
echo "✓ Enable logging"
echo "✓ Monitor metrics"
echo "✓ Set up alerts"

# 5. Cost Optimization
echo -e "\n5. Cost Optimization:"
echo "✓ Right-size memory"
echo "✓ Use provisioned concurrency"
echo "✓ Monitor usage"
```

### توضیح خط به خط:

- single responsibility
- performance optimization
- security best practices
- comprehensive monitoring
- cost optimization

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: Long-Running Functions

❌ اشتباه:
```bash
# long-running functions
```

✅ درست:
```bash
# break into smaller functions
```

### ۲: Stateful Functions

❌ اشتباه:
```bash
# stateful functions
```

✅ درست:
```bash
# stateless functions
```

### ۳: بدون Monitoring

❌ اشتباه:
```bash
# بدون monitoring
```

✅ درست:
```bash
# comprehensive monitoring
```

## بهترین شیوه‌ها (Best Practices)

1. **Function Design** - single responsibility
2. **Performance** - minimize cold starts
3. **Security** - least privilege
4. **Monitoring** - comprehensive logging
5. **Cost** - right-size resources

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه serverless
- Function as a Service
- Serverless frameworks
- Event-driven architecture
- Database services
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Serverless Function

یک اسکریپت بنویسید که:
1. serverless function ایجاد کند
2. event trigger را setup کند
3. function را deploy کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Serverless App

یک اسکریپت بنویسید که:
1. multiple functions ایجاد کند
2. database را integrate کند
3. API Gateway را configure کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Advanced Database را یاد می‌گیریم.
