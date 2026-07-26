#!/usr/bin/env bash

FILE="/etc/passwd"

if [ -f "$FILE" ]; then
    echo "فایل $FILE وجود دارد"
    
    if [ -r "$FILE" ]; then
        echo "فایل قابل خواندن است"
    fi
    
    if [ -w "$FILE" ]; then
        echo "فایل قابل نوشتن است"
    else
        echo "فایل قابل نوشتن نیست"
    fi
else
    echo "فایل $FILE وجود ندارد"
fi

# تست دایرکتوری
DIR="/tmp"
if [ -d "$DIR" ]; then
    echo "$DIR یک دایرکتوری است"
fi

# تست فایل خالی
EMPTY_FILE="/tmp/empty_test.txt"
touch "$EMPTY_FILE"

if [ -f "$EMPTY_FILE" ]; then
    if [ -s "$EMPTY_FILE" ]; then
        echo "فایل غیر خالی است"
    else
        echo "فایل خالی است"
    fi
fi

rm "$EMPTY_FILE"
