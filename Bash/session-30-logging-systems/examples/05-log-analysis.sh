#!/usr/bin/env bash

echo "=== Log Analysis ==="

# ایجاد sample log
cat > /tmp/sample.log << EOF
[2024-01-01 10:00:00] [INFO] Application started
[2024-01-01 10:00:01] [INFO] User logged in
[2024-01-01 10:00:02] [ERROR] Database connection failed
[2024-01-01 10:00:03] [INFO] Retrying connection
[2024-01-01 10:00:04] [ERROR] Database connection failed
[2024-01-01 10:00:05] [WARNING] Memory usage high
[2024-01-01 10:00:06] [INFO] Connection established
[2024-01-01 10:00:07] [ERROR] Query failed
EOF

# شمارش log levels
echo "1. شمارش log levels:"
echo "INFO: $(grep -c '\[INFO\]' /tmp/sample.log)"
echo "ERROR: $(grep -c '\[ERROR\]' /tmp/sample.log)"
echo "WARNING: $(grep -c '\[WARNING\]' /tmp/sample.log)"

# پیدا کردن errors
echo -e "\n2. Error logs:"
grep '\[ERROR\]' /tmp/sample.log

# پیدا کردن patterns
echo -e "\n3. Database errors:"
grep -i "database" /tmp/sample.log

# statistics
echo -e "\n4. Statistics:"
echo "Total logs: $(wc -l < /tmp/sample.log)"
echo "Error rate: $(grep -c '\[ERROR\]' /tmp/sample.log) / $(wc -l < /tmp/sample.log)"

# پاکسازی
rm /tmp/sample.log
