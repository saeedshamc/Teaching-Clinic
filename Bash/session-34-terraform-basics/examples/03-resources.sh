#!/usr/bin/env bash

echo "=== Terraform Resources ==="

# VPC Resource
cat > /tmp/vpc.tf << 'EOF'
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
  
  tags = {
    Name = "main-vpc"
  }
}

resource "aws_subnet" "public" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.1.0/24"
  
  tags = {
    Name = "public-subnet"
  }
}

resource "aws_security_group" "web" {
  name        = "web-sg"
  description = "Allow web traffic"
  vpc_id      = aws_vpc.main.id
  
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
EOF

echo "1. Resources:"
cat /tmp/vpc.tf

# Plan
echo -e "\n2. Plan:"
echo "terraform plan"
echo "✓ Plan shows 3 resources"

# Apply
echo -e "\n3. Apply:"
echo "terraform apply -auto-approve"
echo "✓ Resources created"

# Show state
echo -e "\n4. Show State:"
echo "terraform show"

# پاکسازی
rm /tmp/vpc.tf
