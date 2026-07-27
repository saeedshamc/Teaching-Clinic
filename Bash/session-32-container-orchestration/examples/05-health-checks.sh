#!/usr/bin/env bash

echo "=== Health Checks ==="

# Create service with health check
echo "1. Create Service with Health Check:"
echo "docker service create --name web --replicas 3 \\"
echo "  --health-cmd 'curl -f http://localhost/ || exit 1' \\"
echo "  --health-interval 5s \\"
echo "  --health-retries 3 \\"
echo "  -p 80:80 nginx"
echo "✓ Service created with health check"

# Check health status
echo -e "\n2. Check Health Status:"
echo "docker service ps web"

# Inspect health
echo -e "\n3. Inspect Health:"
echo "docker inspect web --format '{{.Spec.TaskTemplate.ContainerSpec.Healthcheck}}'"

# Rolling update
echo -e "\n4. Rolling Update:"
echo "docker service update --image nginx:latest web"
echo "✓ Rolling update started"
