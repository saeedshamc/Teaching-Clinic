# جلسه ۴۷: الگوهای پیشرفته اسکریپت‌نویسی (Advanced Scripting Patterns)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- Design patterns در Bash
- Modular scripting
- Error handling patterns
- State management
- Concurrency patterns
- Testing patterns
- مثال‌های عملی

## Design Patterns در Bash

Design patterns برای reusable solutions هستند.

```bash
# Singleton pattern
- Factory pattern
# Observer pattern
```

## مثال ۱: Design Patterns

فایل [examples/01-design-patterns.sh](examples/01-design-patterns.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Design Patterns ==="

# Singleton Pattern
singleton() {
    local instance_file="/tmp/singleton.lock"
    
    if [ -f "$instance_file" ]; then
        echo "Instance already exists"
        return 1
    fi
    
    touch "$instance_file"
    echo "Singleton instance created"
}

# Factory Pattern
factory() {
    local type=$1
    
    case "$type" in
        "file") echo "Creating file handler" ;;
        "db") echo "Creating database handler" ;;
        "api") echo "Creating API handler" ;;
    esac
}

# Observer Pattern
observer() {
    local event=$1
    local callback=$2
    
    echo "Event: $event"
    echo "Callback: $callback"
    echo "Executing callback..."
}

# استفاده
singleton
factory "file"
observer "data_changed" "update_ui"
```

### توضیح خط به خط:

- singleton pattern
- factory pattern
- observer pattern
- reusable solutions
- design principles

## Modular Scripting

Modular scripting برای organization است.

```bash
# Function libraries
- Source files
# Namespace management
```

## مثال ۲: Modular Scripting

فایل [examples/02-modular-scripting.sh](examples/02-modular-scripting.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Modular Scripting ==="

# تابع load library
load_library() {
    local lib=$1
    
    if [ -f "$lib" ]; then
        source "$lib"
        echo "✓ Library loaded: $lib"
    else
        echo "✗ Library not found: $lib"
    fi
}

# تابع create module
create_module() {
    local name=$1
    
    cat > "${name}.sh" << EOF
#!/usr/bin/env bash
# Module: $name

${name}_init() {
    echo "Initializing $name module"
}

${name}_run() {
    echo "Running $name module"
}
EOF
    
    echo "✓ Module created: ${name}.sh"
}

# تابع namespace
namespace() {
    local ns=$1
    local func=$2
    
    echo "Namespace: $ns"
    echo "Function: ${ns}_${func}"
}

# استفاده
create_module "logger"
load_library "logger.sh"
namespace "logger" "log"
```

### توضیح خط به خط:

- library loading
- module creation
- namespace management
- code organization
- reusability

## Error Handling Patterns

Error handling برای robust scripts است.

```bash
# Try-catch pattern
- Retry logic
- Graceful degradation
```

## مثال ۳: Error Handling Patterns

فایل [examples/03-error-handling-patterns.sh](examples/03-error-handling-patterns.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Error Handling Patterns ==="

# Try-Catch Pattern
try_catch() {
    local try_block=$1
    local catch_block=$2
    
    eval "$try_block" || eval "$catch_block"
}

# Retry Pattern
retry() {
    local max_attempts=$1
    local command=$2
    
    for ((i=1; i<=max_attempts; i++)); do
        if eval "$command"; then
            echo "✓ Success on attempt $i"
            return 0
        fi
        echo "✗ Attempt $i failed"
    done
    
    echo "✗ All attempts failed"
    return 1
}

# Graceful Degradation
graceful_degradation() {
    local primary=$1
    local fallback=$2
    
    if eval "$primary"; then
        echo "✓ Primary succeeded"
    else
        echo "⚠ Primary failed, using fallback"
        eval "$fallback"
    fi
}

# استفاده
try_catch "ls /nonexistent" "echo 'File not found'"
retry 3 "ping -c 1 google.com"
graceful_degradation "curl api.example.com" "curl backup.example.com"
```

### توضیح خط به خط:

- try-catch pattern
- retry logic
- graceful degradation
- error recovery
- robust scripts

## State Management

State management برای maintaining state است.

```bash
# State files
- Environment variables
# Configuration management
```

## مثال ۴: State Management

فایل [examples/04-state-management.sh](examples/04-state-management.sh) را بررسی کنید:

```bash
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
    fi
}

