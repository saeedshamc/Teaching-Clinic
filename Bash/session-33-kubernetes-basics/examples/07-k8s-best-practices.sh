#!/usr/bin/env bash

echo "=== Kubernetes Best Practices ==="

# 1. Resource Limits
echo -e "\n1. Resource Limits:"
echo "✓ همیشه resource limits تنظیم کنید"
echo "✓ requests و limits تعریف کنید"
echo "✓ از resource quotas استفاده کنید"

# 2. Health Checks
echo -e "\n2. Health Checks:"
echo "✓ liveness probe تعریف کنید"
echo "✓ readiness probe تعریف کنید"
echo "✓ startup probe برای slow-starting apps"

# 3. Security
echo -e "\n3. Security:"
echo "✓ از secrets برای sensitive data استفاده کنید"
echo "✓ non-root user در containers"
echo "✓ RBAC برای access control"

# 4. Scalability
echo -e "\n4. Scalability:"
echo "✓ Horizontal Pod Autoscaler"
echo "✓ Cluster Autoscaler"
echo "✓ Resource-based scaling"

# 5. Monitoring
echo -e "\n5. Monitoring:"
echo "✓ Prometheus و Grafana"
echo "✓ Logging با ELK یا Loki"
echo "✓ Tracing با Jaeger"
