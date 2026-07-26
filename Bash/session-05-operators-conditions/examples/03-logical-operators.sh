#!/usr/bin/env bash

AGE=25
HAS_LICENSE=true

# بررسی با AND
if [ $AGE -ge 18 ] && [ "$HAS_LICENSE" = true ]; then
    echo "شما می‌توانید رانندگی کنید"
else
    echo "شما نمی‌توانید رانندگی کنید"
fi

# بررسی با OR
SCORE=85
if [ $SCORE -ge 90 ] || [ $SCORE -eq 100 ]; then
    echo "نمره عالی!"
elif [ $SCORE -ge 75 ]; then
    echo "نمره خوب"
else
    echo "نیاز به تلاش بیشتر"
fi

# استفاده از NOT
if [ ! "$HAS_LICENSE" = true ]; then
    echo "شما گواهینامه ندارید"
fi
