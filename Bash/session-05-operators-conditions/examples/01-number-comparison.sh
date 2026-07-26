#!/usr/bin/env bash

NUM1=10
NUM2=20

echo "عدد اول: $NUM1"
echo "عدد دوم: $NUM2"

# مقایسه با if
if [ $NUM1 -eq $NUM2 ]; then
    echo "$NUM1 برابر با $NUM2 است"
elif [ $NUM1 -lt $NUM2 ]; then
    echo "$NUM1 کوچکتر از $NUM2 است"
else
    echo "$NUM1 بزرگتر از $NUM2 است"
fi
