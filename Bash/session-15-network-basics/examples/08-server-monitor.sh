#!/usr/bin/env bash

# تابع بررسی سرور
check_server() {
    local server=$1
    local port=${2:-80}
    
    echo "بررسی $server:$port"
    
    # بررسی ping
    if ping -c 1 -W 2 $server > /dev/null 2>&1; then
        echo "  ✓ Ping موفق"
    else
        echo "  ✗ Ping ناموفق"
    fi
    
    # بررسی پورت
    if timeout 2 bash -c "cat < /dev/null > /dev/tcp/$server/$port" 2>/dev/null; then
        echo "  ✓ پورت $port باز است"
    else
        echo "  ✗ پورت $port بسته است"
    fi
}

# تست با localhost
check_server "localhost" "22"
check_server "localhost" "80"
