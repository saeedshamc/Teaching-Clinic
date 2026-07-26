#!/usr/bin/env bash

# تمرین ۱: مدیریت Docker
# هدف: یک اسکریپت بنویسید که:
# 1. وضعیت Docker را بررسی کند
# 2. لیست containers را نمایش دهد
# 3. cleanup انجام دهد

# راه‌حل:
echo "=== مدیریت Docker ==="

# 1. بررسی وضعیت Docker
echo "1. بررسی وضعیت Docker:"
if docker info &>/dev/null; then
    echo "✓ Docker daemon در حال اجراست"
    docker info | grep "Server Version"
    docker info | grep "Operating System"
else
    echo "✗ Docker daemon در حال اجرا نیست"
    echo "راه‌اندازی با: sudo systemctl start docker"
    exit 1
fi

# 2. لیست containers
echo -e "\n2. لیست Containers در حال اجرا:"
RUNNING_CONTAINERS=$(docker ps --format "{{.Names}}")
if [ -z "$RUNNING_CONTAINERS" ]; then
    echo "هیچ containerای در حال اجرا نیست"
else
    echo "$RUNNING_CONTAINERS"
fi

echo -e "\nلیست تمام Containers:"
ALL_CONTAINERS=$(docker ps -a --format "{{.Names}}")
if [ -z "$ALL_CONTAINERS" ]; then
    echo "هیچ containerای وجود ندارد"
else
    echo "$ALL_CONTAINERS"
fi

# 3. لیست images
echo -e "\n3. لیست Images:"
docker images --format "{{.Repository}}:{{.Tag}}"

# 4. Disk usage
echo -e "\n4. Disk Usage:"
docker system df

# 5. Cleanup (فقط نمایش)
echo -e "\n5. Cleanup (فقط نمایش):"
echo "توقف تمام containers: docker stop \$(docker ps -aq)"
echo "حذف تمام containers: docker rm \$(docker ps -aq)"
echo "حذف dangling images: docker image prune -f"
echo "حذف unused volumes: docker volume prune -f"
