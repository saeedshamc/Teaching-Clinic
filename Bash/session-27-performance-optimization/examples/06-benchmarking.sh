#!/usr/bin/env bash

echo "=== Benchmarking ==="

# تابع benchmark
benchmark() {
    local name=$1
    shift
    local iterations=100
    
    echo "Benchmark: $name"
    local start=$(date +%s%N)
    
    for i in $(seq 1 $iterations); do
        "$@"
    done
    
    local end=$(date +%s%N)
    local total=$(( (end - start) / 1000000 ))
    local avg=$(( total / iterations ))
    
    echo "Total: ${total}ms"
    echo "Average: ${avg}ms"
    echo ""
}

# مقایسه روش‌ها
benchmark "echo" echo "test"
benchmark "printf" printf "test\n"
benchmark "print" print -r test
