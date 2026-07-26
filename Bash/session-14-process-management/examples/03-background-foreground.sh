#!/usr/bin/env bash

# اجرا در پس‌زمینه
echo "اجرای sleep در پس‌زمینه..."
sleep 10 &
JOB1=$!

echo "اجرای another sleep در پس‌زمینه..."
sleep 15 &
JOB2=$!

# نمایش jobها
echo -e "\njobهای فعال:"
jobs

# صبر کمی
sleep 2

# آوردن به پیش‌زمینه (در اینجا فقط نمایش)
echo -e "\nPIDهای فعال: $JOB1, $JOB2"

# خاتمه jobها
kill $JOB1 $JOB2 2>/dev/null

echo "jobها خاتمه یافتند"
