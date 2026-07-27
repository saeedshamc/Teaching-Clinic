#!/usr/bin/env bash

echo "=== Backup Best Practices ==="

# 1. 3-2-1 Rule
echo -e "\n1. 3-2-1 Rule:"
echo "✓ 3 نسخه از داده‌ها"
echo "✓ 2 نوع media مختلف"
echo "✓ 1 نسخه offsite"

# 2. Encryption
echo -e "\n2. Encryption:"
echo "✓ Encrypt sensitive backups"
echo "✓ از GPG یا OpenSSL استفاده کنید"
echo "✓ Secure key management"

# 3. Testing
echo -e "\n3. Testing:"
echo "✓ Regular restore tests"
echo "✓ Verify backup integrity"
echo "✓ Document restore procedures"

# 4. Retention
echo -e "\n4. Retention:"
echo "✓ Define retention policy"
echo "✓ Automated cleanup"
echo "✓ Archive critical backups"

# 5. Monitoring
echo -e "\n5. Monitoring:"
echo "✓ Monitor backup jobs"
echo "✓ Alert on failures"
echo "✓ Track backup size"
