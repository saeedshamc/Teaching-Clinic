#!/usr/bin/env bash

# دستور موفق
ls /tmp
echo "Exit code ls /tmp: $?"

# دستور ناموفق
ls /nonexistent
echo "Exit code ls /nonexistent: $?"

# دستور grep
grep "test" /etc/passwd
echo "Exit code grep: $?"

# استفاده از exit code
if ls /tmp > /dev/null 2>&1; then
    echo "دستور موفق بود"
else
    echo "دستور ناموفق بود"
fi
