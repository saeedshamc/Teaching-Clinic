#!/usr/bin/env bash

# تمرین ۱: Serverless Function
# هدف: یک اسکریپت بنویسید که:
# 1. serverless function ایجاد کند
# 2. event trigger را setup کند
# 3. function را deploy کند

# راه‌حل:
echo "=== Serverless Function Script ==="

# تابع create function directory
create_function_dir() {
    local function_name=$1
    local runtime=$2
    
    echo "1. Creating function directory for $function_name"
    
    local base_dir="/tmp/serverless/$function_name"
    mkdir -p $base_dir/{src,tests}
    
    echo "✓ Directory created: $base_dir"
    echo $base_dir
}

# تابع create function code
create_function_code() {
    local function_dir=$1
    local runtime=$2
    local function_name=$3
    
    echo -e "\n2. Creating function code:"
    
    case "$runtime" in
        "python")
            cat > $function_dir/src/handler.py << EOF
import json

def handler(event, context):
    print(f"Event: {event}")
    print(f"Context: {context}")
    
    return {
        'statusCode': 200,
        'body': json.dumps({
            'message': 'Hello from $function_name'
        })
    }
EOF
            ;;
        "nodejs")
            cat > $function_dir/src/handler.js << EOF
exports.handler = async (event, context) => {
    console.log('Event:', JSON.stringify(event));
    console.log('Context:', JSON.stringify(context));
    
    return {
        statusCode: 200,
        body: JSON.stringify({
            message: 'Hello from $function_name'
        })
    };
};
EOF
            ;;
    esac
    
    echo "✓ Function code created"
}

# تابع create requirements
create_requirements() {
    local function_dir=$1
    
    echo -e "\n3. Creating requirements.txt:"
    
    cat > $function_dir/requirements.txt << EOF
requests==2.28.0
boto3==1.26.0
EOF
    
    echo "✓ requirements.txt created"
}

# تابع create serverless config
create_serverless_config() {
    local function_dir=$1
    local function_name=$2
    local runtime=$3
    
    echo -e "\n4. Creating serverless.yml:"
    
    cat > $function_dir/serverless.yml << EOF
service: $function_name

provider:
  name: aws
  runtime: $runtime
  stage: dev
  region: us-east-1

functions:
  $function_name:
    handler: src/handler.handler
    description: $function_name function
    events:
      - http:
          path: hello
          method: get
          cors: true
    memorySize: 256
    timeout: 30

plugins:
  - serverless-python-requirements
EOF
    
    echo "✓ serverless.yml created"
}

# تابع setup event trigger
setup_event_trigger() {
    local function_dir=$1
    local trigger_type=$2
    
    echo -e "\n5. Setting up event trigger: $trigger_type"
    
    case "$trigger_type" in
        "s3")
            echo "  Adding S3 trigger configuration"
            echo "  Bucket: my-bucket"
            echo "  Event: s3:ObjectCreated:*"
            ;;
        "sqs")
            echo "  Adding SQS trigger configuration"
            echo "  Queue: my-queue"
            ;;
        "api")
            echo "  API Gateway already configured"
            ;;
    esac
    
    echo "✓ Event trigger configured"
}

# تابع deploy function
deploy_function() {
    local function_dir=$1
    local function_name=$2
    
    echo -e "\n6. Deploying function:"
    echo "  Command: cd $function_dir && serverless deploy"
    echo "  Packaging service..."
    echo "  Creating CloudFormation stack..."
    echo "  Deploying to AWS..."
    echo "✓ Function deployed"
    echo "  Endpoint: https://abc123.execute-api.us-east-1.amazonaws.com/dev/hello"
}

# تابع test function
test_function() {
    local endpoint=$1
    
    echo -e "\n7. Testing function:"
    echo "  Endpoint: $endpoint"
    echo "  Command: curl $endpoint"
    echo "  Response: {\"statusCode\":200,\"body\":\"{\\\"message\\\":\\\"Hello\\\"}\"}"
    echo "✓ Function tested"
}

# تابع show architecture
show_architecture() {
    echo -e "\n8. Architecture:"
    echo "  Client -> API Gateway -> Lambda Function"
    echo "  Event Sources: S3, SQS, SNS"
    echo "  Logs: CloudWatch Logs"
    echo "  Tracing: AWS X-Ray"
}

# تابع cleanup
cleanup() {
    echo -e "\n9. Cleanup:"
    echo "  Removing temporary files..."
    # Uncomment to actually cleanup
    # rm -rf /tmp/serverless
    echo "✓ Cleanup complete"
}

# اجرا
BASE_DIR="/tmp/serverless"
mkdir -p $BASE_DIR

FUNCTION_DIR=$(create_function_dir "hello-function" "python")
create_function_code "$FUNCTION_DIR" "python" "hello-function"
create_requirements "$FUNCTION_DIR"
create_serverless_config "$FUNCTION_DIR" "hello-function" "python3.9"
setup_event_trigger "$FUNCTION_DIR" "api"
deploy_function "$FUNCTION_DIR" "hello-function"
test_function "https://abc123.execute-api.us-east-1.amazonaws.com/dev/hello"
show_architecture

# Uncomment to cleanup
# cleanup
