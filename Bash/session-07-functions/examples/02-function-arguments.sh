#!/usr/bin/env bash

# تابع با آرگومان
greet_user() {
    NAME=$1
    echo "سلام، $NAME! خوش آمدی."
}

# استفاده از تابع با آرگومان
greet_user "علی"
greet_user "رضا"

# تابع با چند آرگومان
calculate_sum() {
    NUM1=$1
    NUM2=$2
    SUM=$((NUM1 + NUM2))
    echo "جمع $NUM1 و $NUM2 برابر با $SUM است"
}

calculate_sum 10 20
calculate_sum 5 15
