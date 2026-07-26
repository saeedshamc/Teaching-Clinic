#!/usr/bin/env bash

# تمرین ۲: تحلیل لاگ
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل لاگ بخواند
# 2. خطوط ERROR را استخراج کند
# 3. تعداد ERROR در هر ساعت را بشمارد
# 4. گزارش تولید کند

# راه‌حل:
# ایجاد فایل لاگ نمونه
cat > /tmp/app.log << EOF
2024-01-15 10:30:00 INFO Application started
2024-01-15 10:30:05 ERROR Database connection failed
2024-01-15 10:30:10 INFO Retrying connection
2024-01-15 10:30:15 ERROR Connection timeout
2024-01-15 10:30:20 INFO Connection established
2024-01-15 11:30:25 ERROR Query failed
2024-01-15 11:30:30 INFO Query retried successfully
2024-01-15 11:30:35 ERROR Another error
EOF

echo "محتوای لاگ:"
cat /tmp/app.log

# استخراج خطوط ERROR
echo -e "\nخطوط ERROR:"
grep "ERROR" /tmp/app.log

# شمارش ERROR در هر ساعت
echo -e "\nتعداد ERROR در هر ساعت:"
grep "ERROR" /tmp/app.log | awk '{hour=substr($2,1,2); count[hour]++} END {for (h in count) print "ساعت " h ": " count[h]}'

# پاکسازی
rm /tmp/app.log
