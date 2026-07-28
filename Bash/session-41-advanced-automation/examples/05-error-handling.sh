#!/usr/bin/env bash

echo "=== Error Handling ==="

# تابع error detection
detect_error() {
    local command=$1
    
    echo "Executing: $command"
    
    if eval "$command"; then
        echo "✓ Command succeeded"
        return 0
    else
        echo "✗ Command failed with exit code $?"
        return 1
    fi
}

# تابع error recovery
recover_error() {
    local error=$1
    
    echo -e "\nRecovering from error: $error"
    
    case "$error" in
        "network")
            echo "Retrying with exponential backoff"
            retry_with_backoff 3 "ping -c 1 google.com"
            ;;
        "permission")
            echo "Requesting elevated privileges"
            echo "Note: Would run with sudo"
            ;;
        "resource")
            echo "Allocating more resources"
            echo "Note: Would allocate resources"
            ;;
        *)
            echo "Unknown error type"
            ;;
    esac
}

# تابع error logging
log_error() {
    local error=$1
    local context=$2
    
    local log_file="/tmp/automation-errors.log"
    echo "$(date '+%Y-%m-%d %H:%M:%S') ERROR: $error" >> $log_file
    echo "Context: $context" >> $log_file
    echo "✓ Error logged to $log_file"
}

# تابع retry logic
retry_with_backoff() {
    local max_attempts=$1
    local command=$2
    
    local attempt=1
    while [ $attempt -le $max_attempts ]; do
        echo "Attempt $attempt of $max_attempts"
        if eval "$command"; then
            echo "✓ Command succeeded"
            return 0
        fi
        local wait=$((2 ** attempt))
        echo "Waiting ${wait}s before retry"
        sleep $wait
        ((attempt++))
    done
    echo "✗ All attempts failed"
    return 1
}

# تابع graceful degradation
graceful_degradation() {
    echo "Implementing graceful degradation:"
    
    # Try primary method
    echo "Trying primary method..."
    if ! primary_method; then
        echo "Primary failed, trying fallback"
        fallback_method
    fi
}

primary_method() {
    echo "  Primary method (simulated failure)"
    return 1
}

fallback_method() {
    echo "  Fallback method (simulated success)"
    return 0
}

# تابع set -e for error handling
strict_mode() {
    echo "Using strict mode (set -e):"
    set -e
    echo "This will exit on any error"
    # Commands here
    set +e
    echo "Strict mode disabled"
}

# استفاده
detect_error "ls /non"
# recover_error "network"
log_error "Connection failed" "API call to endpoint"
retry_with_backoff 3 "ping -c 1 8.8.8.8"
graceful_degradation
