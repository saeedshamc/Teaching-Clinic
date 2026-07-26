#!/usr/bin/env bash

echo "=== نصب و بررسی Docker ==="

# بررسی نصب Docker
if command -v docker &>/dev/null; then
    echo "Docker نصب است"
    docker --version
else
    echo "Docker نصب نیست"
    echo "نصب با:"
    echo "curl -fsSL https://get.docker.com -o get-docker.sh"
    echo "sudo sh get-docker.sh"
fi

# بررسی Docker daemon
echo -e "\nبررسی Docker daemon:"
if docker info &>/dev/null; then
    echo "✓ Docker daemon در حال اجراست"
else
    echo "✗ Docker daemon در حال اجرا نیست"
    echo "راه‌اندازی با: sudo systemctl start docker"
fi

# لیست images
echo -e "\nلیست Docker Images:"
docker images 2>/dev/null || echo "هیچ imageای وجود ندارد"

# لیست containers
echo -e "\nلیست Docker Containers:"
docker ps -a 2>/dev/null || echo "هیچ containerای وجود ندارد"
