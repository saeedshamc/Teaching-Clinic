#!/usr/bin/env bash

# تعریف آرایه انجمنی
declare -A PERSON

PERSON["name"]="علی"
PERSON["age"]=25
PERSON["city"]="تهران"

echo "اطلاعات شخص:"
echo "  نام: ${PERSON["name"]}"
echo "  سن: ${PERSON["age"]}"
echo "  شهر: ${PERSON["city"]}"

# حلقه روی آرایه انجمنی
echo -e "\nتمام کلیدها:"
for key in "${!PERSON[@]}"; do
    echo "  $key: ${PERSON[$key]}"
done
