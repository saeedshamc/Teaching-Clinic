#!/usr/bin/env bash

# دانلود صفحه
curl -s -o /tmp/page.html http://example.com

# استخراج لینک‌ها با grep
echo "لینک‌های صفحه:"
grep -o 'href="[^"]*"' /tmp/page.html | sed 's/href="//' | sed 's/"$//' | head -10

# پاکسازی
rm /tmp/page.html
