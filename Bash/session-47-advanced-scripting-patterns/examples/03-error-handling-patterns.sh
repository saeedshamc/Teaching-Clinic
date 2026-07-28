#!/usr/bin/env bash

echo "=== Error Handling Patterns ==="

# Try-Catch Pattern
try_catch() {
    local try_block=$1
    local catch_block=$2
    
    if eval "$try_block"; then
        echo "✓ Try block succeeded"
        return 0
    else
        echo "✗ Try block failed, executing catch"
        eval "$catch_block"
        return 1
    fi
}

# Retry Pattern
retry() {
    local max_attempts=$1
    local command=$2
    local delay=${3:-1}
    
    for ((i=1; i<=max_attempts; i++)); do
        echo "Attempt $i/$max_attempts"
        if eval "$command"; then
            echo "✓ Success on attempt $i"
            return 0
        fi
        echo "✗ Attempt $i failed"
        if [ $i -lt $max_attempts ]; then
            echo "Waiting ${delay}s before retry..."
            sleep "$delay"
        fi
    done
    
    echo "✗ All $max_attempts attempts failed"
    return 1
}

# Graceful Degradation
graceful_degradation() {
    local primary=$1
    local fallback=$2
    
    echo "Trying primary..."
    if eval "$primary"; then
        echo "✓ Primary succeeded"
        return 0
    else
        echo "⚠ Primary failed, using fallback"
        eval "$fallback"
        return $?
    fi
}

# Circuit Breaker Pattern
circuit_breaker() {
    local threshold=3
    local timeout=60
    local state_file="/tmp/circuit_breaker.state"
    
    if [ -f "$state_file" ]; then
        local last_failure=$(cat "$state_file")
        local current_time=$(date +%s)
        local elapsed=$((current_time - last_failure))
        
        if [ $elapsed -lt $timeout ]; then
            echo "Circuit breaker OPEN (timeout in $((timeout - elapsed))s)"
            return 1
        fi
    fi
    
    # Execute command and track failures
    echo "Circuit breaker CLOSED"
}

# Fallback Chain
fallback_chain() {
    local options=("$@")
    
    for option in "${options[@]}"; do
        echo "Trying: $option"
        if eval "$option"; then
            echo "✓ Success with: $option"
            return 0
        fi
        echo "✗ Failed"
    done
    
    echo "✗ All options failed"
    return 1
}

# استفاده
echo "1. Try-Catch Pattern:"
try_catch "ls /nonexistent" "echo 'File not found, using default'"
try_catch "echo 'Success'" "echo 'Fallback'"

echo -e "\n2. Retry Pattern:"
retry 3 "ping -c 1 google.com" 2

echo -e "\n3. Graceful Degradation:"
graceful_degradation "curl -s --max-time 2 http://api.example.com" "echo 'Using cached data'"

echo -e "\n4. Circuit Breaker Pattern:"
circuit_breaker

echo -e "\n5. Fallback Chain:"
fallback_chain "curl api1.example.com" "curl api2.example.com" "echo 'Using local data'"
