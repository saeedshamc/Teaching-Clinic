#!/usr/bin/env bash

# تمرین ۱: تابع ماشین حساب
# هدف: یک اسکریپت بنویسید که:
# 1. توابعی برای جمع، تفریق، ضرب و تقسیم داشته باشد
# 2. از کاربر دو عدد و عملگر بگیرد
# 3. تابع مناسب را فراخوانی کند
# 4. نتیجه را چاپ کند

# راه‌حل:
# تعریف توابع ریاضی
add() {
    echo $(($1 + $2))
}

subtract() {
    echo $(($1 - $2))
}

multiply() {
    echo $(($1 * $2))
}

divide() {
    if [ $2 -eq 0 ]; then
        echo "خطا: تقسیم بر صفر مجاز نیست"
        return 1
    fi
    echo $(($1 / $2))
}

# گرفتن ورودی از کاربر
read -p "عدد اول: " NUM1
read -p "عدد دوم: " NUM2
read -p "عملگر (+, -, *, /): " OPERATOR

# اعمال عملگر مناسب
case $OPERATOR in
    "+")
        RESULT=$(add $NUM1 $NUM2)
        echo "جمع: $RESULT"
        ;;
    "-")
        RESULT=$(subtract $NUM1 $NUM2)
        echo "تفریق: $RESULT"
        ;;
    "*")
        RESULT=$(multiply $NUM1 $NUM2)
        echo "ضرب: $RESULT"
        ;;
    "/")
        RESULT=$(divide $NUM1 $NUM2)
        if [ $? -eq 0 ]; then
            echo "تقسیم: $RESULT"
        fi
        ;;
    *)
        echo "عملگر نامعتبر است"
        ;;
esac
