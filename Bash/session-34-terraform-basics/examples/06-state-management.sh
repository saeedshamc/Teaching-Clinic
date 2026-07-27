#!/usr/bin/env bash

echo "=== State Management ==="

# List resources in state
echo "1. List State:"
echo "terraform state list"
echo "✓ All resources listed"

# Show specific resource
echo -e "\n2. Show Resource:"
echo "terraform state show aws_instance.example"
echo "✓ Resource details shown"

# Import existing resource
echo -e "\n3. Import Resource:"
echo "terraform import aws_instance.example i-1234567890"
echo "✓ Resource imported"

# Move resource
echo -e "\n4. Move Resource:"
echo "terraform state mv aws_instance.example module.web.aws_instance.example"
echo "✓ Resource moved"

# Remove resource from state
echo -e "\n5. Remove from State:"
echo "terraform state rm aws_instance.example"
echo "✓ Resource removed from state"

# Refresh state
echo -e "\n6. Refresh State:"
echo "terraform refresh"
echo "✓ State refreshed"
