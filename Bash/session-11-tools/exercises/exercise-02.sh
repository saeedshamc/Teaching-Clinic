#!/usr/bin/env bash

# تمرین ۲: پردازش لاگ
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل لاگ نمونه ایجاد کند
# 2. با awk خطوط ERROR را استخراج کند
# 3. با sed زمان‌ها را فرمت کند
# 4. نتایج را در فایل جداگانه ذخیره کند

# راه‌حل:
# ایجاد فایل لاگ نمونه
cat > /tmp/app.log << EOF
2024-01-15 10:30:00 INFO Application started
2024-01-15 10:30:05 ERROR Database connection failed
2024-01-15 10:30:10 INFO Retrying connection
2024-01-15 10:30:15 ERROR Connection timeout
2024-01-15 10:30:20 INFO Connection established
2024-01-15 10:30:25 ERROR Query failed
EOF

echo "محتوای فایل لاگ:"
cat /tmp/app.log

# استخراج خطوط ERROR با awk
echo -e "\nخطوط ERROR:"
awk '/ERROR/ {print}' /tmp/app.log

# فرمت زمان با sed
echo -e "\nفرمت زمان (حذف ثانیه):"
sed 's/:[0-9][0-9]$//' /tmp/app.log

# ذخیره نتایج
awk '/ERROR/ {print}' /tmp/app.log > /tmp/errors.log
echo -e "\nخطوط ERROR ذخیره شد در errors.log:"
cat /tmp/errors.log

# پاکسازی
rm /tmp/app.log /tmp/errors.log
