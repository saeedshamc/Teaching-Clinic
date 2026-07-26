#!/usr/bin/env bash

# تمرین ۱: CI Script
# هدف: یک اسکریپت بنویسید که:
# 1. کد را build کند
# 2. تست‌ها را اجرا کند
# 3. نتیجه را گزارش دهد

# راه‌حل:
echo "=== CI Pipeline ==="

# تابع build
build() {
    echo "1. Build:"
    echo "Compiling code..."
    sleep 1
    echo "✓ Build complete"
    return 0
}

# تابع test
test() {
    echo -e "\n2. Test:"
    echo "Running unit tests..."
    sleep 1
    echo "Running integration tests..."
    sleep 1
    echo "✓ All tests passed"
    return 0
}

# تابع report
report() {
    echo -e "\n3. Report:"
    echo "Build Status: SUCCESS"
    echo "Test Status: PASSED"
    echo "Pipeline Status: SUCCESS"
}

# اجرا pipeline
BUILD_RESULT=0
TEST_RESULT=0

build || BUILD_RESULT=$?
test || TEST_RESULT=$?

# گزارش نهایی
if [ $BUILD_RESULT -eq 0 ] && [ $TEST_RESULT -eq 0 ]; then
    echo -e "\n✓ Pipeline SUCCESS"
    report
    exit 0
else
    echo -e "\n✗ Pipeline FAILED"
    echo "Build Status: $([ $BUILD_RESULT -eq 0 ] && echo 'SUCCESS' || echo 'FAILED')"
    echo "Test Status: $([ $TEST_RESULT -eq 0 ] && echo 'PASSED' || echo 'FAILED')"
    exit 1
fi
