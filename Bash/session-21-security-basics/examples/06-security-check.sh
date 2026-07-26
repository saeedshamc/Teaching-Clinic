#!/usr/bin/env bash

echo "=== بررسی امنیت سیستم ==="

# 1. آپدیت سیستم
echo -e "\n1. وضعیت آپدیت:"
if command -v apt &>/dev/null; then
    apt list --upgradable 2>/dev/null | head -5
fi

# 2. فایروال
echo -e "\n2. وضعیت فایروال:"
if command -v ufw &>/dev/null; then
    ufw status
elif command -v iptables &>/dev/null; then
    iptables -L -n | head -5
else
    echo "فایروال نصب نیست"
fi

# 3. لاگ‌های امنیتی
echo -e "\n3. لاگ‌های اخیر auth:"
tail -5 /var/log/auth.log 2>/dev/null || echo "دسترسی ندارید"

# 4. failed login attempts
echo -e "\n4. تلاش‌های ناموفق login:"
grep "Failed password" /var/log/auth.log 2>/dev/null | tail -5 || echo "دسترسی ندارید"
