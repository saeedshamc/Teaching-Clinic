#!/usr/bin/env bash

# بررسی نصب بودن پکیج
echo "بررسی نصب بودن curl:"
if command -v curl &>/dev/null; then
    echo "curl نصب است"
    curl --version | head -1
else
    echo "curl نصب نیست"
fi

# لیست پکیج‌های نصب شده
echo -e "\nتعداد پکیج‌های نصب شده:"
dpkg -l | wc -l

# جستجوی پکیج
echo -e "\nجستجوی پکیج wget:"
apt-cache policy wget 2>/dev/null | head -5
