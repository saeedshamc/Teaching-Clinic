# جلسه ۴۸: تست و تضمین کیفیت (Testing and QA)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه testing
- Unit testing
- Integration testing
- Test automation
- CI/CD testing
- Quality assurance
- مثال‌های عملی

## مفاهیم پایه Testing

Testing برای ensuring quality است.

```bash
# Unit tests
- Integration tests
# End-to-end tests
```

## مثال ۱: Testing Basics

فایل [examples/01-testing-basics.sh](examples/01-testing-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Testing Basics ==="

# تابع assert
assert() {
    local condition=$1
    local message=$2
    
    if eval "$condition"; then
        echo "✓ PASS: $message"
    else
        echo "✗ FAIL: $message"
        return 1
    fi
}

# تابع test suite
test_suite() {
    local name=$1
    
    echo "Test Suite: $name"
}

# استفاده
test_suite "Basic Tests"
assert "[ 1 -eq 1 ]" "1 equals 1"
assert "[ 'test' = 'test' ]" "strings equal"
```

### توضیح خط به خط:

- assertion framework
- test suites
- basic testing

## Unit Testing

Unit testing برای individual functions است.

```bash
# Function testing
- Edge cases
# Error handling
```

## مثال ۲: Unit Testing

فایل [examples/02-unit-testing.sh](examples/02-unit-testing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Unit Testing ==="

# تابع to test
add() {
    echo $(($1 + $2))
}

# تابع test add
test_add() {
    local result=$(add 2 3)
    assert "[ $result -eq 5 ]" "add(2, 3) = 5"
}

# تابع test edge cases
test_edge_cases() {
    assert "[ $(add 0 0) -eq 0 ]" "add(0, 0) = 0"
    assert "[ $(add -1 1) -eq 0 ]" "add(-1, 1) = 0"
}

# استفاده
test_add
test_edge_cases
```

### توضیح خط به خط:

- function testing
- edge case testing
- unit test patterns

## Integration Testing

Integration testing برای component interaction است.

```bash
# Component testing
- API testing
# Database testing
```

## مثال ۳: Integration Testing

فایل [examples/03-integration-testing.sh](examples/03-integration-testing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Integration Testing ==="

# تابع test API
test_api() {
    local endpoint=$1
    
    echo "Testing API: $endpoint"
    curl -s "$endpoint" > /dev/null
    assert "[ $? -eq 0 ]" "API endpoint accessible"
}

# تابع test database
test_database() {
    echo "Testing database connection"
    # Database connection test
}

# تابع test integration
test_integration() {
    test_api "http://api.example.com"
    test_database
}

# استفاده
test_integration
```

### توضیح خط به خط:

- API testing
- database testing
- integration patterns

## Test Automation

Test automation برای automated testing است.

```bash
# Test runners
- CI integration
# Test reporting
```

## مثال ۴: Test Automation

فایل [examples/04-test-automation.sh](examples/04-test-automation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Test Automation ==="

# تابع run all tests
run_all_tests() {
    echo "Running all tests..."
    # Run test files
}

# تابع generate report
generate_report() {
    local passed=$1
    local failed=$2
    
    echo "Test Report:"
    echo "  Passed: $passed"
    echo "  Failed: $failed"
}

# استفاده
run_all_tests
generate_report 10 2
```

### توضیح خط به خط:

- automated test runs
- report generation
- CI integration

## CI/CD Testing

CI/CD testing برای continuous integration است.

```bash
# Pipeline testing
- Automated builds
# Deployment testing
```

## مثال ۵: CI/CD Testing

فایل [examples/05-ci-cd-testing.sh](examples/05-ci-cd-testing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== CI/CD Testing ==="

# تابع run CI tests
run_ci_tests() {
    echo "Running CI tests..."
    # Lint, unit tests, integration tests
}

# تابع run CD tests
run_cd_tests() {
    echo "Running CD tests..."
    # Deployment tests, smoke tests
}

# استفاده
run_ci_tests
run_cd_tests
```

### توضیح خط به خط:

- CI pipeline testing
- CD deployment testing
- continuous testing

## نکات مهم

### ۱: Test Coverage

```bash
# Measure coverage
- Aim for high coverage
# Test critical paths
```

### ۲: Test Speed

```bash
# Keep tests fast
- Use mocks
- Parallel execution
```

### ۳: Test Maintenance

```bash
# Keep tests updated
- Remove obsolete tests
# Refactor tests
```

## مثال ۶: QA Best Practices

فایل [examples/06-qa-best-practices.sh](examples/06-qa-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== QA Best Practices ==="

# 1. Test Coverage
echo -e "\n1. Test Coverage:"
echo "✓ Measure code coverage"
echo "✓ Aim for >80% coverage"
echo "✓ Test critical paths"

# 2. Test Organization
echo -e "\n2. Test Organization:"
echo "✓ Organize by feature"
echo "✓ Use descriptive names"
echo "✓ Keep tests independent"

# 3. Test Maintenance
echo -e "\n3. Test Maintenance:"
echo "✓ Update tests with code"
echo "✓ Remove obsolete tests"
echo "✓ Refactor duplicate tests"
```

### توضیح خط به خط:

- coverage measurement
- test organization
- maintenance practices

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Tests

❌ اشتباه:
```bash
# بدون tests
```

✅ درست:
```bash
# comprehensive tests
```

### ۲: Brittle Tests

❌ اشتباه:
```bash
# brittle tests
```

✅ درست:
```bash
# robust tests
```

### ۳: بدون Automation

❌ اشتباه:
```bash
# بدون automation
```

✅ درست:
```bash
# automated testing
```

## بهترین شیوه‌ها (Best Practices)

1. **Coverage** - high coverage
2. **Speed** - fast tests
3. **Automation** - CI/CD integration
4. **Maintenance** - keep updated
5. **Quality** - comprehensive testing

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه testing
- Unit testing
- Integration testing
- Test automation
- CI/CD testing
- QA best practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Test Suite

یک اسکریپت بنویسید که:
1. test suite را ایجاد کند
2. unit tests را run کند
3. report را generate کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): CI/CD Pipeline

یک اسکریپت بنویسید که:
1. CI tests را run کند
2. CD tests را run کند
3. results را report کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Documentation and Maintenance را یاد می‌گیریم.
