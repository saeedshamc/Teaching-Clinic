#!/usr/bin/env bash

echo "=== اسکن امنیتی پایه ==="

# 1. کاربران بدون پسورد
echo -e "\n1. کاربران بدون پسورد:"
awk -F: '($2 == "" || $2 == "!") {print $1}' /etc/shadow 2>/dev/null || echo "دسترسی ندارید"

# 2. فایل‌های world-writable
echo -e "\n2. فایل‌های world-writable در /etc:"
find /etc -perm -002 -type f 2>/dev/null | head -5 || echo "هیچ فایلی یافت نشد"

# 3. پورت‌های باز
echo -e "\n3. پورت‌های باز:"
netstat -tuln | grep LISTEN | head -5

# 4. پروسه‌های root
echo -e "\n4. پروسه‌های در حال اجرا با root:"
ps aux | awk '$1 == "root"' | head -5
