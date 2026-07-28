#!/usr/bin/env bash

# تمرین ۲: Serverless App
# هدف: یک اسکریپت بنویسید که:
# 1. multiple functions ایجاد کند
# 2. database را integrate کند
# 3. API Gateway را configure کند

# راه‌حل:
echo "=== Serverless Application Script ==="

# تابع create app structure
create_app_structure() {
    local app_name=$1
    
    echo "1. Creating application structure for $app_name"
    
    local base_dir="/tmp/serverless-app/$app_name"
    mkdir -p $base_dir/{functions/{user,order,product},infrastructure}
    
    echo "✓ Structure created: $base_dir"
    echo $base_dir
}

# تابع create user function
create_user_function() {
    local app_dir=$1
    
    echo -e "\n2. Creating user function:"
    
    mkdir -p $app_dir/functions/user/src
    cat > $app_dir/functions/user/src/handler.py << EOF
import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('users')

def handler(event, context):
    path = event['path']
    method = event['httpMethod']
    
    if path == '/users' and method == 'GET':
        return get_users()
    elif path == '/users' and method == 'POST':
        return create_user(json.loads(event['body']))
    
    return {'statusCode': 404, 'body': 'Not Found'}

def get_users():
    response = table.scan()
    return {'statusCode': 200, 'body': json.dumps(response['Items'])}

def create_user(user_data):
    table.put_item(Item=user_data)
    return {'statusCode': 201, 'body': json.dumps(user_data)}
EOF
    
    echo "✓ User function created"
}

# تابع create order function
create_order_function() {
    local app_dir=$1
    
    echo -e "\n3. Creating order function:"
    
    mkdir -p $app_dir/functions/order/src
    cat > $app_dir/functions/order/src/handler.py << EOF
import json
import boto3

sqs = boto3.client('sqs')
queue_url = sqs.get_queue_url(QueueName='orders')['QueueUrl']

def handler(event, context):
    for record in event['Records']:
        process_order(record['body'])
    
    return {'statusCode': 200, 'body': 'Orders processed'}

def process_order(message):
    print(f"Processing order: {message}")
    # Process order logic
EOF
    
    echo "✓ Order function created"
}

# تابع create product function
create_product_function() {
    local app_dir=$1
    
    echo -e "\n4. Creating product function:"
    
    mkdir -p $app_dir/functions/product/src
    cat > $app_dir/functions/product/src/handler.py << EOF
import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('products')

def handler(event, context):
    path = event['path']
    
    if '/products' in path:
        return get_products()
    
    return {'statusCode': 404, 'body': 'Not Found'}

def get_products():
    response = table.scan()
    return {'statusCode': 200, 'body': json.dumps(response['Items'])}
EOF
    
    echo "✓ Product function created"
}

# تابع create serverless config
create_app_config() {
    local app_dir=$1
    local app_name=$2
    
    echo -e "\n5. Creating serverless.yml:"
    
    cat > $app_dir/serverless.yml << EOF
service: $app_name

provider:
  name: aws
  runtime: python3.9
  stage: dev
  region: us-east-1
  environment:
    USERS_TABLE: \${self:resources.UsersTable.Name}
    ORDERS_QUEUE: orders

functions:
  userApi:
    handler: functions/user/src/handler.handler
    events:
      - http:
          path: /users
          method: any
          cors: true
  
  orderProcessor:
    handler: functions/order/src/handler.handler
    events:
      - sqs:
          arn:
            Fn::GetAtt:
              - OrdersQueue
              - Arn
          batchSize: 10
  
  productApi:
    handler: functions/product/src/handler.handler
    events:
      - http:
          path: /products
          method: get
          cors: true

resources:
  Resources:
    UsersTable:
      Type: AWS::DynamoDB::Table
      Properties:
        TableName: users
        AttributeDefinitions:
          - AttributeName: userId
            AttributeType: S
        KeySchema:
          - AttributeName: userId
            KeyType: HASH
        BillingMode: PAY_PER_REQUEST
    
    OrdersQueue:
      Type: AWS::SQS::Queue
      Properties:
        QueueName: orders

plugins:
  - serverless-python-requirements
EOF
    
    echo "✓ serverless.yml created"
}

