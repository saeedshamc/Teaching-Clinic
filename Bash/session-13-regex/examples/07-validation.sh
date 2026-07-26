#!/usr/bin/env bash

# تابع اعتبارسنجی ایمیل
validate_email() {
    local email=$1
    local email_regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
    
    if [[ $email =~ $email_regex ]]; then
        echo "ایمیل معتبر است: $email"
        return 0
    else
        echo "ایمیل نامعتبر است: $email"
        return 1
    fi
}

# تابع اعتبارسنجی شماره تلفن
validate_phone() {
    local phone=$1
    local phone_regex="^[0-9]{10}$"
    
    if [[ $phone =~ $phone_regex ]]; then
        echo "شماره تلفن معتبر است: $phone"
        return 0
    else
        echo "شماره تلفن نامعتبر است: $phone"
        return 1
    fi
}

# تست
validate_email "user@example.com"
validate_email "invalid-email"
validate_phone "0912345678"
validate_phone "123"
