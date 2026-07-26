#!/usr/bin/env bash

# نمایش لاگ‌های اخیر
echo "لاگ‌های اخیر:"
journalctl -n 10 --no-pager

# لاگ‌های boot
echo -e "\nلاگ‌های boot:"
journalctl -b --no-pager | head -10

# لاگ‌های kernel
echo -e "\nلاگ‌های kernel:"
dmesg | tail -10
