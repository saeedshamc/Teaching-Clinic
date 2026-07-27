#!/usr/bin/env bash

# تمرین ۱: Basic Infrastructure
# هدف: یک Terraform config بنویسید که:
# 1. VPC ایجاد کند
# 2. subnet ایجاد کند
# 3. security group ایجاد کند

# راه‌حل:
echo "=== Terraform Basic Infrastructure ==="

# تابع ایجاد provider
create_provider() {
    cat > /tmp/provider.tf << 'EOF'
provider "aws" {
  region = "us-west-2"
}
EOF
    echo "✓ Provider ایجاد شد"
}

# تابع ایجاد VPC
create_vpc() {
    cat > /tmp/vpc.tf << 'EOF'
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
  
  tags = {
    Name        = "main-vpc"
    Environment = "production"
  }
}

output "vpc_id" {
  description = "ID of the VPC"
  value       = aws_vpc.main.id
}
EOF
    echo "✓ VPC resource ایجاد شد"
}

# تابع ایجاد subnet
create_subnet() {
    cat > /tmp/subnet.tf << 'EOF'
resource "aws_subnet" "public" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.1.0/24"
  
  tags = {
    Name = "public-subnet"
  }
}

output "subnet_id" {
  description = "ID of the subnet"
  value       = aws_subnet.public.id
}
EOF
    echo "✓ Subnet resource ایجاد شد"
}

# تابع ایجاد security group
create_security_group() {
    cat > /tmp/security_group.tf << 'EOF'
resource "aws_security_group" "web" {
  name        = "web-sg"
  description = "Allow web traffic"
  vpc_id      = aws_vpc.main.id
  
  tags = {
    Name = "web-security-group"
  }
  
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "HTTP from anywhere"
  }
  
  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "HTTPS from anywhere"
  }
  
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound traffic"
  }
}

output "security_group_id" {
  description = "ID of the security group"
  value       = aws_security_group.web.id
}
EOF
    echo "✓ Security Group resource ایجاد شد"
}

# تابع initialize
terraform_init() {
    echo "1. Initialize Terraform:"
    echo "terraform init"
    echo "✓ Terraform initialized"
}

# تابع plan
terraform_plan() {
    echo -e "\n2. Plan Infrastructure:"
    echo "terraform plan"
    echo "✓ Plan generated"
}

# تابع apply
terraform_apply() {
    echo -e "\n3. Apply Infrastructure:"
    echo "terraform apply -auto-approve"
    echo "✓ Infrastructure created"
}

# تابع show outputs
show_outputs() {
    echo -e "\n4. Show Outputs:"
    echo "terraform output vpc_id"
    echo "terraform output subnet_id"
    echo "terraform output security_group_id"
}

# تابع destroy
terraform_destroy() {
    echo -e "\n5. Destroy Infrastructure:"
    echo "terraform destroy -auto-approve"
    echo "✓ Infrastructure destroyed"
}

# تابع cleanup
cleanup() {
    rm -f /tmp/provider.tf /tmp/vpc.tf /tmp/subnet.tf /tmp/security_group.tf
    echo "✓ Temporary files cleaned up"
}

# اجرا
create_provider
create_vpc
create_subnet
create_security_group

echo -e "\n=== Terraform Files ==="
echo "Provider:"
cat /tmp/provider.tf

echo -e "\nVPC:"
cat /tmp/vpc.tf

echo -e "\nSubnet:"
cat /tmp/subnet.tf

echo -e "\nSecurity Group:"
cat /tmp/security_group.tf

terraform_init
terraform_plan
terraform_apply
show_outputs
terraform_destroy
cleanup
