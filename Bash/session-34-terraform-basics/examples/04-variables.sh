#!/usr/bin/env bash

echo "=== Terraform Variables ==="

# variables.tf
cat > /tmp/variables.tf << 'EOF'
variable "region" {
  description = "AWS region"
  type        = string
  default     = "us-west-2"
}

variable "instance_type" {
  description = "EC2 instance type"
  type        = string
  default     = "t2.micro"
}

variable "environment" {
  description = "Environment name"
  type        = string
}
EOF

echo "1. Variables:"
cat /tmp/variables.tf

# terraform.tfvars
cat > /tmp/terraform.tfvars << 'EOF'
region      = "us-east-1"
instance_type = "t3.micro"
environment  = "production"
EOF

echo -e "\n2. terraform.tfvars:"
cat /tmp/terraform.tfvars

# Use variable
cat > /tmp/main.tf << 'EOF'
resource "aws_instance" "example" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = var.instance_type
  
  tags = {
    Environment = var.environment
  }
}
EOF

echo -e "\n3. Use Variables:"
cat /tmp/main.tf

# Plan with variables
echo -e "\n4. Plan with Variables:"
echo "terraform plan -var='environment=staging'"
echo "✓ Plan with variable override"

# پاکسازی
rm /tmp/variables.tf /tmp/terraform.tfvars /tmp/main.tf
