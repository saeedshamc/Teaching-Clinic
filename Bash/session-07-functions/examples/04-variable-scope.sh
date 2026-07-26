#!/usr/bin/env bash

# متغیر سراسری
GLOBAL_VAR="من سراسری هستم"

test_scope() {
    # متغیر محلی
    local LOCAL_VAR="من محلی هستم"
    
    echo "درون تابع:"
    echo "  GLOBAL_VAR: $GLOBAL_VAR"
    echo "  LOCAL_VAR: $LOCAL_VAR"
    
    # تغییر متغیر سراسری
    GLOBAL_VAR="من تغییر کردم"
}

echo "قبل از تابع:"
echo "  GLOBAL_VAR: $GLOBAL_VAR"

test_scope

echo "بعد از تابع:"
echo "  GLOBAL_VAR: $GLOBAL_VAR"
echo "  LOCAL_VAR: $LOCAL_VAR"  # این چاپ نمی‌شود
