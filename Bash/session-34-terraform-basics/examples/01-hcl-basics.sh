#!/usr/bin/env bash

echo "=== HCL Syntax ==="

# ایجاد main.tf
cat > /tmp/main.tf << 'EOF'
# Provider configuration
provider "aws" {
  region = "us-west-2"
}

# Resource
resource "aws_instance" "example" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = "t2.micro"
  
  tags = {
    Name = "Example Instance"
  }
}
EOF

echo "1. HCL File:"
cat /tmp/main.tf

# Initialize
echo -e "\n2. Initialize:"
echo "terraform init"
echo "✓ Terraform initialized"

# Plan
echo -e "\n3. Plan:"
echo "terraform plan"
echo "✓ Plan generated"

# Apply
echo -e "\n4. Apply:"
echo "terraform apply"
echo "✓ Resources created"

# پاکسازی
rm /tmp/main.tf
