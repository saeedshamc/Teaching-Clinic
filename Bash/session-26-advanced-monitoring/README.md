# جلسه ۲۶: مانیتورینگ پیشرفته (Advanced Monitoring)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پیشرفته مانیتورینگ
- Prometheus و Grafana
- Monitoring با Bash
- Alerting
- Log Monitoring
- Performance Monitoring
- مثال‌های عملی

## مفاهیم پیشرفته مانیتورینگ

مانیتورینگ پیشرفته شامل موارد زیر است:
- **Metrics** - داده‌های عددی
- **Logs** - داده‌های متنی
- **Traces** - ردیابی درخواست‌ها
- **Alerts** - هشدارها
- **Dashboards** - داشبوردها

## Prometheus

Prometheus یک سیستم مانیتورینگ open-source است.

```bash
prometheus --config.file=prometheus.yml
```

## مثال ۱: Prometheus Basics

فایل [examples/01-prometheus.sh](examples/01-prometheus.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `scrape_interval` - فاصله scraping
- `scrape_configs` - تنظیمات scraping
- `job_name` - نام job
- `targets` - آدرس‌های مانیتورینگ

## Grafana

Grafana یک پلتفرم visualization است.

```bash
grafana-server --config=grafana.ini
```

## مثال ۲: Grafana Basics

فایل [examples/02-grafana.sh](examples/02-grafana.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `grafana-server` - سرور Grafana
- datasource - منبع داده
- Prometheus به عنوان datasource
- access proxy برای دسترسی

## Monitoring با Bash

## مثال ۳: System Monitoring

فایل [examples/03-system-monitor.sh](examples/03-system-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== System Monitoring ==="

# تابع مانیتورینگ CPU
monitor_cpu() {
    echo "1. CPU Usage:"
    CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)
    echo "CPU: $CPU_USAGE%"
    
    if (( $(echo "$CPU_USAGE > 80" | bc -l) )); then
        echo "⚠ WARNING: CPU usage high"
    fi
}

# تابع مانیتورینگ Memory
monitor_memory() {
    echo -e "\n2. Memory Usage:"
    MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    echo "Memory: $MEM_USAGE%"
    
    if [ $MEM_USAGE -gt 80 ]; then
        echo "⚠ WARNING: Memory usage high"
    fi
}

# تابع مانیتورینگ Disk
monitor_disk() {
    echo -e "\n3. Disk Usage:"
    df -h | awk '$NF=="/"{print $5}' | while read usage; do
        echo "Root: $usage"
        if [ ${usage%\%} -gt 80 ]; then
            echo "⚠ WARNING: Disk usage high"
        fi
    done
}

# تابع مانیتورینگ Load
monitor_load() {
    echo -e "\n4. Load Average:"
    LOAD=$(uptime | awk -F'load average:' '{print $2}')
    echo "Load: $LOAD"
}

# اجرا
monitor_cpu
monitor_memory
monitor_disk
monitor_load
```

### توضیح خط به خط:

- `top -bn1` - CPU usage
- `free` - memory usage
- `df -h` - disk usage
- `uptime` - load average
- warning برای usage بالا

## Alerting

## مثال ۴: Alerting Script

فایل [examples/04-alerting.sh](examples/04-alerting.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Alerting ==="

# تابع ارسال alert
send_alert() {
    local severity=$1
    local message=$2
    
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] [$severity] $message"
    
    # در عمل، از email یا Slack استفاده کنید
    # echo "$message" | mail -s "Alert: $severity" admin@example.com
}

# تابع بررسی و alert
check_and_alert() {
    local metric=$1
    local value=$2
    local threshold=$3
    local severity=$4
    
    if (( $(echo "$value > $threshold" | bc -l) )); then
        send_alert "$severity" "$metric is $value (threshold: $threshold)"
    fi
}

# مانیتورینگ با alert
CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)
check_and_alert "CPU" "$CPU_USAGE" 80 "WARNING"

MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
check_and_alert "Memory" "$MEM_USAGE" 80 "WARNING"

DISK_USAGE=$(df -h | awk '$NF=="/"{print $5}' | cut -d'%' -f1)
check_and_alert "Disk" "$DISK_USAGE" 80 "WARNING"
```

### توضیح خط به خط:

- `send_alert` - ارسال alert
- `check_and_alert` - بررسی و alert
- threshold برای warning
- severity level

## Log Monitoring

## مثال ۵: Log Monitoring

فایل [examples/05-log-monitor.sh](examples/05-log-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Log Monitoring ==="

# تابع مانیتورینگ error logs
monitor_error_logs() {
    echo "1. Error Logs:"
    if [ -f /var/log/syslog ]; then
        ERROR_COUNT=$(grep -i "error" /var/log/syslog | wc -l)
        echo "Error count: $ERROR_COUNT"
        
        if [ $ERROR_COUNT -gt 10 ]; then
            echo "⚠ WARNING: Too many errors"
        fi
    else
        echo "Log file not found"
    fi
}

# تابع مانیتورینگ failed attempts
monitor_failed_attempts() {
    echo -e "\n2. Failed Login Attempts:"
    if [ -f /var/log/auth.log ]; then
        FAILED_COUNT=$(grep "Failed password" /var/log/auth.log | wc -l)
        echo "Failed attempts: $FAILED_COUNT"
        
        if [ $FAILED_COUNT -gt 5 ]; then
            echo "⚠ WARNING: Too many failed attempts"
        fi
    else
        echo "Auth log not found"
    fi
}

# تابع مانیتورینگ real-time
monitor_realtime() {
    echo -e "\n3. Real-time Monitoring:"
    echo "Monitoring /var/log/syslog (Ctrl+C to stop)"
    tail -f /var/log/syslog 2>/dev/null || echo "Cannot monitor"
}

# اجرا
monitor_error_logs
monitor_failed_attempts
# monitor_realtime  - برای مانیتورینگ real-time
```

### توضیح خط به خط:

- `grep -i "error"` - جستجوی error
- `wc -l` - شمارش خطوط
- `tail -f` - مانیتورینگ real-time
- warning برای تعداد زیاد

## Performance Monitoring

## مثال ۶: Performance Monitoring

فایل [examples/06-performance-monitor.sh](examples/06-performance-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Performance Monitoring ==="

# تابع مانیتورینگ response time
monitor_response_time() {
    echo "1. Response Time:"
    URL="http://example.com"
    START=$(date +%s%N)
    curl -s "$URL" > /dev/null
    END=$(date +%s%N)
    RESPONSE_TIME=$(( (END - START) / 1000000 ))
    echo "Response time: ${RESPONSE_TIME}ms"
    
    if [ $RESPONSE_TIME -gt 1000 ]; then
        echo "⚠ WARNING: Slow response"
    fi
}

# تابع مانیتورینگ throughput
monitor_throughput() {
    echo -e "\n2. Throughput:"
    # تعداد درخواست‌ها در ثانیه
    echo "Requests per second: $(netstat -an | grep ESTABLISHED | wc -l)"
}

# تابع مانیتورینگ connections
monitor_connections() {
    echo -e "\n3. Active Connections:"
    ESTABLISHED=$(netstat -an | grep ESTABLISHED | wc -l)
    LISTEN=$(netstat -an | grep LISTEN | wc -l)
    echo "Established: $ESTABLISHED"
    echo "Listening: $LISTEN"
}

# تابع مانیتورینگ network I/O
monitor_network_io() {
    echo -e "\n4. Network I/O:"
    if [ -f /proc/net/dev ]; then
        cat /proc/net/dev | awk 'NR>2 {print $1, $2, $10}'
    fi
}

# اجرا
monitor_response_time
monitor_throughput
monitor_connections
monitor_network_io
```

### توضیح خط به خط:

- `date +%s%N` - زمان با نانوثانیه
- response time calculation
- `netstat` - connections
- `/proc/net/dev` - network I/O

## نکات مهم

### ۱. Monitoring Strategy

```bash
# تعریف metrics مهم
# تنظیم thresholds مناسب
# alerting در زمان مناسب
```

### ۲. Data Retention

```bash
# نگهداری داده‌ها برای مدت مشخص
# cleanup منظم
# storage management
```

### ۳. Alert Fatigue

```bash
# جلوگیری از alertهای زیاد
# grouping alerts
# smart alerting
```

## مثال ۷: Monitoring Best Practices

فایل [examples/07-monitoring-best-practices.sh](examples/07-monitoring-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Monitoring Best Practices ==="

# 1. Define SLOs
echo -e "\n1. Service Level Objectives:"
echo "Availability: 99.9%"
echo "Response time: < 200ms"
echo "Error rate: < 0.1%"

# 2. Monitor the right things
echo -e "\n2. Key Metrics:"
echo "- Latency"
echo "- Traffic"
echo "- Errors"
echo "- Saturation"

# 3. Use appropriate tools
echo -e "\n3. Monitoring Stack:"
echo "- Prometheus for metrics"
echo "- Grafana for visualization"
echo "- Alertmanager for alerts"
echo "- Loki for logs"

# 4. Alert smartly
echo -e "\n4. Alerting Strategy:"
echo "- Alert on symptoms, not causes"
echo "- Use severity levels"
echo "- Include runbooks"

# 5. Regular review
echo -e "\n5. Regular Review:"
echo "- Review alerts weekly"
echo "- Update thresholds"
echo "- Remove unused alerts"
```

### توضیح خط به خط:

- SLOs - اهداف سرویس
- Key metrics - metrics کلیدی
- Monitoring stack - ابزارها
- Alerting strategy - استراتژی alerting
- Regular review - بررسی منظم

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. Monitoring همه چیز

❌ اشتباه:
```bash
# monitoring بیش از حد
```

✅ درست:
```bash
# فقط metrics مهم را مانیتور کنید
```

### ۲. Thresholds نامناسب

❌ اشتباه:
```bash
# thresholds خیلی پایین یا بالا
```

✅ درست:
```bash
# thresholds بر اساس baseline
```

### ۳. Alert fatigue

❌ اشتباه:
```bash
# alertهای زیاد و بی‌فایده
```

✅ درست:
```bash
# alertهای معنادار و grouped
```

## بهترین شیوه‌ها (Best Practices)

1. **SLOs تعریف کنید** - اهداف واضح
2. **metrics مناسب را مانیتور کنید** - نه همه چیز
3. **thresholds واقعی** - بر اساس داده‌ها
4. **alert smart** - جلوگیری از fatigue
5. **review منظم** - بهبود مداوم

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پیشرفته مانیتورینگ
- Prometheus و Grafana
- Monitoring با Bash
- Alerting
- Log Monitoring
- Performance Monitoring
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): System Monitor

یک اسکریپت بنویسید که:
1. CPU، Memory، Disk را مانیتور کند
2. alert برای usage بالا
3. گزارش تولید کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Log Monitor

یک اسکریپت بنویسید که:
1. error logs را مانیتور کند
2. failed attempts را شناسایی کند
3. alert ارسال کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Performance Optimization را یاد می‌گیریم.
