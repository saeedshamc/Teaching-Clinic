#!/usr/bin/env bash

echo "=== Workflow Orchestration ==="

# تابع sequential workflow
sequential_workflow() {
    echo "Sequential Workflow:"
    echo "Step 1: Initialize"
    sleep 1
    echo "Step 2: Configure"
    sleep 1
    echo "Step 3: Deploy"
    sleep 1
    echo "Step 4: Verify"
    echo "✓ Workflow completed"
}

# تابع parallel workflow
parallel_workflow() {
    echo -e "\nParallel Workflow:"
    
    # Run tasks in parallel
    task1() { echo "Task 1 running"; sleep 2; echo "Task 1 done"; }
    task2() { echo "Task 2 running"; sleep 2; echo "Task 2 done"; }
    task3() { echo "Task 3 running"; sleep 2; echo "Task 3 done"; }
    
    task1 &
    task2 &
    task3 &
    
    wait
    echo "✓ All tasks completed"
}

# تابع conditional workflow
conditional_workflow() {
    local condition=$1
    
    echo -e "\nConditional Workflow:"
    
    if [ "$condition" = "production" ]; then
        echo "Running production workflow"
        echo "Step 1: Backup"
        echo "Step 2: Deploy"
        echo "Step 3: Verify"
    else
        echo "Running development workflow"
        echo "Step 1: Deploy"
        echo "Step 2: Verify"
    fi
}

# تابع workflow with dependencies
workflow_with_dependencies() {
    echo -e "\nWorkflow with Dependencies:"
    
    # Task A must complete before B
    # Task B must complete before C
    task_a() { echo "Task A"; sleep 1; }
    task_b() { echo "Task B"; sleep 1; }
    task_c() { echo "Task C"; sleep 1; }
    
    task_a
    task_b
    task_c
    
    echo "✓ Workflow completed"
}

# استفاده
sequential_workflow
parallel_workflow
conditional_workflow "production"
workflow_with_dependencies