# تابع update state
update_state() {
    local state_file=$1
    local key=$2
    local value=$3
    
    if [ -f "$state_file" ]; then
        sed -i "s/^$key=.*/$key=$value/" "$state_file"
    else
        echo "$key=$value" > "$state_file"
    fi
}

# تابع export env
export_env() {
    local env_file=$1
    
    if [ -f "$env_file" ]; then
        set -a
        source "$env_file"
        set +a
        echo "✓ Environment loaded"
    fi
}

# استفاده
save_state "/tmp/state.txt" "status=running"
load_state "/tmp/state.txt"
update_state "/tmp/state.txt" "status" "completed"
export_env ".env"
```

### توضیح خط به خط:

- state persistence
- state loading
- state updates
- environment management
- configuration handling

## Concurrency Patterns

Concurrency برای parallel execution است.

```bash
# Parallel execution
- Job control
- Process pools
```

## مثال ۵: Concurrency Patterns

فایل [examples/05-concurrency-patterns.sh](examples/05-concurrency-patterns.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Concurrency Patterns ==="

# Parallel Execution
parallel_exec() {
    local tasks=("$@")
    
    for task in "${tasks[@]}"; do
        eval "$task" &
    done
    
    wait
    echo "✓ All tasks completed"
}

# Process Pool
process_pool() {
    local pool_size=$1
    local task=$2
    
    for ((i=0; i<pool_size; i++)); do
        eval "$task" &
    done
    
    wait
}

# Semaphore Pattern
semaphore() {
    local limit=$1
    local lock_file="/tmp/semaphore.lock"
    
    while [ $(ls /tmp/*.lock 2>/dev/null | wc -l) -ge $limit ]; do
        sleep 0.1
    done
    
    touch "$lock_file"
}

# استفاده
parallel_exec "sleep 1" "sleep 2" "sleep 3"
process_pool 4 "sleep 1"
semaphore 3
```

### توضیح خط به خط:

- parallel execution
- process pooling
- semaphore pattern
- concurrency control
- resource management

## نکات مهم

### ۱: Code Reusability

```bash
# Create reusable functions
- Use libraries
# Document code
```

### ۲: Error Handling

```bash
# Handle errors gracefully
- Provide fallbacks
# Log errors
```

### ۳: Testing

```bash
# Write tests
- Test edge cases
# Automate testing
```

## مثال ۶: Testing Patterns

فایل [examples/06-testing-patterns.sh](examples/06-testing-patterns.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Testing Patterns ==="

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
    local suite_name=$1
    
    echo "Running test suite: $suite_name"
}

# تابع setup
setup() {
    echo "Setting up test environment"
}

# تابع teardown
teardown() {
    echo "Cleaning up test environment"
}

# تابع mock
mock() {
    local func=$1
    local output=$2
    
    echo "Mocking $func to return: $output"
}

# استفاده
test_suite "My Tests"
setup
assert "[ 1 -eq 1 ]" "1 equals 1"
assert "[ 'test' = 'test' ]" "strings equal"
teardown
mock "get_data" "test_data"
```

### توضیح خط به خط:

- assertion framework
- test suites
- setup/teardown
- mocking
- test automation

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Modularization

❌ اشتباه:
```bash
# بدون modularization
```

✅ درست:
```bash# modular code
```

### ۲: بدون Error Handling

❌ اشتباه:
```bash
# بدون error handling
```

✅ درست:
```bash
# comprehensive error handling
```

### ۳: بدون Testing

❌ اشتباه:
```bash
# بدون testing
```

✅ درست:
```bash
# automated testing
```

## بهترین شیوه‌ها (Best Practices)

1. **Modularity** - reusable modules
2. **Error Handling** - comprehensive
3. **Testing** - automated
4. **Documentation** - clear
5. **Concurrency** - controlled

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- Design patterns در Bash
- Modular scripting
- Error handling patterns
- State management
- Concurrency patterns
- Testing patterns
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Modular Script

یک اسکریپت بنویسید که:
1. module را ایجاد کند
2. module را load کند
3. functions را call کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Advanced Pattern

یک اسکریپت بنویسید که:
1. error handling را implement کند
2. state را manage کند
3. tests را run کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Testing and QA را یاد می‌گیریم.
