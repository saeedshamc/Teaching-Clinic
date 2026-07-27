#!/usr/bin/env bash

echo "=== Rolling Updates ==="

# Update image
echo "1. Update Deployment Image:"
echo "kubectl set image deployment/nginx-deployment nginx=nginx:1.19"
echo "✓ Image update started"

# Check rollout status
echo -e "\n2. Check Rollout Status:"
echo "kubectl rollout status deployment/nginx-deployment"

# Check rollout history
echo -e "\n3. Check Rollout History:"
echo "kubectl rollout history deployment/nginx-deployment"

# Rollback
echo -e "\n4. Rollback:"
echo "kubectl rollout undo deployment/nginx-deployment"
echo "✓ Rollback completed"

# Rollback to specific revision
echo -e "\n5. Rollback to Revision:"
echo "kubectl rollout undo deployment/nginx-deployment --to-revision=2"
