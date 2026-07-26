#!/usr/bin/env bash

# تمرین ۲: اسکریپت با گزینه‌های getopts
# هدف: یک اسکریپت بنویسید که:
# 1. از getopts استفاده کند
# 2. گزینه -f برای نام فایل بگیرد
# 3. گزینه -v برای حالت verbose بگیرد
# 4. گزینه -o برای نام فایل خروجی بگیرد
# 5. اگر فایل ورودی وجود نداشته باشد، پیام خطا دهد

# راه‌حل:
INPUT_FILE=""
OUTPUT_FILE=""
VERBOSE=false

# پارس کردن گزینه‌ها
while getopts "f:o:v" opt; do
    case $opt in
        f)
            INPUT_FILE="$OPTARG"
            ;;
        o)
            OUTPUT_FILE="$OPTARG"
            ;;
        v)
            VERBOSE=true
            ;;
        \?)
            echo "استفاده: $0 -f <فایل_ورودی> [-o <فایل_خروجی>] [-v]"
            exit 1
            ;;
    esac
done

# بررسی اینکه آیا فایل ورودی داده شده است
if [ -z "$INPUT_FILE" ]; then
    echo "خطا: فایل ورودی با گزینه -f لازم است"
    echo "استفاده: $0 -f <فایل_ورودی> [-o <فایل_خروجی>] [-v]"
    exit 1
fi

# بررسی اینکه آیا فایل ورودی وجود دارد
if [ ! -f "$INPUT_FILE" ]; then
    echo "خطا: فایل '$INPUT_FILE' وجود ندارد"
    exit 1
fi

echo "پردازش فایل: $INPUT_FILE"

if [ "$VERBOSE" = true ]; then
    echo "حالت verbose فعال است"
    echo "اندازه فایل: $(wc -c < "$INPUT_FILE") بایت"
    echo "تعداد خطوط: $(wc -l < "$INPUT_FILE") خط"
fi

if [ -n "$OUTPUT_FILE" ]; then
    echo "فایل خروجی: $OUTPUT_FILE"
    cp "$INPUT_FILE" "$OUTPUT_FILE"
    echo "فایل کپی شد"
fi

echo "تمام شد!"
