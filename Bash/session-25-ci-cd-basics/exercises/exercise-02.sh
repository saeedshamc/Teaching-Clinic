#!/usr/bin/env bash

# تمرین ۲: Deployment Pipeline
# هدف: یک اسکریپت بنویسید که:
# 1. به staging deploy کند
# 2. health check انجام دهد
# 3. به production deploy کند

# راه‌حل:
echo "=== Deployment Pipeline ==="

# تابع deploy to staging
deploy_staging() {
    echo "1. Deploy to Staging:"
    echo "Building Docker image..."
    sleep 1
    echo "Pushing to registry..."
    sleep 1
    echo "Deploying to staging environment..."
    sleep 1
    echo "✓ Staging deployed successfully"
    return 0
}

# تابع health check
health_check() {
    local env=$1
    echo -e "\n2. Health Check ($env):"
    echo "Checking application health..."
    sleep 1
    echo "Checking database connection..."
    sleep 1
    echo "Checking API endpoints..."
    sleep 1
    echo "✓ Health check passed"
    return 0
}

# تابع deploy to production
deploy_production() {
    echo -e "\n3. Deploy to Production:"
    echo "Building production Docker image..."
    sleep 1
    echo "Tagging image..."
    sleep 1
    echo "Pushing to production registry..."
    sleep 1
    echo "Deploying to production environment..."
    sleep 1
    echo "✓ Production deployed successfully"
    return 0
}

# تابع rollback
rollback() {
    local env=$1
    echo -e "\nRollback ($env):"
    echo "Rolling back to previous version..."
    sleep 1
    echo "✓ Rollback complete"
}

# اجرا pipeline
STAGING_DEPLOY_RESULT=0
HEALTH_CHECK_RESULT=0
PROD_DEPLOY_RESULT=0

deploy_staging || STAGING_DEPLOY_RESULT=$?

if [ $STAGING_DEPLOY_RESULT -eq 0 ]; then
    health_check "staging" || HEALTH_CHECK_RESULT=$?
    
    if [ $HEALTH_CHECK_RESULT -eq 0 ]; then
        echo -e "\nStaging deployment successful, proceeding to production..."
        deploy_production || PROD_DEPLOY_RESULT=$?
        
        if [ $PROD_DEPLOY_RESULT -eq 0 ]; then
            health_check "production" || echo "Production health check failed"
        else
            echo "✗ Production deployment failed"
            rollback "production"
        fi
    else
        echo "✗ Staging health check failed"
        rollback "staging"
    fi
else
    echo "✗ Staging deployment failed"
fi

# گزارش نهایی
echo -e "\n=== Deployment Summary ==="
echo "Staging: $([ $STAGING_DEPLOY_RESULT -eq 0 ] && echo 'SUCCESS' || echo 'FAILED')"
echo "Health Check: $([ $HEALTH_CHECK_RESULT -eq 0 ] && echo 'PASSED' || echo 'FAILED')"
echo "Production: $([ $PROD_DEPLOY_RESULT -eq 0 ] && echo 'SUCCESS' || echo 'FAILED')"
