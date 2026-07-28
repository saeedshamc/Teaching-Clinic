#!/usr/bin/env bash

echo "=== State Management ==="

# تابع save state
save_state() {
    local state_file=$1
    local state=$2
    
    echo "$state" > "$state_file"
    echo "✓ State saved to $state_file"
}

# تابع load state
load_state() {
    local state_file=$1
    
    if [ -f "$state_file" ]; then
        cat "$state_file"
    else
        echo "No state found"
        return 1
    fi
}

# تابع update state
update_state() {
    local state_file=$1
    local key=$2
    local value=$3
    
    if [ -f "$state_file" ]; then
        if grep -q "^$key=" "$state_file"; then
            sed -i "s/^$key=.*/$key=$value/" "$state_file"
        else
            echo "$key=$value" >> "$state_file"
        fi
    else
        echo "$key=$value" > "$state_file"
    fi
    echo "✓ State updated: $key=$value"
}

# تابع get state value
get_state_value() {
    local state_file=$1
    local key=$2
    
    if [ -f "$state_file" ]; then
        grep "^$key=" "$state_file" | cut -d'=' -f2-
    else
        echo ""
    fi
}

# تابع export env
export_env() {
    local env_file=$1
    
    if [ -f "$env_file" ]; then
        set -a
        source "$env_file"
        set +a
        echo "✓ Environment loaded from $env_file"
    else
        echo "✗ Environment file not found: $env_file"
    fi
}

# تابع create env file
create_env_file() {
    local env_file=$1
    
    cat > "$env_file" << EOF
APP_NAME=myapp
APP_VERSION=1.0.0
DEBUG=true
LOG_LEVEL=info
EOF
    
    echo "✓ Environment file created: $env_file"
}

# تابع clear state
clear_state() {
    local state_file=$1
    
    if [ -f "$state_file" ]; then
        rm "$state_file"
        echo "✓ State cleared: $state_file"
    fi
}

# استفاده
echo "1. State persistence:"
save_state "/tmp/app.state" "status=running"
load_state "/tmp/app.state"

echo -e "\n2. State updates:"
update_state "/tmp/app.state" "status" "completed"
update_state "/tmp/app.state" "progress" "100"
get_state_value "/tmp/app.state" "status"

echo -e "\n3. Environment management:"
create_env_file "/tmp/.env"
export_env "/tmp/.env"
echo "APP_NAME: $APP_NAME"

echo -e "\n4. State cleanup:"
clear_state "/tmp/app.state"
