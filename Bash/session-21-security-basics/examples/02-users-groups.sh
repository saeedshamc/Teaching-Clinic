#!/usr/bin/env bash

# نمایش کاربران
echo "کاربران سیستم:"
cut -d: -f1 /etc/passwd | head -10

# نمایش گروه‌ها
echo -e "\nگروه‌های سیستم:"
cut -d: -f1 /etc/group | head -10

# کاربر جاری
echo -e "\nکاربر جاری:"
whoami

# گروه‌های کاربر جاری
echo -e "\nگروه‌های کاربر جاری:"
groups

# UID و GID
echo -e "\nUID و GID:"
id
