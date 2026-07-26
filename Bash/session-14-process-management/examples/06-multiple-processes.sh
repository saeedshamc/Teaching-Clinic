#!/usr/bin/env bash

# ایجاد چند پروسه
echo "ایجاد 5 پروسه sleep..."
for i in {1..5}; do
    sleep $((i * 10)) &
    PIDS[$!]=$!
done

echo "PIDهای ایجاد شده: ${PIDS[@]}"

# صبر کمی
sleep 2

# نمایش وضعیت
echo -e "\nوضعیت پروسه‌ها:"
for pid in "${PIDS[@]}"; do
    if ps -p $pid > /dev/null; then
        echo "PID $pid: در حال اجرا"
    else
        echo "PID $pid: خاتمه یافته"
    fi
done

# خاتمه همه
echo -e "\nخاتمه تمام پروسه‌ها..."
for pid in "${PIDS[@]}"; do
    kill $pid 2>/dev/null
done

echo "تمام شد"
