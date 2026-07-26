#!/usr/bin/env bash

echo "=== بررسی وضعیت شبکه ==="

# 1. بررسی اتصال به اینترنت
echo -e "\n1. اتصال به اینترنت:"
if ping -c 1 -W 2 8.8.8.8 > /dev/null 2>&1; then
    echo "✓ اتصال به اینترنت برقرار است"
else
    echo "✗ اتصال به اینترنت برقرار نیست"
fi

# 2. بررسی DNS
echo -e "\n2. بررسی DNS:"
if nslookup google.com > /dev/null 2>&1; then
    echo "✓ DNS کار می‌کند"
else
    echo "✗ DNS مشکل دارد"
fi

# 3. نمایش IP سیستم
echo -e "\n3. IP سیستم:"
hostname -I

# 4. پورت‌های باز
echo -e "\n4. پورت‌های باز:"
netstat -tuln | grep LISTEN | head -5

# 5. gateway
echo -e "\n5. Gateway پیش‌فرض:"
ip route | grep default
