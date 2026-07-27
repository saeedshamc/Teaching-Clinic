#!/usr/bin/env bash

echo "=== Service Discovery ==="

# Create overlay network
echo "1. Create Overlay Network:"
echo "docker network create --driver overlay my-network"
echo "✓ Overlay network created"

# Create web service
echo -e "\n2. Create Web Service:"
echo "docker service create --name web --network my-network -p 80:80 nginx"
echo "✓ Web service created"

# Create app service
echo -e "\n3. Create App Service:"
echo "docker service create --name app --network my-network my-app"
echo "✓ App service created"

# Service discovery
echo -e "\n4. Service Discovery:"
echo "App می‌تواند web را با DNS پیدا کند:"
echo "curl http://web:80"

# List tasks
echo -e "\n5. List Tasks:"
echo "docker service ps web"
