#!/usr/bin/env bash

# تمرین ۲: scraping اخلاقی
# هدف: یک اسکریپت بنویسید که:
# 1. robots.txt را بررسی کند
# 2. با delay مناسب صفحه را دانلود کند
# 3. داده‌های مورد نیاز را استخراج کند

# راه‌حل:
URL="http://example.com"
DELAY=2

echo "=== Scraping اخلاقی ==="

# 1. بررسی robots.txt
echo -e "\n1. بررسی robots.txt:"
ROBOTS_URL="${URL}/robots.txt"
echo "بررسی: $ROBOTS_URL"
curl -s -I "$ROBOTS_URL" | head -3

# 2. دانلود با delay
echo -e "\n2. دانلود با delay $DELAY ثانیه:"
echo "دانلود صفحه..."
curl -s -o /tmp/page.html "$URL"
echo "صفحه دانلود شد"
sleep $DELAY
echo "Delay کامل شد"

# 3. استخراج داده
echo -e "\n3. استخراج عنوان:"
TITLE=$(grep -o '<title>.*</title>' /tmp/page.html | sed 's/<title>//;s/<\/title>//')
echo "عنوان: $TITLE"

echo -e "\n4. استخراج لینک‌ها:"
LINKS=$(grep -o 'href="[^"]*"' /tmp/page.html | sed 's/href="//;s/"$//' | head -5)
echo "$LINKS"

# پاکسازی
rm /tmp/page.html

echo -e "\n=== Scraping اخلاقی کامل شد ==="
