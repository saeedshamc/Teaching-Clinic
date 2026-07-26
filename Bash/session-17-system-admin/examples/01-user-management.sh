#!/usr/bin/env bash

# نمایش کاربران سیستم
echo "کاربران سیستم:"
cut -d: -f1 /etc/passwd | head -10

# نمایش کاربر جاری
echo -e "\nکاربر جاری:"
whoami

# نمایش گروه‌های کاربر جاری
echo -e "\nگروه‌های کاربر جاری:"
groups

# نمایش اطلاعات کاربر
echo -e "\nاطلاعات کاربر جاری:"
id

# بررسی وجود کاربر
echo -e "\nبررسی وجود کاربر root:"
if id root &>/dev/null; then
    echo "کاربر root وجود دارد"
else
    echo "کاربر root وجود ندارد"
fi
