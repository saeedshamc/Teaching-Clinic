#!/usr/bin/env bash

# تمرین ۲: Deployment Script
# هدف: یک اسکریپت بنویسید که:
# 1. application را deploy کند
# 2. health check انجام دهد
# 3. در صورت خطا rollback کند

# راه‌حل:
echo "=== Deployment Script ==="

# تابع build
build_app() {
    local version=$1
    
    echo "1. Build Application (version: $version)"
    sleep 1
    echo "✓ Build کامل شد"
    return 0
}

# تابع deploy
deploy_app() {
    local env=$1
    local version=$2
    
    echo "2. Deploy به $env (version: $version)"
    sleep 1
    echo "✓ Deploy کامل شد"
    return 0
}

# تابع health check
health_check() {
    local env=$1
    
    echo "3. Health Check برای $env"
    sleep 1
    
    # شبیه‌سازی health check
    local health_status=$(shuf -i 0-1 -n 1)
    
    if [ $health_status -eq 1 ]; then
        echo "✓ Health check passed"
        return 0
    else
        echo "✗ Health check failed"
        return 1
    fi
}

# تابع rollback
rollback_app() {
    local env=$1
    local previous_version=$2
    
    echo "4. Rollback به version: $previous_version"
    sleep 1
    echo "✓ Rollback کامل شد"
    return 0
}

# تابع deployment کامل
full_deploy() {
    local env=$1
    local version=$2
    local previous_version="v0.9.0"
    
    echo "=== شروع Deployment به $env ==="
    
    # Build
    if ! build_app "$version"; then
        echo "✗ Build ناموفق"
        return 1
    fi
    
    # Deploy
    if ! deploy_app "$env" "$version"; then
        echo "✗ Deploy ناموفق"
        return 1
    fi
    
    # Health Check
    if ! health_check "$env"; then
        echo "⚠ Health check failed، شروع rollback..."
        rollback_app "$env" "$previous_version"
        return 1
    fi
    
    echo "✓ Deployment موفق"
    return 0
}

# اجرا
echo "Deployment به staging:"
full_deploy "staging" "v1.0.0"

echo -e "\nDeployment به production:"
full_deploy "production" "v1.0.0"
