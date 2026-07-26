#!/usr/bin/env bash

# تابع پاکسازی
cleanup() {
    echo "پاکسازی..."
    rm -f /tmp/temp_file.txt
    echo "پاکسازی انجام شد"
}

# trap برای EXIT
trap cleanup EXIT

# ایجاد فایل موقت
echo "ایجاد فایل موقت..."
echo "test data" > /tmp/temp_file.txt

# trap برای INT (Ctrl+C)
trap 'echo "Ctrl+C فشرده شد!"; exit 1' INT

echo "اسکریپت در حال اجراست..."
echo "برای تست Ctrl+C را فشار دهید یا صبر کنید تا تمام شود"

sleep 3

echo "اسکریپت با موفقیت تمام شد"
