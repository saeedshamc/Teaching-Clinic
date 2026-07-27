#!/usr/bin/env bash

echo "=== Logging Best Practices ==="

# 1. Log Levels
echo -e "\n1. Log Levels:"
echo "✓ از log level مناسب استفاده کنید"
echo "✓ DEBUG فقط در development"
echo "✓ INFO برای normal operations"
echo "✓ ERROR برای failures"

# 2. Sensitive Data
echo -e "\n2. Sensitive Data:"
echo "✓ هرگز passwords را log نکنید"
echo "✓ tokens را mask کنید"
echo "✓ PII را log نکنید"

# 3. Performance
echo -e "\n3. Performance:"
echo "✓ Asynchronous logging"
echo "✓ Buffer برای کاهش I/O"
echo "✓ Log rotation برای size management"

# 4. Structure
echo -e "\n4. Structure:"
echo "✓ Structured log format"
echo "✓ Consistent format"
echo "✓ Include context"

# 5. Retention
echo -e "\n5. Retention:"
echo "✓ Define retention policy"
echo "✓ Compress old logs"
echo "✓ Archive important logs"
