#!/usr/bin/env bash

# تابع بررسی robots.txt
check_robots() {
    local url=$1
    local robots_url="${url}/robots.txt"
    
    echo "بررسی robots.txt: $robots_url"
    curl -s -I "$robots_url" | head -5
}

# تابع scraping با delay
scrape_with_delay() {
    local url=$1
    local delay=${2:-2}
    
    echo "دانلود $url با delay $delay ثانیه"
    curl -s "$url" > /tmp/page.html
    sleep $delay
    echo "دانلود کامل شد"
    
    rm /tmp/page.html
}

# تابع User-Agent مناسب
scrape_with_ua() {
    local url=$1
    local ua="MyBot/1.0 (contact: email@example.com)"
    
    echo "دانلود با User-Agent مناسب"
    curl -s -A "$ua" "$url" > /tmp/page.html
    rm /tmp/page.html
}

# مثال
echo "=== Scraping اخلاقی ==="
echo "1. همیشه robots.txt را بررسی کنید"
echo "2. از delay استفاده کنید"
echo "3. User-Agent مناسب بگذارید"
echo "4. فقط داده‌های عمومی را استخراج کنید"
echo "5. Terms of Service را رعایت کنید"
