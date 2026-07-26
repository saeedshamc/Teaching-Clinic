#!/usr/bin/env bash

# تمرین ۲: مدیر پروسه
# هدف: یک اسکریپت بنویسید که:
# 1. نام یک پروسه را بگیرد
# 2. بررسی کند آیا در حال اجراست
# 3. اگر نبود، آن را راه‌اندازی کند
# 4. اگر بود، PID آن را نمایش دهد

# راه‌حل:
if [ $# -eq 0 ]; then
    echo "استفاده: $0 <نام_پروسه>"
    exit 1
fi

PROCESS_NAME=$1

echo "بررسی پروسه: $PROCESS_NAME"

# بررسی وجود پروسه
if pgrep -x "$PROCESS_NAME" > /dev/null; then
    PID=$(pgrep -x "$PROCESS_NAME")
    echo "پروسه $PROCESS_NAME در حال اجراست"
    echo "PID: $PID"
else
    echo "پروسه $PROCESS_NAME در حال اجرا نیست"
    
    # برای تست، sleep را راه‌اندازی می‌کنیم
    if [ "$PROCESS_NAME" = "sleep" ]; then
        echo "راه‌اندازی پروسه sleep..."
        sleep 60 &
        NEW_PID=$!
        echo "پروسه sleep راه‌اندازی شد با PID: $NEW_PID"
    else
        echo "برای راه‌اندازی واقعی، دستور مناسب را اضافه کنید"
    fi
fi
