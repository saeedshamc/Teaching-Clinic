#!/usr/bin/env bash

read -p "نام کاربری: " USERNAME
read -p "سن: " AGE

# بررسی سن
if [ $AGE -lt 18 ]; then
    echo "شما نوجوان هستید"
elif [ $AGE -ge 18 ] && [ $AGE -lt 30 ]; then
    echo "شما جوان هستید"
elif [ $AGE -ge 30 ] && [ $AGE -lt 60 ]; then
    echo "شما میانسال هستید"
else
    echo "شما مسن هستید"
fi

# بررسی نام کاربری
if [ -z "$USERNAME" ]; then
    echo "نام کاربری نمی‌تواند خالی باشد"
elif [ ${#USERNAME} -lt 3 ]; then
    echo "نام کاربری باید حداقل 3 کاراکتر باشد"
else
    echo "نام کاربری معتبر است: $USERNAME"
fi
