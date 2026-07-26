#!/usr/bin/env bash

# ایجاد اسکریپت طولانی
cat > /tmp/long_task.sh << 'EOF'
#!/usr/bin/env bash
for i in {1..10}; do
    echo "کار در حال انجام: $i"
    sleep 2
done
echo "کار تمام شد"
EOF

chmod +x /tmp/long_task.sh

# اجرا با nohup
echo "اجرای task طولانی با nohup..."
nohup /tmp/long_task.sh > /tmp/nohup.out 2>&1 &
PID=$!

echo "PID: $PID"
echo "خروجی در: /tmp/nohup.out"

# نمایش خروجی بعد از کمی صبر
sleep 5
echo -e "\nخروجی تا این لحظه:"
cat /tmp/nohup.out

# پاکسازی
kill $PID 2>/dev/null
rm /tmp/long_task.sh /tmp/nohup.out
