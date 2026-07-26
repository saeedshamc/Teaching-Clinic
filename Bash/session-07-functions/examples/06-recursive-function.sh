#!/usr/bin/env bash

# فاکتوریل بازگشتی
factorial_recursive() {
    local n=$1
    
    if [ $n -le 1 ]; then
        echo 1
    else
        local prev=$(factorial_recursive $((n - 1)))
        echo $((n * prev))
    fi
}

echo "فاکتوریل بازگشتی 5: $(factorial_recursive 5)"
echo "فاکتوریل بازگشتی 7: $(factorial_recursive 7)"

# فیبوناچی بازگشتی
fibonacci() {
    local n=$1
    
    if [ $n -le 1 ]; then
        echo $n
    else
        local a=$(fibonacci $((n - 1)))
        local b=$(fibonacci $((n - 2)))
        echo $((a + b))
    fi
}

echo -e "\nفیبوناچی 10: $(fibonacci 10)"
