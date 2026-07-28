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
    echo "  Memory: 256 MB"
    echo "  Timeout: 30 seconds"
    echo "✓ Function created"
}

# تابع deploy function
deploy_function() {
    local function=$1
    local zip_file=$2
    
    echo -e "\nDeploying function $function:"
    echo "  Package: $zip_file"
    echo "  Uploading to cloud..."
    echo "  Creating CloudFormation stack..."
    echo "✓ Function deployed"
    echo "  Function ARN: arn:aws:lambda:us-east-1:123456789012:function:$function"
}

# تابع invoke function
invoke_function() {
    local function=$1
    local payload=$2
    
    echo -e "\nInvoking function $function:"
    echo "  Payload: $payload"
    echo "  Execution time: 45ms"
    echo "  Memory used: 128 MB"
    echo "  Response: {\"statusCode\":200,\"body\":\"Hello World\"}"
}

# تابع list functions
list_functions() {
    echo -e "\nAvailable functions:"
    echo "  - hello-world (Python 3.9)"
    echo "  - process-order (Node.js 14.x)"
    echo "  - send-email (Python 3.8)"
    echo "  - image-resize (Python 3.9)"
}

# تابع update function
update_function() {
    local function=$1
    local zip_file=$2
    
    echo -e "\nUpdating function $function:"
    echo "  New package: $zip_file"
    echo "  Updating code..."
    echo "✓ Function updated"
}

# تابع delete function
delete_function() {
    local function=$1
    
    echo -e "\nDeleting function $function:"
    echo "  Removing from AWS..."
    echo "✓ Function deleted"
}

# استفاده
create_lambda "hello-world" "python3.9"
deploy_function "hello-world" "function.zip"
invoke_function "hello-world" '{"name":"World"}'
list_functions
update_function "hello-world" "function-v2.zip"
# delete_function "hello-world"