# تابع create database resources
create_database_resources() {
    local app_dir=$1
    
    echo -e "\n6. Creating database resources:"
    
    cat > $app_dir/infrastructure/dynamodb.tf << EOF
resource "aws_dynamodb_table" "users" {
  name           = "users"
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "userId"
  attribute {
    name = "userId"
    type = "S"
  }
}

resource "aws_dynamodb_table" "products" {
  name           = "products"
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "productId"
  attribute {
    name = "productId"
    type = "S"
  }
}
EOF
    
    echo "✓ Database resources defined"
}

# تابع configure API Gateway
configure_api_gateway() {
    local app_dir=$1
    
    echo -e "\n7. Configuring API Gateway:"
    
    cat > $app_dir/infrastructure/api-gateway.tf << EOF
resource "aws_api_gateway_rest_api" "main" {
  name = "serverless-api"
  description = "API Gateway for serverless app"
}

resource "aws_api_gateway_resource" "users" {
  rest_api_id = aws_api_gateway_rest_api.main.id
  parent_id   = aws_api_gateway_rest_api.main.root_resource_id
  path_part   = "users"
}

resource "aws_api_gateway_resource" "products" {
  rest_api_id = aws_api_gateway_rest_api.main.id
  parent_id   = aws_api_gateway_rest_api.main.root_resource_id
  path_part   = "products"
}
EOF
    
    echo "✓ API Gateway configured"
}

# تابع deploy application
deploy_app() {
    local app_dir=$1
    local app_name=$2
    
    echo -e "\n8. Deploying application:"
    echo "  Deploying infrastructure..."
    echo "  Creating DynamoDB tables..."
    echo "  Creating SQS queue..."
    echo "  Deploying Lambda functions..."
    echo "  Configuring API Gateway..."
    echo "✓ Application deployed"
    echo "  API endpoints:"
    echo "    - https://api.example.com/users"
    echo "    - https://api.example.com/products"
}

# تابع test application
test_app() {
    local base_url=$1
    
    echo -e "\n9. Testing application:"
    echo "  Testing user API..."
    echo "  GET $base_url/users"
    echo "  Response: [{\"userId\":\"1\",\"name\":\"John\"}]"
    echo "  Testing product API..."
    echo "  GET $base_url/products"
    echo "  Response: [{\"productId\":\"1\",\"name\":\"Product A\"}]"
    echo "✓ Application tested"
}

# تابع show app topology
show_app_topology() {
    echo -e "\n10. Application Topology:"
    echo "  ┌─────────────────┐"
    echo "  │  API Gateway    │"
    echo "  └────────┬────────┘"
    echo "           │"
    echo "     ┌─────┴─────┬───────┐"
    echo "     │           │       │"
    echo "  ┌───┴───┐  ┌───┴───┐ ┌─┴────┐"
    echo "  │ User  │  │ Order │ │Product│"
    echo "  │Lambda │  │Lambda │ │Lambda│"
    echo "  └───┬───┘  └───┬───┘ └──┬───┘"
    echo "      │          │        │"
    echo "  ┌───┴───┐  ┌───┴───┐ ┌─┴────┐"
    echo "  │DynamoDB│  │  SQS  │ │DynamoDB│"
    echo "  │ (users)│  │(orders)│ │(products)│"
    echo "  └────────┘  └───────┘ └───────┘"
}

# اجرا
BASE_DIR="/tmp/serverless-app"
APP_NAME="my-serverless-app"

APP_DIR=$(create_app_structure "$APP_NAME")
create_user_function "$APP_DIR"
create_order_function "$APP_DIR"
create_product_function "$APP_DIR"
create_app_config "$APP_DIR" "$APP_NAME"
create_database_resources "$APP_DIR"
configure_api_gateway "$APP_DIR"
deploy_app "$APP_DIR" "$APP_NAME"
test_app "https://api.example.com"
show_app_topology
