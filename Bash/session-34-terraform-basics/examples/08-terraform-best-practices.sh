#!/usr/bin/env bash

echo "=== Terraform Best Practices ==="

# 1. State Backend
echo -e "\n1. State Backend:"
echo "✓ از remote state backend استفاده کنید"
echo "✓ S3 برای state storage"
echo "✓ DynamoDB برای locking"

# 2. Workspaces
echo -e "\n2. Workspaces:"
echo "✓ workspaces برای multiple environments"
echo "✓ dev, staging, production"
echo "✓ isolated state per workspace"

# 3. Modules
echo -e "\n3. Modules:"
echo "✓ reusable modules ایجاد کنید"
echo "✓ version control برای modules"
echo "✓ module registry استفاده کنید"

# 4. Variables
echo -e "\n4. Variables:"
echo "✓ sensitive variables در environment"
echo "✓ terraform.tfvars برای non-sensitive"
echo "✓ validation برای variables"

# 5. Documentation
echo -e "\n5. Documentation:"
echo "✓ README برای هر module"
echo "✓ examples و usage"
echo "✓ changelog برای changes"
