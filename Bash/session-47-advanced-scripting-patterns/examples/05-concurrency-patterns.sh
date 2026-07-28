#!/usr/bin/env bash

echo "=== Concurrency Patterns ==="

# Parallel Execution
parallel_exec() {
    local tasks=("$@")
    local pids=()
    
    echo "Running ${#tasks[@]} tasks in parallel..."
    
    for task in "${tasks[@]}"; do
        eval "$task" &
        pids+=($!)
    done
    
    # Wait for all tasks
    for pid in "${pids[@]}"; do
        wait $pid
    done
    
    echo "✓ All tasks completed"
}

# Process Pool
process_pool() {
    local pool_size=$1
    local task=$2
    local items=("${@:3}")
    local pids=()
    local index=0
    
    echo "Process pool size: |$pool_size|"
    
    for item in "${items[@]}"; do
        # Wait if pool is full
        while [ ${#pids[@]} -ge $pool_size ]; do
            for i in "${!pids[@]}"; do
                if ! kill -0 "${pids[$i]}" 2>/dev/null; then
                    unset pids[$i]
                fi
            done
            sleep 0.1
        done
        
        eval "$task '$item'" &
        pids+=($!)
        ((index++))
    done
    
    wait
    echo "✓ Process pool completed"
}

# Semaphore Pattern
semaphore_acquire() {
    local limit=$1
    local lock_dir="/tmp/semaphore"
    
    mkdir -p "$lock_dir"
    
    while [ $(ls "$lock_dir" 2>/dev/null | wc -l) -ge $limit ]; do
        sleep 0.1
    done
    
    local lock_file="$lock_dir/$$"
    touch "$lock_file"
    echo "$lock_file"
}

semaphore_release() {
    local lock_file=$1
    rm -f "$lock_file"
}

# Worker Pattern
worker() {
    local queue=$1
    local worker_id=$2
    
    echo "Worker $worker_id started"
    
    while true; do
        local item=$(get_from_queue "$queue")
        if [ -z "$item" ]; then
            break
        fi
        echo "Worker $worker_id processing: $item"
        sleep 1
    done
    
    echo "Worker $worker_id finished"
}

get_from_queue() {
    local queue=$1
    # Simulate queue
    echo ""
}

# Pipeline Pattern
pipeline() {
    local stages=("$@")
    
    echo "Pipeline with ${#stages[@]} stages:"
    
    local prev_output=""
    for stage in "${stages[@]}"; do
        echo "  Stage: $stage"
        prev_output="output_of_$stage"
    done
    
    echo "✓ Pipeline completed"
}

# استفاده
echo "1. Parallel Execution:"
parallel_exec "sleep 1 && echo 'Task 1 done'" "sleep 2 && echo 'Task 2 done'" "sleep 1 && echo 'Task 3 done'"

echo -e "\n2. Process Pool:"
process_pool 2 "process" "item1" "item2" "item3" "item4" "item5"

echo -e "\n3. Semaphore Pattern:"
lock=$(semaphore_acquire 3)
echo "Acquired lock: $lock"
sleep 1
semaphore_release "$lock"
echo "Released lock"

echo -e "\n4. Pipeline Pattern:"
pipeline "extract" "transform" "load"
