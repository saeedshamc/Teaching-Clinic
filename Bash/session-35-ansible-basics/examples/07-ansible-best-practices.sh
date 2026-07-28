#!/usr/bin/env bash

echo "=== Ansible Best Practices ==="

# 1. Idempotency
echo -e "\n1. Idempotency:"
echo "✓ همیشه idempotent tasks بنویسید"
echo "✓ از state parameter استفاده کنید"
echo "✓ multiple runs بدون مشکل"

# 2. Handlers
echo -e "\n2. Handlers:"
echo "✓ handlers برای restart services"
echo "✓ فقط در صورت تغییر اجرا شوند"
echo "✓ notify برای trigger handler"

# 3. Variables
echo -e "\n3. Variables:"
echo "✓ defaults برای default values"
echo "✓ vars برای override"
echo "✓ vault برای sensitive data"

# 4. Roles
echo -e "\n4. Roles:"
echo "✓ reusable roles ایجاد کنید"
echo "✓ roles را version control کنید"
echo "✓ از ansible-galaxy استفاده کنید"

# 5. Testing
echo -e "\n5. Testing:"
echo "✓ --check برای dry run"
echo "✓ --syntax-check برای validation"
echo "✓ ansible-lint برای linting"
