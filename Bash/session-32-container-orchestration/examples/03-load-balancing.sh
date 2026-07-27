#!/usr/bin/env bash

echo "=== Load Balancing ==="

# Create service with multiple replicas
echo "1. Create Service with Load Balancing:"
echo "docker service create --name web --replicas 5 -p 80:80 nginx"
echo "✓ Service created with 5 replicas"

# Check load balancing
echo -e "\n2. Check Load Balancing:"
echo "for i in {1..10}; do curl localhost; done"
echo "ترافیک بین replicas توزیع می‌شود"

# Inspect service
echo -e "\n3. Inspect Service:"
echo "docker service inspect web --format '{{.Endpoint.Ports}}'"

# Check VIP
echo -e "\n4. Check VIP:"
echo "docker service inspect web --format '{{.Endpoint.VirtualIPs}}'"
