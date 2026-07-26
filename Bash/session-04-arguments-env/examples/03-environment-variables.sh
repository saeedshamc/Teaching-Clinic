#!/usr/bin/env bash

echo "--- متغیرهای محیطی رایج ---"
echo "پوشه خانگی: $HOME"
echo "نام کاربر: $USER"
echo "Shell: $SHELL"
echo "مسیر جاری: $PWD"
echo "زبان: $LANG"
echo "نام میزبان: $HOSTNAME"
echo "PATH: $PATH"

echo -e "\n--- تمام متغیرهای محیطی ---"
env | head -10
