#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

source "$CONFIG_FILE"

# تابع help
show_help() {
    echo "استفاده: $0 [گزینه]"
    echo "گزینه‌ها:"
    echo "  collect   - جمع‌آوری لاگ‌ها"
    echo "  analyze   - تحلیل لاگ‌ها"
    echo "  monitor   - مانیتورینگ سیستم"
    echo "  alert     - بررسی alertها"
    echo "  all       - اجرای تمام عملیات"
}

# اجرای عملیات
case "$1" in
    collect)
        "$SCRIPT_DIR/log-collector.sh"
        ;;
    analyze)
        "$SCRIPT_DIR/log-analyzer.sh"
        ;;
    monitor)
        "$SCRIPT_DIR/monitor.sh"
        ;;
    alert)
        "$SCRIPT_DIR/alert.sh"
        ;;
    all)
        echo "اجرای تمام عملیات..."
        "$SCRIPT_DIR/log-collector.sh"
        "$SCRIPT_DIR/log-analyzer.sh"
        "$SCRIPT_DIR/alert.sh"
        ;;
    *)
        show_help
        ;;
esac
