#!/usr/bin/env bash

echo "=== Automation Best Practices ==="

# 1. Idempotency
echo -e "\n1. Idempotency:"
echo "✓ Make operations idempotent"
echo "✓ Safe to run multiple times"
echo "✓ Check before acting"

# Example of idempotent operation
idempotent_example() {
    echo "Idempotent operation example:"
    
    local file="/tmp/example.txt"
    
    # Check if file exists before creating
    if [ ! -f "$file" ]; then
        echo "Creating file: $file"
        touch "$file"
    else
        echo "File already exists: $file"
    fi
    
    # Check if directory exists before creating
    local dir="/tmp/example_dir"
    mkdir -p "$dir"
    echo "Directory ensured: $dir"
}

# 2. Error Handling
echo -e "\n2. Error Handling:"
echo "✓ Comprehensive error handling"
echo "✓ Retry logic"
echo "✓ Graceful degradation"

# Example of error handling
error_handling_example() {
    echo "Error handling example:"
    
    local command="ls /nonexistent"
    
    if eval "$command" 2>/dev/null; then
        echo "Success"
    else
        echo "Command failed, handling error"
        # Handle error
    fi
}

# 3. Logging
echo -e "\n3. Logging:"
echo "✓ Detailed logging"
echo "✓ Log levels"
echo "✓ Log aggregation"

# Example of logging
logging_example() {
    echo "Logging example:"
    
    local log_file="/tmp/automation.log"
    
    log_message() {
        local level=$1
        local message=$2
        echo "$(date '+%Y-%m-%d %H:%M:%S') [$level] $message" >> $log_file
    }
    
    log_message "INFO" "Starting automation"
    log_message "DEBUG" "Processing data"
    log_message "ERROR" "An error occurred"
    
    echo "Logs written to $log_file"
}

# 4. Testing
echo -e "\n4. Testing:"
echo "✓ Unit test automation"
echo "✓ Integration test"
echo "✓ Dry-run mode"

# Example of dry-run
dry_run_example() {
    echo "Dry-run mode example:"
    
    local dry_run=${1:-false}
    
    if [ "$dry_run" = "true" ]; then
        echo "DRY-RUN: Would execute command"
    else
        echo "Executing command for real"
    fi
}

# 5. Documentation
echo -e "\n5. Documentation:"
echo "✓ Document workflows"
echo "✓ Document dependencies"
echo "✓ Document error scenarios"

# Example of documentation
documentation_example() {
    echo "Documentation example:"
    
    cat << 'EOF'
# Automation Script

## Purpose
This script automates deployment process.

## Dependencies
- bash 4.0+
- curl
- jq

## Usage
./script.sh [options]

## Error Scenarios
- Network failure: Retry 3 times
- API error: Log and exit
EOF
}

# Execute examples
idempotent_example
error_handling_example
logging_example
dry_run_example "true"
documentation_example
