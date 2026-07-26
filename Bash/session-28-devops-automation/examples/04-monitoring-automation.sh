#!/usr/bin/env bash

echo "=== Monitoring Automation ==="

# تابع setup monitoring
setup_monitoring() {
    local service=$1
    
    echo "Setup Monitoring برای $service"
    
    # 1. Install agent
    echo "1. Install Monitoring Agent:"
    # apt install prometheus-node-exporter
    echo "✓ Agent نصب شد"
    
    # 2. Configure metrics
    echo -e "\n2. Configure Metrics:"
    cat > /tmp/prometheus.yml << EOF
scrape_configs:
  - job_name: '$service'
    static_configs:
      - targets: ['localhost:9090']
EOF
    echo "✓ Metrics تنظیم شد"
    
    # 3. Setup alerts
    echo -e "\n3. Setup Alerts:"
    cat > /tmp/alerts.yml << EOF
groups:
  - name: $service
    rules:
      - alert: HighErrorRate
        expr: error_rate > 0.05
EOF
    echo "✓ Alerts تنظیم شد"
    
    # پاکسازی
    rm /tmp/prometheus.yml /tmp/alerts.yml
}

# تابع create dashboard
create_dashboard() {
    local service=$1
    
    echo "ایجاد Dashboard برای $service"
    
    cat > /tmp/dashboard.json << EOF
{
  "dashboard": {
    "title": "$service Dashboard",
    "panels": [
      {
        "title": "Request Rate",
        "targets": [{"expr": "rate(requests_total[5m])"}]
      },
      {
        "title": "Error Rate",
        "targets": [{"expr": "rate(errors_total[5m])"}]
      }
    ]
  }
}
EOF
    
    echo "✓ Dashboard ایجاد شد"
    cat /tmp/dashboard.json
    
    # پاکسازی
    rm /tmp/dashboard.json
}

# اجرا
setup_monitoring "web-service"
create_dashboard "web-service"
