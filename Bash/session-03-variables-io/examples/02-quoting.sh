#!/usr/bin/env bash

NAME="علی"
CURRENT_DIR=$(pwd)
CURRENT_DATE=$(date)

# Double Quotes - متغیرها تفسیر می‌شوند
echo "Double Quotes:"
echo "  نام: $NAME"
echo "  مسیر: $CURRENT_DIR"
echo "  تاریخ: $CURRENT_DATE"

# Single Quotes - متغیرها تفسیر نمی‌شوند
echo -e "\nSingle Quotes:"
echo '  نام: $NAME'
echo '  مسیر: $CURRENT_DIR'
echo '  تاریخ: $CURRENT_DATE'

# Backticks - اجرای دستورات
echo -e "\nBackticks:"
echo "  مسیر: `pwd`"
echo "  کاربر: `whoami`"
echo "  فایل‌ها: `ls | wc -l`"

# ترکیب quoting
echo -e "\nترکیب quoting:"
echo "نام کاربر: '$(whoami)'"
echo "مسیر: \"$CURRENT_DIR\""
