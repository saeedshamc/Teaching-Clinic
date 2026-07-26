#!/usr/bin/env bash

echo "=== مدیریت Docker Containers ==="

# اجرا container
echo -e "\n1. اجرا Container:"
docker run -d --name test-nginx nginx:latest

# لیست containers در حال اجرا
echo -e "\n2. لیست Containers در حال اجرا:"
docker ps

# لیست تمام containers
echo -e "\n3. لیست تمام Containers:"
docker ps -a

# توقف container
echo -e "\n4. توقف Container:"
docker stop test-nginx

# حذف container
echo -e "\n5. حذف Container:"
docker rm test-nginx
