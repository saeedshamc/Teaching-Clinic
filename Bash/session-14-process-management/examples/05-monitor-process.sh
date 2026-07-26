#!/usr/bin/env bash

# ایجاد پروسه نمونه
sleep 60 &
PID=$!

echo "مانیتورینگ پروسه $PID برای 5 ثانیه..."

for i in {1..5}; do
    if ps -p $PID > /dev/null; then
        echo "ثانیه $i: پروسه در حال اجراست"
        # نمایش استفاده CPU و Memory
        ps -p $PID -o pid,pcpu,pmem,comm
    else
        echo "ثانیه $i: پروسه خاتمه یافت"
        break
    fi
    sleep 1
done

# خاتمه
kill $PID 2>/dev/null
echo "پروسه خاتمه یافت"
