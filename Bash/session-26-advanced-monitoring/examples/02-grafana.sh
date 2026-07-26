#!/usr/bin/env bash

echo "=== Grafana ==="

# بررسی نصب Grafana
if command -v grafana-server &>/dev/null; then
    echo "Grafana نصب است"
    grafana-server --version
else
    echo "Grafana نصب نیست"
    echo "نصب با:"
    echo "wget https://dl.grafana.com/oss/release/grafana_9.0.0_amd64.deb"
    echo "sudo dpkg -i grafana_9.0.0_amd64.deb"
fi

# ایجاد datasource
cat > /tmp/grafana-datasource.json << 'EOF'
{
  "name": "Prometheus",
  "type": "prometheus",
  "url": "http://localhost:9090",
  "access": "proxy",
  "isDefault": true
}
EOF

echo "Grafana Datasource Config:"
cat /tmp/grafana-datasource.json

# پاکسازی
rm /tmp/grafana-datasource.json
