#!/usr/bin/env bash

# مقایسه دو دستور
echo "مقایسه خروجی دو دستور:"
diff <(echo "Hello") <(echo "Hello")
diff <(echo "Hello") <(echo "World")

# خواندن از خروجی دستور
echo -e "\nخواندن از خروجی ls:"
while read file; do
    echo "فایل: $file"
done < <(ls /tmp | head -5)

# نوشتن به ورودی دستور
echo -e "\nنوشتن به grep:"
echo -e "علی\nرضا\nمحمد" | grep "علی"

# ترکیب چند فایل
echo -e "\nترکیب خروجی:"
cat <(echo "خط 1") <(echo "خط 2") <(echo "خط 3")
