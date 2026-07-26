#!/usr/bin/env bash

echo "=== امنیت Docker ==="

# 1. اسکن image
echo -e "\n1. اسکن Image:"
echo "docker scan nginx:latest"

# 2. بررسی vulnerabilities
echo -e "\n2. بررسی Vulnerabilities:"
echo "docker image ls --format '{{.Repository}}:{{.Tag}}' | xargs -I {} docker scan {}"

# 3. استفاده از non-root container
echo -e "\n3. اجرا با non-root user:"
echo "docker run --user 1000:1000 nginx:latest"

# 4. محدود کردن resources
echo -e "\n4. محدود کردن Resources:"
echo "docker run --memory=512m --cpus=1 nginx:latest"

# 5. read-only filesystem
echo -e "\n5. Read-only Filesystem:"
echo "docker run --read-only nginx:latest"
