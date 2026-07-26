#!/usr/bin/env bash

# متغیرهای پیش‌فرض
NAME="کاربر"
VERBOSE=false

# پارس کردن گزینه‌ها
while getopts "n:v" opt; do
    case $opt in
        n)
            NAME="$OPTARG"
            ;;
        v)
            VERBOSE=true
            ;;
        \?)
            echo "استفاده: $0 [-n نام] [-v]"
            exit 1
            ;;
    esac
done

echo "سلام، $NAME!"

if [ "$VERBOSE" = true ]; then
    echo "حالت verbose فعال است"
    echo "تعداد آرگومان‌ها: $#"
    echo "آرگومان‌های اضافی: ${@:OPTIND}"
fi
