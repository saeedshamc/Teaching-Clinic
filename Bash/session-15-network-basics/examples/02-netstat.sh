#!/usr/bin/env bash

# نمایش پورت‌های باز
echo "پورت‌های باز TCP:"
netstat -tuln | grep tcp

echo -e "\nپورت‌های باز UDP:"
netstat -tuln | grep udp

# نمایش اتصالات فعال
echo -e "\nاتصالات فعال:"
netstat -an | grep ESTABLISHED | head -5

# استفاده از ss (جایگزین مدرن)
echo -e "\nاستفاده از ss:"
ss -tuln | head -10
