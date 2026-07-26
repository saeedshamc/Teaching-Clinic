#!/usr/bin/env bash

# ایجاد فایل لاگ نمونه
cat > /tmp/app.log << EOF
2024-01-15 10:30:00 INFO Application started
2024-01-15 10:30:05 ERROR Database connection failed
2024-01-15 10:30:10 INFO Retrying connection
2024-01-15 10:30:15 ERROR Connection timeout
2024-01-15 10:30:20 INFO Connection established
2024-01-15 10:30:25 ERROR Query failed
2024-01-15 10:30:30 INFO Query retried successfully
EOF

echo "محتوای لاگ:"
cat /tmp/app.log

# شمارش خطوط ERROR
echo -e "\nتعداد خطوط ERROR:"
grep -c "ERROR" /tmp/app.log

# استخراج خطوط ERROR با timestamp
echo -e "\nخطوط ERROR:"
grep "ERROR" /tmp/app.log

# شمارش بر اساس سطح
echo -e "\nشمارش بر اساس سطح:"
awk '{count[$3]++} END {for (level in count) print level, count[level]}' /tmp/app.log

# پاکسازی
rm /tmp/app.log
