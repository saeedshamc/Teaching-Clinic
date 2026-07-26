#!/usr/bin/env bash

# اسکن localhost
echo "اسکن پورت‌های رایج localhost:"
nmap localhost

# اسکن پورت‌های خاص
echo -e "\nاسکن پورت‌های 80 و 443:"
nmap -p 80,443 localhost

# اسکن با نسخه سرویس
echo -e "\nاسکن با نسخه سرویس:"
nmap -sV localhost
