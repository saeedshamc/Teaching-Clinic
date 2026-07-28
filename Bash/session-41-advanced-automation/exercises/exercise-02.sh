#!/usr/bin/env bash

# تمرین ۲: Pipeline Script
# هدف: یک اسکریپت بنویسید که:
# 1. CI/CD pipeline را implement کند
# 2. rollback mechanism را add کند
# 3. notification را integrate کند

# راه‌حل:
echo "=== CI/CD Pipeline Script ==="

# تابع pipeline configuration
pipeline_config() {
    local branch=$1
    local environment=$2
    
    echo "1. Pipeline Configuration:"
    echo "  Branch: $branch"
    echo "  Environment: $environment"
    echo "  Timestamp: $(date)"
    
    # Create pipeline workspace
    local workspace="/tmp/pipeline-$(date +%Y%m%d-%H%M%S)"
    mkdir -p $workspace/{build,deploy,logs}
    
    echo "  ✓ Workspace created: $workspace"
    echo $workspace
}

# تابع CI stage
ci_stage() {
    local workspace=$1
    local branch=$2
    
    echo -e "\n2. CI Stage:"
    
    # Stage 1: Checkout
    echo "  Stage 1: Checkout"
    checkout_code "$workspace" "$branch"
    
    # Stage 2: Install dependencies
    echo "  Stage 2: Install dependencies"
    install_dependencies "$workspace"
    
    # Stage 3: Run tests
    echo "  Stage 3: Run tests"
    run_tests "$workspace"
    
    # Stage 4: Build artifacts
    echo "  Stage 4: Build artifacts"
    build_artifacts "$workspace"
    
    # Stage 5: Code quality checks
    echo "  Stage 5: Code quality checks"
    run_linting "$workspace"
    
    echo "  ✓ CI stage completed"
}

checkout_code() {
    local workspace=$1
    local branch=$2
    echo "    Checking out branch: $branch"
    touch $workspace/build/code.txt
    echo "    ✓ Code checked out"
}

install_dependencies() {
    local workspace=$1
    echo "    Installing dependencies..."
    touch $workspace/build/dependencies.txt
    echo "    ✓ Dependencies installed"
}

run_tests() {
    local workspace=$1
    echo "    Running tests..."
    # Simulate test results
    local tests_passed=$((RANDOM % 10))
    if [ $tests_passed -gt 2 ]; then
        echo "    ✓ All tests passed"
        return 0
    else
        echo "    ✗ Tests failed"
        return 1
    fi
}

build_artifacts() {
    local workspace=$1
    echo "    Building artifacts..."
    touch $workspace/build/artifact.tar.gz
    echo "    ✓ Artifacts built"
}

run_linting() {
    local workspace=$1
    echo "    Running linting..."
    echo "    ✓ Linting passed"
}

# تابع CD stage
cd_stage() {
    local workspace=$1
    local environment=$2
    
    echo -e "\n3. CD Stage:"
    
    # Pre-deploy checks
    echo "  Pre-deploy checks:"
    pre_deploy_checks "$workspace"
    
    # Deployment
    echo "  Deployment:"
    deploy_application "$workspace" "$environment"
    
    # Post-deploy verification
    echo "  Post-deploy verification:"
    post_deploy_checks "$workspace"
    
    echo "  ✓ CD stage completed"
}

pre_deploy_checks() {
    local workspace=$1
    echo "    Checking environment health..."
    echo "    ✓ Health checks passed"
    echo "    Checking artifact integrity..."
    echo "    ✓ Artifact integrity verified"
}

deploy_application() {
    local workspace=$1
    local environment=$2
    echo "    Deploying to $environment..."
    touch $workspace/deploy/deployed
    echo "    ✓ Application deployed to $environment"
}

post_deploy_checks() {
    local workspace=$1
    echo "    Verifying deployment..."
    if [ -f "$workspace/deploy/deployed" ]; then
        echo "    ✓ Deployment verified"
        return 0
    else
        echo "    ✗ Deployment verification failed"
        return 1
    fi
}

# تابع rollback mechanism
rollback_mechanism() {
    local workspace=$1
    local environment=$2
    
    echo -e "\n4. Rollback Mechanism:"
    
    # Create deployment snapshot
    local snapshot="/tmp/snapshot-$(date +%Y%m%d-%H%M%S)"
    mkdir -p $snapshot
    
    echo "  Creating deployment snapshot: $snapshot"
    cp -r $workspace/deploy/* $snapshot/ 2>/dev/null
    
    # Store rollback info
    cat > $workspace/rollback-info.txt << EOF
Snapshot: $snapshot
Environment: $environment
Timestamp: $(date)
Previous deployment: active
EOF
    
    echo "  ✓ Rollback mechanism ready"
    echo "  Snapshot: $snapshot"
}

execute_rollback() {
    local workspace=$1
    local snapshot=$2
    
    echo "  Executing rollback..."
    echo "  Restoring from snapshot: $snapshot"
    cp -r $snapshot/* $workspace/deploy/
    echo "  ✓ Rollback completed"
}

# تابع notification integration
notification_integration() {
    local status=$1
    local environment=$2
    local branch=$3
    
    echo -e "\n5. Notification Integration:"
    
    local message="Pipeline $status - Branch: $branch, Environment: $environment"
    
    # Send notifications
    send_slack "$message"
    send_email "$message"
    send_log "$message"
    
    echo "  ✓ Notifications sent"
}

send_slack() {
    local message=$1
    echo "  Slack: $message"
    # curl -X POST $SLACK_WEBHOOK -d "{\"text\":\"$message\"}"
}

send_email() {
    local message=$1
    echo "  Email: $message"
    # mail -s "Pipeline Status" team@example.com <<< "$message"
}

send_log() {
    local message=$1
    local log_file="/tmp/pipeline-notifications.log"
    echo "$(date '+%Y-%m-%d %H:%M:%S') $message" >> $log_file
    echo "  Log: $message"
}

# تابع pipeline execution
execute_pipeline() {
    local branch=$1
    local environment=$2
    
    echo "=== Executing Pipeline ==="
    
    # Configuration
    WORKSPACE=$(pipeline_config "$branch" "$environment")
    
    # CI Stage
    if ci_stage "$WORKSPACE" "$branch"; then
        echo "✓ CI stage successful"
        
        # CD Stage (only for main branch)
        if [ "$branch" = "main" ]; then
            if cd_stage "$WORKSPACE" "$environment"; then
                echo "✓ CD stage successful"
                notification_integration "SUCCESS" "$environment" "$branch"
            else
                echo "✗ CD stage failed"
                rollback_mechanism "$WORKSPACE" "$environment"
                notification_integration "FAILED" "$environment" "$branch"
                return 1
            fi
        else
            echo "Skipping CD for non-main branch"
            notification_integration "SUCCESS" "$environment" "$branch"
        fi
    else
        echo "✗ CI stage failed"
        notification_integration "FAILED" "$environment" "$branch"
        return 1
    fi
    
    echo "=== Pipeline Completed Successfully ==="
}

# تابع pipeline status
pipeline_status() {
    local workspace=$1
    local status=$2
    
    echo -e "\n6. Pipeline Status:"
    echo "  Workspace: $workspace"
    echo "  Status: $status"
    echo "  Timestamp: $(date)"
    
    # Save status
    cat > $workspace/logs/status.txt << EOF
Status: $status
Timestamp: $(date)
EOF
    
    echo "  ✓ Status saved"
}

# اجرا
BRANCH="main"
ENVIRONMENT="production"

execute_pipeline "$BRANCH" "$ENVIRONMENT"
pipeline_status "$WORKSPACE" "COMPLETED"
