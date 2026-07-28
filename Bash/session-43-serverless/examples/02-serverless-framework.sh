#!/usr/bin/env bash

echo "=== Serverless Framework ==="

# تابع create serverless.yml
create_serverless_config() {
    cat > /tmp/serverless.yml << 'EOF'
service: my-service

provider:
  name: aws
  runtime: python3.9
  stage: dev
  region: us-east-1

functions:
  hello:
    handler: handler.hello
    description: Hello world function
    events:
      - http:
          path: hello
          method: get
          cors: true
    memorySize: 256
    timeout: 30
  
  processOrder:
    handler: handler.processOrder
    description: Process order function
    events:
      - sqs:
          arn: arn:aws:sqs:us-east-1:123456789012:order-queue
          batchSize: 10
    memorySize: 512
    timeout: 60
  
  sendEmail:
    handler: handler.sendEmail
    description: Send email function
    events:
      - sns:
          topicName: notifications
          displayName: Email notification topic
    memorySize: 128
    timeout: 10

resources:
  Resources:
    OrderQueue:
      Type: AWS::SQS::Queue
      Properties:
        QueueName: order-queue

plugins:
  - serverless-offline
  - serverless-python-requirements

custom:
  pythonRequirements:
    dockerizePip: true
EOF
    
    echo "✓ serverless.yml created at /tmp/serverless.yml"
}

# تابع deploy with framework
deploy_serverless() {
    echo -e "\nDeploying with Serverless Framework:"
    echo "  Stage: dev"
    echo "  Region: us-east-1"
    echo "  Packaging service..."
    echo "  Creating CloudFormation stack..."
    echo "  Deploying functions:"
    echo "    - hello: POST /dev/hello"
    echo "    - processOrder: SQS order-queue"
    echo "    - sendEmail: SNS notifications"
    echo "✓ Service deployed"
    echo "  Service endpoints:"
    echo "    https://abc123.execute-api.us-east-1.amazonaws.com/dev/hello"
}

# تابع remove service
remove_service() {
    echo -e "\nRemoving service:"
    echo "  Command: serverless remove --stage dev"
    echo "  Removing CloudFormation stack..."
    echo "  Deleting S3 bucket..."
    echo "  Removing IAM roles..."
    echo "✓ Service removed"
}

# تابع local invoke
local_invoke() {
    local function=$1
    local data=$2
    
    echo -e "\nLocal invoke of $function:"
    echo "  Command: serverless invoke local --function $function --data '$data'"
    echo "  Starting local server..."
    echo "  Response: {\"statusCode\":200,\"body\":\"Local execution successful\"}"
}

# تابع offline mode
offline_mode() {
    echo -e "\nStarting offline mode:"
    echo "  Command: serverless offline"
    echo "  Starting local API Gateway..."
    echo "  Serverless Offline: Server running on http://localhost:3000"
    echo "  Endpoints:"
    echo "    GET http://localhost:3000/dev/hello"
}

# تابع package only
package_service() {
    echo -e "\nPackaging service:"
    echo "  Command: serverless package --package my-service.zip"
    echo "  Creating deployment package..."
    echo "  Package size: 2.5 MB"
    echo "✓ Package created"
}

# استفاده
create_serverless_config
deploy_serverless
local_invoke "hello" '{"name":"Local"}'
offline_mode
package_service
# remove_service
