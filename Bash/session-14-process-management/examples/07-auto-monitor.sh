#!/usr/bin/env bash

# تابع مانیتورینگ
monitor_process() {
    local process_name=$1
    local check_interval=${2:-10}
    
    echo "مانیتورینگ $process_name هر $check_interval ثانیه"
    echo "برای توقف Ctrl+C را فشار دهید"
    
    while true; do
        if pgrep -x "$process_name" > /dev/null; then
            echo "$(date): $process_name در حال اجراست"
        else
            echo "$(date): $process_name در حال اجرا نیست!"
        fi
        sleep $check_interval
    done
}

# تست با یک پروسه ساده
sleep 100 &
MONITOR_PID=$!

# مانیتورینگ برای 3 بار
for i in {1..3}; do
    if pgrep -x "sleep" > /dev/null; then
        echo "تست $i: sleep در حال اجراست"
    else
        echo "تست $i: sleep در حال اجرا نیست"
    fi
    sleep 2
done

# خاتمه
kill $MONITOR_PID 2>/dev/null
echo "تست تمام شد"
