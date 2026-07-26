#!/usr/bin/env bash

# شمارش با while
echo "شمارش تا 5 با while:"
COUNT=1
while [ $COUNT -le 5 ]; do
    echo "عدد: $COUNT"
    COUNT=$((COUNT + 1))
done

# خواندن ورودی تا زمانی که کاربر exit را تایپ کند
echo -e "\nنوع 'exit' برای خروج:"
while true; do
    read -p "کامند: " CMD
    if [ "$CMD" = "exit" ]; then
        echo "خروج..."
        break
    fi
    echo "شما تایپ کردید: $CMD"
done
