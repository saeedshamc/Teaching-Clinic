#!/usr/bin/env bash

# نمایش سرویس‌های فعال
echo "سرویس‌های فعال:"
systemctl list-units --type=service --state=running | head -10

# وضعیت سرویس خاص
echo -e "\nوضعیت ssh:"
systemctl status ssh 2>/dev/null || echo "سرویس ssh یافت نشد"

# سرویس‌های فعال شده در boot
echo -e "\nسرویس‌های فعال شده در boot:"
systemctl list-unit-files --state=enabled | head -10
