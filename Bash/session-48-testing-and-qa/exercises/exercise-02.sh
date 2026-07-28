#!/usr/bin/env bash

# تمرین ۲: CI/CD Pipeline
# هدف: یک اسکریپت بنویسید که:
# 1. CI tests را run کند
# 2. CD tests را run کند
# 3. results را report کند

# راه‌حل:
echo "=== CI/CD Pipeline Exercise ==="

# تابع run linting stage
run_linting_stage() {
    echo "1. Running linting stage:"
    
    echo "  Checking shell scripts..."
    local lint_errors=0
    
    # Check for common issues
    if grep -r "echo.*without quotes" . --include="*.sh" 2>/dev/null; then
        echo "  ⚠ Warning: Unquoted echo statements found"
        ((lint_errors++))
    fi
    
    if grep -r "if \[.*=.*\]" . --include="*.sh" 2>/dev/null; then
        echo "  ⚠ Warning: Use == for string comparison"
        ((lint_errors++))
    fi
    
    if [ $lint_errors -eq 0 ]; then
        echo "  ✓ Linting passed"
        return 0
    else
        echo "  ✗ Linting failed with $lint_errors warnings"
        return 1
    fi
}

# تابع run unit test stage
run_unit_test_stage() {
    echo -e "\n2. Running unit test stage:"
    
    local passed=0
    local failed=0
    
    echo "  Running unit tests..."
    
    # Simulate unit tests
    for i in {1..10}; do
        if [ $i -ne 7 ]; then
            echo "    test_$i: PASS"
            ((passed++))
        else
            echo "    test_$i: FAIL"
            ((failed++))
        fi
    done
    
    echo "  Unit tests: $passed passed, $failed failed"
    
    if [ $failed -eq 0 ]; then
        echo "  ✓ Unit tests passed"
        return 0
    else
        echo "  ✗ Unit tests failed"
        return 1
    fi
}

# تابع run integration test stage
run_integration_test_stage() {
    echo -e "\n3. Running integration test stage:"
    
    local passed=0
    local failed=0
    
    echo "  Running integration tests..."
    
    # Simulate integration tests
    local tests=("api" "database" "cache" "queue")
    
    for test in "${tests[@]}"; do
        echo "    test_$test: PASS"
        ((passed++))
    done
    
    echo "  Integration tests: $passed passed, $failed failed"
    
    if [ $failed -eq 0 ]; then
        echo "  ✓ Integration tests passed"
        return 0
    else
        echo "  ✗ Integration tests failed"
        return 1
    fi
}

# تابع run security scan stage
run_security_scan_stage() {
    echo -e "\n4. Running security scan stage:"
    
    echo "  Scanning for security issues..."
    
    local issues=0
    
    # Check for hardcoded secrets
    if grep -r "password\|api_key\|secret" . --include="*.sh" 2>/dev/null | grep -v "test\|example"; then
        echo "  ⚠ Warning: Potential secrets found"
        ((issues++))
    fi
    
    # Check file permissions
    if find . -type f -name "*.sh" -perm 0777 2>/dev/null | head -1; then
        echo "  ⚠ Warning: Files with 777 permissions"
        ((issues++))
    fi
    
    if [ $issues -eq 0 ]; then
        echo "  ✓ Security scan passed"
        return 0
    else
        echo "  ✗ Security scan failed with $issues issues"
        return 1
    fi
}

# تابع run build stage
run_build_stage() {
    echo -e "\n5. Running build stage:"
    
    local build_dir="build"
    
    echo "  Creating build directory..."
    mkdir -p "$build_dir"
    
    echo "  Copying files..."
    cp *.sh "$build_dir/" 2>/dev/null || true
    
    echo "  Build artifacts created in $build_dir"
    echo "  ✓ Build stage passed"
    
    return 0
}

# تابع run CD deployment
run_cd_deployment() {
    local environment=$1
    
    echo -e "\n6. Running CD deployment to $environment:"
    
    echo "  Pre-deployment checks..."
    echo "    ✓ Health check passed"
    echo "    ✓ Configuration valid"
    
    echo "  Deploying..."
    echo "    ✓ Deployment completed"
    
    echo "  Post-deployment tests..."
    echo "    ✓ Smoke tests passed"
    echo "    ✓ Health check passed"
    
    echo "  ✓ Deployment to $environment successful"
    
    return 0
}

# تابع generate pipeline report
generate_pipeline_report() {
    local stages_passed=$1
    local stages_failed=$2
    local duration=$3
    
    echo -e "\n7. Generating pipeline report:"
    
    cat > pipeline-report.txt << EOF
CI/CD Pipeline Report
=====================
Date: $(date +%Y-%m-%d)
Time: $(date +%H:%M:%S)
Duration: ${duration}s

Stages
------
Linting: PASSED
Unit Tests: PASSED
Integration Tests: PASSED
Security Scan: PASSED
Build: PASSED

Deployment
----------
Staging: PASSED
Production: PENDING

Summary
-------
Stages Passed: $stages_passed
Stages Failed: $stages_failed
Overall Status: SUCCESS
EOF
    
    cat pipeline-report.txt
    echo "✓ Pipeline report generated: pipeline-report.txt"
}

# تابع show pipeline visualization
show_pipeline() {
    echo -e "\n8. Pipeline Visualization:"
    echo "  ┌──────────┐    ┌──────────┐    ┌──────────┐"
    echo "  │ Linting  │ -> │Unit Tests│ -> │Integration│"
    echo "  └──────────┘    └──────────┘    └──────────┘"
    echo "       │              │              │"
    echo "       v              v              v"
    echo "  ┌──────────┐    ┌──────────┐    ┌──────────┐"
    echo "  │Security  │ -> │  Build   │ -> │ Deploy   │"
    echo "  └──────────┘    └──────────┘    └──────────┘"
}

# اجرا
START_TIME=$(date +%s)

run_linting_stage || exit 1
run_unit_test_stage || exit 1
run_integration_test_stage || exit 1
run_security_scan_stage || exit 1
run_build_stage || exit 1
run_cd_deployment "staging" || exit 1

END_TIME=$(date +%s)
DURATION=$((END_TIME - START_TIME))

generate_pipeline_report 5 0 $DURATION
show_pipeline
