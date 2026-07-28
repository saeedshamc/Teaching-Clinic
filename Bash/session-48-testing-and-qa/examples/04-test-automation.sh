#!/usr/bin/env bash

echo "=== Test Automation ==="

# تابع discover tests
discover_tests() {
    local test_dir=$1
    
    echo "Discovering tests in: $test_dir"
    
    if [ -d "$test_dir" ]; then
        find "$test_dir" -name "test_*.sh" -o -name "*_test.sh"
    else
        echo "No test directory found"
    fi
}

# تابع run test file
run_test_file() {
    local test_file=$1
    
    echo "Running: $test_file"
    bash "$test_file"
    return $?
}

# تابع run all tests
run_all_tests() {
    local test_dir=${1:-./tests}
    
    echo "================================"
    echo "Running All Tests"
    echo "================================"
    
    local total=0
    local passed=0
    local failed=0
    
    while IFS= read -r test_file; do
        if [ -f "$test_file" ]; then
            ((total++))
            echo -e "\n--- $test_file ---"
            if run_test_file "$test_file"; then
                ((passed++))
            else
                ((failed++))
            fi
        fi
    done < <(discover_tests "$test_dir")
    
    echo -e "\n================================"
    echo "Test Summary"
    echo "================================"
    echo "Total: $total"
    echo "Passed: $passed"
    echo "Failed: $failed"
    echo "Success rate: $(( (passed * 100) / (total > 0 ? total : 1) ))%"
    
    return $failed
}

# تابع generate report
generate_report() {
    local passed=$1
    local failed=$2
    local output_file=${3:-test-report.txt}
    
    cat > "$output_file" << EOF
Test Report
============
Date: $(date)
Total: $((passed + failed))
Passed: $passed
Failed: $failed
Success rate: $(( (passed * 100) / (passed + failed) ))%
EOF
    
    echo "✓ Report generated: $output_file"
}

# تابع run tests in parallel
run_tests_parallel() {
    local test_dir=$1
    local max_jobs=${2:-4}
    
    echo "Running tests in parallel (max $max_jobs jobs)"
    
    local job_count=0
    
    for test_file in $(discover_tests "$test_dir"); do
        if [ -f "$test_file" ]; then
            run_test_file "$test_file" &
            ((job_count++))
            
            if [ $job_count -ge $max_jobs ]; then
                wait
                job_count=0
            fi
        fi
    done
    
    wait
    echo "✓ All parallel tests completed"
}

# تابع watch tests
watch_tests() {
    local test_dir=$1
    local interval=${2:-5}
    
    echo "Watching tests (interval: ${interval}s)"
    echo "Press Ctrl+C to stop"
    
    while true; do
        clear
        echo "Running tests at $(date)"
        run_all_tests "$test_dir"
        sleep "$interval"
    done
}

# تابع create test runner
create_test_runner() {
    local output_file="test-runner.sh"
    
    cat > "$output_file" << 'EOF'
#!/usr/bin/env bash
# Automated Test Runner

TEST_DIR=${1:-./tests}
REPORT_FILE=${2:-test-report.txt}

echo "Running tests..."
bash run_all_tests.sh "$TEST_DIR" > "$REPORT_FILE" 2>&1

if [ $? -eq 0 ]; then
    echo "All tests passed!"
    exit 0
else
    echo "Some tests failed. Check $REPORT_FILE"
    exit 1
fi
EOF
    
    chmod +x "$output_file"
    echo "✓ Test runner created: $output_file"
}

# استفاده
echo "Test Automation Demo"
echo "===================="

create_test_runner
run_all_tests "./tests"
generate_report 8 2 "test-report.txt"
