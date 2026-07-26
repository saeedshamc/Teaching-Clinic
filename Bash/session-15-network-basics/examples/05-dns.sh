#!/usr/bin/env bash

# nslookup ساده
echo "nslookup برای google.com:"
nslookup google.com

# dig پیشرفته
echo -e "\ndig برای google.com:"
dig google.com +short

# host ساده
echo -e "\nhost برای google.com:"
host google.com

# بررسی MX record
echo -e "\nMX record برای gmail.com:"
dig gmail.com MX +short
