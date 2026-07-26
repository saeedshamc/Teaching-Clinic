#!/usr/bin/env bash

# تمرین ۲: جستجوی فایل‌ها
# هدف: یک اسکریپت بنویسید که:
# 1. یک ساختار پوشه‌ای پیچیده ایجاد کند
# 2. فایل‌های مختلف با پسوندهای مختلف در آن ایجاد کند
# 3. با استفاده از find، تمام فایل‌های با پسوند مشخص را پیدا کند
# 4. تعداد فایل‌های پیدا شده را چاپ کند
# 5. در نهایت همه را پاک کند

# راه‌حل:
echo "ایجاد ساختار پوشه‌ای..."
mkdir -p /tmp/complex_search/{src,docs,config,data}

echo "ایجاد فایل‌ها..."
touch /tmp/complex_search/src/main.py
touch /tmp/complex_search/src/helper.py
touch /tmp/complex_search/docs/readme.md
touch /tmp/complex_search/docs/guide.md
touch /tmp/complex_search/config/app.conf
touch /tmp/complex_search/config/db.conf
touch /tmp/complex_search/data/users.csv
touch /tmp/complex_search/data/products.csv

echo "جستجوی فایل‌های py:"
PY_FILES=$(find /tmp/complex_search -name "*.py")
echo "$PY_FILES"
PY_COUNT=$(echo "$PY_FILES" | wc -l)
echo "تعداد فایل‌های py: $PY_COUNT"

echo -e "\nجستجوی فایل‌های md:"
MD_FILES=$(find /tmp/complex_search -name "*.md")
echo "$MD_FILES"
MD_COUNT=$(echo "$MD_FILES" | wc -l)
echo "تعداد فایل‌های md: $MD_COUNT"

echo -e "\nجستجوی فایل‌های conf:"
CONF_FILES=$(find /tmp/complex_search -name "*.conf")
echo "$CONF_FILES"
CONF_COUNT=$(echo "$CONF_FILES" | wc -l)
echo "تعداد فایل‌های conf: $CONF_COUNT"

echo -e "\nپاکسازی..."
rm -rf /tmp/complex_search

echo "تمام شد!"
