#!/usr/bin/env bash

echo "=== Scaling ==="

# Create service
echo "1. Create Service:"
echo "docker service create --name web --replicas 3 -p 80:80 nginx"
echo "✓ Service created with 3 replicas"

# Scale up
echo -e "\n2. Scale Up:"
echo "docker service scale web=10"
echo "✓ Scaled to 10 replicas"

# Scale down
echo -e "\n3. Scale Down:"
echo "docker service scale web=5"
echo "✓ Scaled to 5 replicas"

# Auto-scaling script
echo -e "\n4. Auto-scaling:"
echo "#!/bin/bash"
echo "CPU_USAGE=\$(docker stats --no-stream --format \"{{.CPUPerc}}\" web)"
echo "if [ \$CPU_USAGE > 80 ]; then"
echo "  docker service scale web=\$((current + 2))"
echo "fi"
