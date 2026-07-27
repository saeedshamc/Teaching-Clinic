#!/usr/bin/env bash

echo "=== Docker Swarm ==="

# Initialize swarm
echo "1. Initialize Swarm:"
echo "docker swarm init"
echo "✓ Swarm initialized"

# Create service
echo -e "\n2. Create Service:"
echo "docker service create --name web --replicas 3 -p 80:80 nginx"
echo "✓ Service created with 3 replicas"

# List services
echo -e "\n3. List Services:"
echo "docker service ls"

# Scale service
echo -e "\n4. Scale Service:"
echo "docker service scale web=5"
echo "✓ Service scaled to 5 replicas"

# Remove service
echo -e "\n5. Remove Service:"
echo "docker service rm web"
echo "✓ Service removed"
