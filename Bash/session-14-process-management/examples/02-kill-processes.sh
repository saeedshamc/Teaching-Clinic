#!/usr/bin/env bash

# ایجاد یک پروسه ساده
sleep 100 &
PID=$!

echo "پروسه sleep ایجاد شد با PID: $PID"

# بررسی وجود پروسه
if ps -p $PID > /dev/null; then
    echo "پروسه در حال اجراست"
fi

# خاتمه پروسه
echo "خاتمه پروسه..."
kill $PID

# صبر برای خاتمه
sleep 1

# بررسی مجدد
if ps -p $PID > /dev/null; then
    echo "پروسه هنوز در حال اجراست"
else
    echo "پروسه خاتمه یافت"
fi
