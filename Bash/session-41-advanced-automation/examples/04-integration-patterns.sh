#!/usr/bin/env bash

echo "=== Integration Patterns ==="

# تابع API integration
api_integration() {
    local endpoint=$1
    
    echo "API Integration with $endpoint:"
    
    # Make API call
    if command -v curl &> /dev/null; then
        local response=$(curl -s -w "\n%{http_code}" "$endpoint" 2>/dev/null)
        local http_code=$(echo "$response" | tail -1)
        local body=$(echo "$response" | head -n -1)
        
        echo "HTTP Status: $http_code"
        echo "Response: $body"
        
        # Process response
        if [ "$http_code" = "200" ]; then
            echo "✓ API call successful"
            process_api_response "$body"
        else
            echo "✗ API call failed"
        fi
    else
        echo "curl not available, simulating API call"
        echo "Response: {\"status\": \"ok\"}"
    fi
}

process_api_response() {
    local response=$1
    echo "  Processing response..."
    # Trigger next step based on response
}

# تابع database integration
database_integration() {
    local query=$1
    
    echo -e "\nDatabase Integration:"
    
    # Execute query
    if command -v mysql &> /dev/null; then
        echo "Executing query: $query"
        # local result=$(mysql -u user -p database -e "$query")
        echo "✓ Query executed"
    else
        echo "mysql not available, simulating query"
        echo "Result: Query executed successfully"
    fi
}

# تابع service integration
service_integration() {
    local service=$1
    local action=$2
    
    echo -e "\nService Integration with $service:"
    
    # Call service
    case "$service" in
        "email")
            echo "  Sending email: $action"
            echo "  ✓ Email sent"
            ;;
        "slack")
            echo "  Sending Slack message: $action"
            echo "  ✓ Message sent"
            ;;
        "sms")
            echo "  Sending SMS: $action"
            echo "  ✓ SMS sent"
            ;;
        *)
            echo "  Unknown service: $service"
            ;;
    esac
}

# تابع message queue integration
mq_integration() {
    local queue=$1
    
    echo -e "\nMessage Queue Integration:"
    echo "Queue: $queue"
    
    # Simulate message consumption
    for i in {1..3}; do
        echo "  Processing message $i"
        process_message "message-$i"
    done
}

process_message() {
    local message=$1
    echo "    Processed: $message"
}

# استفاده
api_integration "https://jsonplaceholder.typicode.com/posts/1"
database_integration "SELECT * FROM users"
service_integration "slack" "Deployment completed"
mq_integration "task-queue"
