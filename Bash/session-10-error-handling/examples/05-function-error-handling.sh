#!/usr/bin/env bash

# تابع با مدیریت خطا
divide() {
    local num1=$1
    local num2=$2
    
    if [ $num2 -eq 0 ]; then
        echo "خطا: تقسیم بر صفر" >&2
        return 1
    fi
    
    echo $(($num1 / $num2))
    return 0
}

# استفاده از تابع
divide 10 2
if [ $? -eq 0 ]; then
    echo "تقسیم موفق بود"
else
    echo "تقسیم ناموفق بود"
fi

divide 10 0
if [ $? -eq 0 ]; then
    echo "تقسیم موفق بود"
else
    echo "تقسیم ناموفق بود"
fi
