#!/usr/bin/env bash

# توابع ریاضی ساده
add() {
    echo $(($1 + $2))
}

subtract() {
    echo $(($1 - $2))
}

multiply() {
    echo $(($1 * $2))
}

# تابع سلام
greet() {
    echo "سلام، $1!"
}
