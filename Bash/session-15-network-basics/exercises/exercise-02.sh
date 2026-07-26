#!/usr/bin/env bash

# تمرین ۲: دانلود خودکار
# هدف: یک اسکریپت بنویسید که:
# 1. لیستی از URLها را از فایل بخواند
# 2. هر URL را دانلود کند
# 3. در صورت خطا، retry کند
# 4. گزارشی از موفقیت/شکست تولید کند

# راه‌حل:
URL_FILE="/tmp/urls.txt"
DOWNLOAD_DIR="/tmp/downloads"
REPORT_FILE="/tmp/download_report.txt"

# ایجاد دایرکتوری
mkdir -p "$DOWNLOAD_DIR"

# ایجاد فایل URLها
cat > "$URL_FILE" << EOF
http://example.com
http://example.org
EOF

echo "دانلود فایل‌ها..."

# خالی کردن فایل گزارش
> "$REPORT_FILE"

SUCCESS_COUNT=0
FAIL_COUNT=0

# دانلود هر URL
while read url; do
    filename=$(basename "$url")
    echo "دانلود $url..."
    
    # تلاش برای دانلود (با retry)
    for attempt in {1..3}; do
        if curl -s -o "$DOWNLOAD_DIR/$filename" "$url"; then
            echo "$url: SUCCESS" >> "$REPORT_FILE"
            echo "  ✓ دانلود موفق"
            SUCCESS_COUNT=$((SUCCESS_COUNT + 1))
            break
        else
            if [ $attempt -eq 3 ]; then
                echo "$url: FAILED" >> "$REPORT_FILE"
                echo "  ✗ دانلود ناموفق"
                FAIL_COUNT=$((FAIL_COUNT + 1))
            else
                echo "  تلاش مجدد ($attempt/3)..."
                sleep 1
            fi
        fi
    done
done < "$URL_FILE"

echo -e "\nگزارش:"
echo "موفق: $SUCCESS_COUNT"
echo "ناموفق: $FAIL_COUNT"
echo -e "\nجزئیات در $REPORT_FILE"
cat "$REPORT_FILE"

# پاکسازی
rm -rf "$DOWNLOAD_DIR" "$URL_FILE" "$REPORT_FILE"
