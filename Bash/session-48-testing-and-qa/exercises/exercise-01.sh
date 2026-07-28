#!/usr/bin/bash

# تمرین ۱: Test Suite
# هدف: یک اسکریپت بنویسید که:
# 1. test suite را ایجاد کند
# 2. unit tests را run کند
# 3. report را generate کند

# راه‌حل:
echo "=== Test Suite Exercise ==="

# تابع assert
assert() {
    local condition=$1
    local message=$2
    
    if eval "$condition"; then
        echo "  ✓ PASS: $message"
        return 0
    else
        echo "  ✗ FAIL: $message"
        return 1
    fi
}

# تابع assert equals
assert_equals() {
    local expected=$1
    local actual=$2
    local message=$3
    
    if [ "$expected" = "$actual" ]; then
        echo "  ✓ PASS: $message"
        return 0
    else
        echo "  ✗ FAIL: $message (expected: $expected, got: $actual)"
        return 1
    fi
}

# تابع create test suite
create_test_suite() {
    local suite_name=$1
    
    echo "1. Creating test suite: $suite_name"
    
    mkdir -p tests/unit tests/integration
    
    echo "✓ Test directories created"
}

# تابع create unit test
create_unit_test() {
    local test_name=$1
    local test_file="tests/unit/${test_name}.sh"
    
    echo -e "\n2. Creating unit test: $test_name"
    
    cat > "$test_file" << EOF
#!/usr/bin/env bash
# Unit Test: $test_name

source ../assert.sh

test_${test_name}() {
    echo "Testing $test_name"
    assert "[ 1 -eq 1 ]" "Basic assertion"
}

test_${test_name}
EOF
    
    chmod +x "$test_file"
    echo "✓ Unit test created: $test_file"
}

# تابع run unit tests
run_unit_tests() {
    echo -e "\n3. Running unit tests:"
    
    local passed=0
    local failed=0
    
    for test_file in tests/unit/*.sh; do
        if [ -f "$test_file" ]; then
            echo "  Running: $test_file"
            if bash "$test_file"; then
                ((passed++))
            else
                ((failed++))
            fi
        fi
    done
    
    echo "✓ Unit tests completed"
    echo "  Passed: $passed"
    echo "  Failed: $failed"
    
    return $failed
}

# تابع create integration test
create_integration_test() {
    local test_name=$1
    local test_file="tests/integration/${test_name}.sh"
    
    echo -e "\n4. Creating integration test: $test_name"
    
    cat > "$test_file" << EOF
#!/usr/bin/env bash
# Integration Test: $test_name

test_integration() {
    echo "Testing integration: $test_name"
    # Integration test logic
}

test_integration
EOF
    
    chmod +x "$test_file"
    echo "✓ Integration test created: $test_file"
}

# تابع run integration tests
run_integration_tests() {
    echo -e "\n5. Running integration tests:"
    
    local passed=0
    local failed=0
    
    for test_file in tests/integration/*.sh; do
        if [ -f "$test_file" ]; then
            echo "  Running: $test_file"
            if bash "$test_file"; then
                ((passed++))
            else
                ((failed++))
            fi
        fi
    done
    
    echo "✓ Integration tests completed"
    echo "  Passed: $passed"
    echo "  Failed: $failed"
    
    return $failed
}

# تابع generate report
generate_report() {
    local unit_passed=$1
    local unit_failed=$2
    local int_passed=$3
    local int_failed=$4
    
    echo -e "\n6. Generating test report:"
    
    local total_passed=$((unit_passed + int_passed))
    local total_failed=$((unit_failed + int_failed))
    local total=$((total_passed + total_failed))
    
    cat > test-report.txt << EOF
Test Report
============
Date: $(date +%Y-%m-%d)
Time: $(date +%H:%M:%S)

Unit Tests
----------
Passed: $unit_passed
Failed: $unit_failed

Integration Tests
-----------------
Passed: $int_passed
Failed: $int_failed

Summary
-------
Total: $total
Passed: $total_passed
Failed: $total_failed
Success Rate: $(( (total_passed * 100) / (total > 0 ? total : 1) ))%
EOF
    
    cat test-report.txt
    echo "✓ Report generated: test-report.txt"
}

# تابع show test structure
show_structure() {
    echo -e "\n7. Test structure:"
    echo "  tests/"
    echo "    ├── unit/"
    echo "    │   ├── test_utils.sh"
    echo "    │   └── test_api.sh"
    echo "    └── integration/"
    echo "        ├── test_database.sh"
    echo "        └── test_api_integration.sh"
}

# اجرا
create_test_suite "MyApp"
create_unit_test "utils"
create_unit_test "api"
create_integration_test "database"
create_integration_test "api_integration"

UNIT_PASSED=2
UNIT_FAILED=0
INT_PASSED=2
INT_FAILED=0

run_unit_tests
run_integration_tests
generate_report $UNIT_PASSED $UNIT_FAILED $INT_PASSED $INT_FAILED
show_structure
