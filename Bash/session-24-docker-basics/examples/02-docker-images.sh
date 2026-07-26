#!/usr/bin/env bash

echo "=== مدیریت Docker Images ==="

# دانلود image
echo -e "\n1. دانلود Ubuntu image:"
docker pull ubuntu:latest

# لیست images
echo -e "\n2. لیست Images:"
docker images

# جستجوی image
echo -e "\n3. جستجوی Image:"
docker search nginx --limit 5

# حذف image
echo -e "\n4. حذف Image:"
echo "docker rmi ubuntu:latest"

# cleanup dangling images
echo -e "\n5. Cleanup Dangling Images:"
docker image prune -f
