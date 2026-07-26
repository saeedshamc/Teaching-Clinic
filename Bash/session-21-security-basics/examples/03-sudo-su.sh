#!/usr/bin/env bash

# بررسی دسترسی sudo
echo "بررسی دسترسی sudo:"
sudo -v 2>/dev/null && echo "دسترسی sudo دارید" || echo "دسترسی sudo ندارید"

# نمایش sudoers
echo -e "\nتنظیمات sudoers (نمایش):"
sudo cat /etc/sudoers 2>/dev/null | head -10 || echo "دسترسی برای مشاهده sudoers ندارید"

# لیست دستورات مجاز با sudo
echo -e "\nدستورات اخیر با sudo:"
sudo -l 2>/dev/null | head -10 || echo "دسترسی برای مشاهده ندارید"
