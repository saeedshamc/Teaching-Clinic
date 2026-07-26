#!/usr/bin/env bash

echo "=== Performance Monitoring ==="

# تابع مانیتورینگ response time
monitor_response_time() {
    echo "1. Response Time:"
    URL="http://example.com"
    START=$(date +%s%N)
    curl -s "$URL" > /dev/null
    END=$(date +%s%N)
    RESPONSE_TIME=$(( (END - START) / 1000000 ))
    echo "Response time: ${RESPONSE_TIME}ms"
    
    if [ $RESPONSE_TIME -gt 1000 ]; then
        echo "⚠ WARNING: Slow response"
    fi
}

# تابع مانیتورینگ throughput
monitor_throughput() {
    echo -e "\n2. Throughput:"
    # تعداد درخواست‌ها در ثانیه
    echo "Requests per second: $(netstat -an | grep ESTABLISHED | wc -l)"
}

# تابع مانیتورینگ connections
monitor_connections() {
    echo -e "\n3. Active Connections:"
    ESTABLISHED=$(netstat -an | grep ESTABLISHED | wc -l)
    LISTEN=$(netstat -an | grep LISTEN | wc -l)
    echo "Established: $ESTABLISHED"
    echo "Listening: $LISTEN"
}

# تابع مانیتورینگ network I/O
monitor_network_io() {
    echo -e "\n4. Network I/O:"
    if [ -f /proc/net/dev ]; then
        cat /proc/net/dev | awk 'NR>2 {print $1, $2, $10}'
    fi
}

# اجرا
monitor_response_time
monitor_throughput
monitor_connections
monitor_network_io
