#!/usr/bin/env bash

# شمارش با until
echo "شمارش تا 5 با until:"
COUNT=1
until [ $COUNT -gt 5 ]; do
    echo "عدد: $COUNT"
    COUNT=$((COUNT + 1))
done

# ایجاد فایل برای تست
touch /tmp/test.txt

# منتظر ماندن برای فایل
echo -e "\nمنتظر فایل test.txt..."
until [ -f "/tmp/test.txt" ]; do
    echo "در حال انتظار..."
    sleep 1
done
echo "فایل پیدا شد!"

rm /tmp/test.txt
