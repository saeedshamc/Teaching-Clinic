#!/usr/bin/env bash

# تمرین ۱: بررسی سلامت
# هدف: یک اسکریپت بنویسید که:
# 1. فضای دیسک را بررسی کند
# 2. حافظه را بررسی کند
# 3. load average را بررسی کند
# 4. گزارش سلامت تولید کند

# راه‌حل:
echo "=== بررسی سلامت سیستم ==="

# 1. فضای دیسک
echo -e "\n1. فضای دیسک:"
DISK_USAGE=$(df / | awk 'NR==2 {sub(/%/,"",$5); print $5}')
echo "استفاده دیسک: $DISK_USAGE%"

# 2. حافظه
echo -e "\n2. حافظه:"
MEM_TOTAL=$(free -h | awk 'NR==2 {print $2}')
MEM_USED=$(free -h | awk 'NR==2 {print $3}')
echo "حافظه: $MEM_USED / $MEM_TOTAL"

# 3. Load average
echo -e "\n3. Load average:"
LOAD=$(uptime | awk -F'load average:' '{print $2}')
CORES=$(nproc)
echo "Load: $LOAD (cores: $CORES)"

# 4. وضعیت کلی
echo -e "\n4. وضعیت کلی:"
if [ $DISK_USAGE -lt 80 ]; then
    echo "✓ دیسک: OK"
else
    echo "⚠ دیسک: هشدار"
fi
