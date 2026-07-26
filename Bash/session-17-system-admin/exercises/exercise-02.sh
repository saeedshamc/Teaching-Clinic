#!/usr/bin/env bash

# تمرین ۲: مدیر سرویس
# هدف: یک اسکریپت بنویسید که:
# 1. لیستی از سرویس‌ها را بگیرد
# 2. وضعیت هر سرویس را بررسی کند
# 3. سرویس‌های متوقف را شناسایی کند
# 4. گزارش تولید کند

# راه‌حل:
# لیست سرویس‌های رایج برای بررسی
SERVICES=("ssh" "cron" "systemd-logind")

echo "=== بررسی وضعیت سرویس‌ها ==="

for service in "${SERVICES[@]}"; do
    echo -e "\nبررسی $service:"
    
    if systemctl is-active --quiet "$service" 2>/dev/null; then
        echo "✓ $service در حال اجراست"
    else
        echo "✗ $service متوقف است"
    fi
done

echo -e "\nتمام سرویس‌های فعال:"
systemctl list-units --type=service --state=running | head -10
