#!/usr/bin/env bash

echo "=== Prometheus ==="

# ایجاد تنظیمات Prometheus
cat > /tmp/prometheus.yml << 'EOF'
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: 'prometheus'
    static_configs:
      - targets: ['localhost:9090']
  
  - job_name: 'node'
    static_configs:
      - targets: ['localhost:9100']
EOF

echo "Prometheus Config:"
cat /tmp/prometheus.yml

# اجرای Prometheus
echo -e "\nاجرای Prometheus:"
echo "prometheus --config.file=/tmp/prometheus.yml"

# پاکسازی
rm /tmp/prometheus.yml
