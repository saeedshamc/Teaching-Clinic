#!/usr/bin/env bash

NAME1="علی"
NAME2="رضا"

echo "نام اول: $NAME1"
echo "نام دوم: $NAME2"

# مقایسه رشته‌ها
if [ "$NAME1" = "$NAME2" ]; then
    echo "نام‌ها یکسان هستند"
else
    echo "نام‌ها متفاوت هستند"
fi

# بررسی رشته خالی
EMPTY=""
if [ -z "$EMPTY" ]; then
    echo "متغیر EMPTY خالی است"
fi

if [ -n "$NAME1" ]; then
    echo "متغیر NAME1 غیر خالی است"
fi
