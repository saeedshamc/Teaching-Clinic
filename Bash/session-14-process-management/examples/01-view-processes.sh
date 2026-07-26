#!/usr/bin/env bash

# نمایش پروسه‌های جاری کاربر
echo "پروسه‌های جاری کاربر:"
ps

echo -e "\nپروسه‌های جاری با جزئیات:"
ps aux | head -10

# جستجوی پروسه خاص
echo -e "\nجستجوی پروسه bash:"
ps aux | grep bash

# نمایش PID خود
echo -e "\nPID اسکریپت جاری: $$"
echo "PID والد: $PPID"
