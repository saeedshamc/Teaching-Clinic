#!/usr/bin/env bash

echo "=== مانیتورینگ سیستم ==="

# فضای دیسک
echo -e "\n1. فضای دیسک:"
df -h | head -5

# حافظه
echo -e "\n2. حافظه:"
free -h

# CPU
echo -e "\n3. Load average:"
uptime

# Uptime
echo -e "\n4. Uptime:"
uptime -p

# تعداد پروسه‌ها
echo -e "\n5. تعداد پروسه‌ها:"
ps aux | wc -l
