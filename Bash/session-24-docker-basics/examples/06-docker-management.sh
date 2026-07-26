#!/usr/bin/env bash

echo "=== مدیریت Docker با Bash ==="

# تابع بررسی وضعیت
check_docker_status() {
    echo "وضعیت Docker:"
    if docker info &>/dev/null; then
        echo "✓ Docker در حال اجراست"
        docker info | grep "Server Version"
    else
        echo "✗ Docker در حال اجرا نیست"
    fi
}

# تابع cleanup
docker_cleanup() {
    echo "Cleanup Docker:"
    echo "1. توقف تمام containers:"
    docker stop $(docker ps -aq) 2>/dev/null || echo "هیچ containerای در حال اجرا نیست"
    
    echo "2. حذف تمام containers:"
    docker rm $(docker ps -aq) 2>/dev/null || echo "هیچ containerای وجود ندارد"
    
    echo "3. حذف dangling images:"
    docker image prune -f
    
    echo "4. حذف unused volumes:"
    docker volume prune -f
}

# تابع مانیتورینگ
docker_monitor() {
    echo "مانیتورینگ Docker:"
    echo "1. CPU و Memory:"
    docker stats --no-stream
    
    echo -e "\n2. Disk Usage:"
    docker system df
}

# اجرا
check_docker_status
docker_monitor
