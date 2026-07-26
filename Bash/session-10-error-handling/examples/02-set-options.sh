#!/usr/bin/env bash

echo "بدون set -e:"
false  # exit code 1
echo "این چاپ می‌شود"

echo -e "\nبا set -e:"
set -e
# false  # این باعث خروج از اسکریپت می‌شود
echo "این چاپ نمی‌شود"

set +e  # غیرفعال کردن set -e

echo -e "\nبدون set -u:"
echo $UNDEFINED_VAR  # خالی چاپ می‌شود

echo -e "\nبا set -u:"
set -u
# echo $UNDEFINED_VAR  # خطا می‌دهد
set +u

echo -e "\nبا set -x (debug mode):"
set -x
echo "این دستور نمایش داده می‌شود"
set +x
