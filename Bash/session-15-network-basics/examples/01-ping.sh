#!/usr/bin/env bash

# تست اتصال به گوگل
echo "تست اتصال به google.com (4 بسته):"
ping -c 4 google.com

# تست اتصال به localhost
echo -e "\nتست اتصال به localhost:"
ping -c 2 localhost

# بررسی آدرس IP
echo -e "\nآدرس IP google.com:"
nslookup google.com | grep "Address:"
