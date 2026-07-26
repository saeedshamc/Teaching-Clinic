#!/usr/bin/env bash

# نمایش cron جاری
echo "Cron jobs جاری:"
crontab -l 2>/dev/null || echo "هیچ cron job وجود ندارد"

# ایجاد cron job نمونه
echo -e "\nایجاد cron job نمونه:"
# هر روز ساعت 12:00 اجرا شود
(crontab -l 2>/dev/null; echo "0 12 * * * echo 'Daily task' > /tmp/cron_test.txt") | crontab -

echo "Cron job ایجاد شد"
echo "برای مشاهده: crontab -l"
echo "برای حذف: crontab -r"
