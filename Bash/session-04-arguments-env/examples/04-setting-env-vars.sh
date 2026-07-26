#!/usr/bin/env bash

# تعریف متغیر محلی
LOCAL_VAR="این یک متغیر محلی است"
echo "متغیر محلی: $LOCAL_VAR"

# تعریف متغیر محیطی
export EXPORTED_VAR="این یک متغیر محیطی است"
echo "متغیر محیطی: $EXPORTED_VAR"

# نمایش متغیرهای محیطی جدید
echo -e "\n--- متغیرهای محیطی جدید ---"
env | grep VAR

# تست در اسکریپت فرزند
echo -e "\n--- تست در اسکریپت فرزند ---"
bash -c 'echo "متغیر محلی در فرزند: $LOCAL_VAR"'
bash -c 'echo "متغیر محیطی در فرزند: $EXPORTED_VAR"'
