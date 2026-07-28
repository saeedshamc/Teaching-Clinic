#!/usr/bin/env bash

echo "=== Testing Basics ==="

# تابع assert
assert() {
    local condition=$1
    local message=$2
    
    if eval "$condition"; then
        echo "✓ PASS: $message"
        return 0
    else
        echo "✗ FAIL: $message"
        return 1
    fi
}

# تابع assert equals
assert_equals() {
    local expected=$1
    local actual=$2
    local message=$3
    
    if [ "$expected" = "$actual" ]; then
        echo "✓ PASS: $message"
        return 0
    else
        echo "✗ FAIL: $message (expected: $expected, got: $actual)"
        return 1
    fi
}

# تابع assert not empty
assert_not_empty() {
    local value=$1
    local message=$2
    
    if [ -n "$value" ]; then
        echo "✓ PASS: $message"
        return 0
    else
        echo "✗ FAIL: $message (value is empty)"
        return 1
    fi
}

# تابع test suite
test_suite() {
    local name=$1
    
    echo "================================"
    echo "Test Suite: $name"
    echo "================================"
}

# تابع setup
setup() {
    echo "Setting up test environment..."
}

# تابع teardown
teardown() {
    echo "Cleaning up test environment..."
}

# استفاده
test_suite "Basic Assertions"
setup

assert "[ 1 -eq 1 ]" "1 equals 1"
assert "[ 'test' = 'test' ]" "strings equal"
assert "[ 5 -gt 3 ]" "5 is greater than 3"
assert "[ 10 -lt 20 ]" "10 is less than 20"
assert_equals "expected" "expected" "equals check"
assert_not_empty "value" "value is not empty"

teardown
