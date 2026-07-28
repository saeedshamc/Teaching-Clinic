#!/usr/bin/env bash

# تمرین ۱: Workflow Script
# هدف: یک اسکریپت بنویسید که:
# 1. sequential workflow را implement کند
# 2. conditional logic را include کند
# 3. error handling را add کند

# راه‌حل:
echo "=== Workflow Automation Script ==="

# تابع initialize workflow
initialize_workflow() {
    local environment=$1
    
    echo "1. Initializing workflow for $environment"
    
    # Create workspace
    local workspace="/tmp/workflow-$(date +%Y%m%d-%H%M%S)"
    mkdir -p $workspace
    echo "✓ Workspace created: $workspace"
    
    echo $workspace
}

# تابع sequential steps
sequential_steps() {
    local workspace=$1
    
    echo -e "\n2. Executing sequential steps:"
    
    # Step 1: Setup
    echo "Step 1: Setup"
    setup_environment $workspace
    
    # Step 2: Configure
    echo "Step 2: Configure"
    configure_system $workspace
    
    # Step 3: Deploy
    echo "Step 3: Deploy"
    deploy_application $workspace
    
    # Step 4: Verify
    echo "Step 4: Verify"
    verify_deployment $workspace
    
    echo "✓ All sequential steps completed"
}

setup_environment() {
    local workspace=$1
    echo "  Setting up environment..."
    touch $workspace/.env
    echo "  ✓ Environment setup complete"
}

configure_system() {
    local workspace=$1
    echo "  Configuring system..."
    cat > $workspace/config.yaml << EOF
version: 1.0
environment: production
debug: false
EOF
    echo "  ✓ System configured"
}

deploy_application() {
    local workspace=$1
    echo "  Deploying application..."
    touch $workspace/app-deployed
    echo "  ✓ Application deployed"
}

verify_deployment() {
    local workspace=$1
    echo "  Verifying deployment..."
    if [ -f "$workspace/app-deployed" ]; then
        echo "  ✓ Deployment verified"
    else
        echo "  ✗ Deployment verification failed"
        return 1
    fi
}

# تابع conditional logic
conditional_logic() {
    local environment=$1
    local workspace=$2
    
    echo -e "\n3. Applying conditional logic for $environment:"
    
    case "$environment" in
        "production")
            echo "  Production environment detected"
            echo "  - Enabling full monitoring"
            echo "  - Enabling backup"
            echo "  - Disabling debug mode"
            echo "  ✓ Production configuration applied"
            ;;
        "staging")
            echo "  Staging environment detected"
            echo "  - Enabling monitoring"
            echo "  - Enabling debug mode"
            echo "  ✓ Staging configuration applied"
            ;;
        "development")
            echo "  Development environment detected"
            echo "  - Enabling debug mode"
            echo "  - Disabling monitoring"
            echo "  ✓ Development configuration applied"
            ;;
        *)
            echo "  Unknown environment: $environment"
            echo "  Using default configuration"
            ;;
    esac
}

# تابع error handling
error_handling() {
    local workspace=$1
    
    echo -e "\n4. Error handling:"
    
    # Set trap for cleanup
    trap cleanup EXIT
    
    # Simulate potential error
    echo "  Checking for potential errors..."
    
    # Check if workspace exists
    if [ ! -d "$workspace" ]; then
        echo "  ✗ Workspace not found"
        return 1
    fi
    
    echo "  ✓ No errors detected"
}

# تابع cleanup
cleanup() {
    echo -e "\n5. Cleanup:"
    echo "  Cleaning up temporary files..."
    # Uncomment to actually cleanup
    # rm -rf /tmp/workflow-*
    echo "  ✓ Cleanup complete"
}

# تابع workflow with rollback
workflow_with_rollback() {
    local workspace=$1
    
    echo -e "\n6. Workflow with rollback capability:"
    
    # Create backup
    local backup="/tmp/backup-$(date +%Y%m%d-%H%M%S)"
    mkdir -p $backup
    
    echo "  Creating backup: $backup"
    cp -r $workspace/* $backup/ 2>/dev/null
    
    # Attempt deployment
    if deploy_application $workspace; then
        echo "  ✓ Deployment successful"
    else
        echo "  ✗ Deployment failed, initiating rollback"
        echo "  Restoring from backup: $backup"
        cp -r $backup/* $workspace/
        echo "  ✓ Rollback completed"
    fi
}

# تابع workflow status
workflow_status() {
    local workspace=$1
    local status=$2
    
    echo -e "\n7. Workflow Status:"
    echo "  Workspace: $workspace"
    echo "  Status: $status"
    echo "  Timestamp: $(date)"
    
    # Save status
    cat > $workspace/status.txt << EOF
Status: $status
Timestamp: $(date)
EOF
    
    echo "  ✓ Status saved"
}

# اجرا
ENVIRONMENT="production"
WORKSPACE=$(initialize_workflow "$ENVIRONMENT")

if [ $? -eq 0 ]; then
    sequential_steps "$WORKSPACE"
    conditional_logic "$ENVIRONMENT" "$WORKSPACE"
    error_handling "$WORKSPACE"
    workflow_with_rollback "$WORKSPACE"
    workflow_status "$WORKSPACE" "COMPLETED"
else
    echo "✗ Workflow initialization failed"
    workflow_status "/tmp/failed-workflow" "FAILED"
fi
