#!/usr/bin/env bash

# تمرین ۱: بررسی چند سرور
# هدف: یک اسکریپت بنویسید که:
# 1. لیستی از سرورها را بگیرد
# 2. به هر سرور ping بزند
# 3. نتایج را در فایل ذخیره کند

# راه‌حل:
SERVERS_FILE="/tmp/servers.txt"
RESULT_FILE="/tmp/ping_results.txt"

# ایجاد فایل سرورها
cat > "$SERVERS_FILE" << EOF
google.com
localhost
8.8.8.8
EOF

echo "بررسی سرورها..."

# خالی کردن فایل نتایج
> "$RESULT_FILE"

# بررسی هر سرور
while read server; do
    echo "بررسی $server..."
    
    if ping -c 1 -W 2 "$server" > /dev/null 2>&1; then
        echo "$server: ONLINE" >> "$RESULT_FILE"
        echo "  ✓ $server آنلاین است"
    else
        echo "$server: OFFLINE" >> "$RESULT_FILE"
        echo "  ✗ $server آفلاین است"
    fi
done < "$SERVERER_FILE"

echo -e "\nنتایج در $RESULT_FILE ذخیره شد"
cat "$RESULT_FILE"

# پاکسازی
rm "$SERVERER_FILE" "$RESULT_FILE"
