#!/usr/bin/env bash

# تمرین ۲: اسکن امنیتی
# هدف: یک اسکریپت بنویسید که:
# 1. کاربران بدون پسورد را پیدا کند
# 2. فایل‌های world-writable را شناسایی کند
# 3. پورت‌های غیرمنتظره را پیدا کند
# 4. گزارش امنیتی تولید کند

# راه‌حل:
echo "=== گزارش امنیتی ==="
REPORT_FILE="/tmp/security_report.txt"

# خالی کردن فایل گزارش
> "$REPORT_FILE"

# 1. کاربران بدون پسورد
echo "1. کاربران بدون پسورد:" | tee -a "$REPORT_FILE"
USERS_NO_PASS=$(awk -F: '($2 == "" || $2 == "!") {print $1}' /etc/shadow 2>/dev/null)
if [ -n "$USERS_NO_PASS" ]; then
    echo "$USERS_NO_PASS" | tee -a "$REPORT_FILE"
else
    echo "هیچ کاربری بدون پسورد یافت نشد" | tee -a "$REPORT_FILE"
fi

# 2. فایل‌های world-writable در /etc
echo -e "\n2. فایل‌های world-writable در /etc:" | tee -a "$REPORT_FILE"
WRITABLE_FILES=$(find /etc -perm -002 -type f 2>/dev/null)
if [ -n "$WRITABLE_FILES" ]; then
    echo "$WRITABLE_FILES" | tee -a "$REPORT_FILE"
else
    echo "هیچ فایل world-writable یافت نشد" | tee -a "$REPORT_FILE"
fi

# 3. پورت‌های باز
echo -e "\n3. پورت‌های باز:" | tee -a "$REPORT_FILE"
OPEN_PORTS=$(netstat -tuln 2>/dev/null | grep LISTEN | awk '{print $4}' | awk -F: '{print $NF}' | sort -u)
if [ -n "$OPEN_PORTS" ]; then
    echo "$OPEN_PORTS" | tee -a "$REPORT_FILE"
else
    echo "نمی‌توان پورت‌ها را بررسی کرد" | tee -a "$REPORT_FILE"
fi

# 4. پورت‌های غیرمنتظره (پورت‌های غیر استاندارد)
echo -e "\n4. پورت‌های غیرمنتظره (بالای 1024):" | tee -a "$REPORT_FILE"
UNUSUAL_PORTS=$(netstat -tuln 2>/dev/null | grep LISTEN | awk -F: '{print $NF}' | awk '{print $1}' | awk '$1 > 1024')
if [ -n "$UNUSUAL_PORTS" ]; then
    echo "$UNUSUAL_PORTS" | tee -a "$REPORT_FILE"
else
    echo "هیچ پورت غیرمنتظره‌ای یافت نشد" | tee -a "$REPORT_FILE"
fi

echo -e "\nگزارش در $REPORT_FILE ذخیره شد"
cat "$REPORT_FILE"

# پاکسازی
rm "$REPORT_FILE"
