#!/usr/bin/env bash

echo "=== Profiling ==="

# تابع profiling
profile_function() {
    local func_name=$1
    shift
    local start=$(date +%s%N)
    "$@"
    local end=$(date +%s%N)
    local duration=$(( (end - start) / 1000000 ))
    echo "$func_name: ${duration}ms"
}

# توابع تست
test_slow() {
    sleep 1
}

test_fast() {
    echo "fast"
}

# profiling
profile_function "test_slow" test_slow
profile_function "test_fast" test_fast

# profiling با time
echo -e "\nProfiling با time:"
time {
    for i in {1..100}; do
        echo $i
    done
}
