#!/usr/bin/env bash

# تمرین ۲: Module Structure
# هدف: یک Terraform module بنویسید که:
# 1. reusable VPC module باشد
# 2. variables داشته باشد
# 3. outputs داشته باشد

# راه‌حل:
echo "=== Terraform Module Structure ==="

# ایجاد دایرکتوری module
mkdir -p /tmp/modules/vpc

# تابع ایجاد module variables
create_module_variables() {
    cat > /tmp/modules/vpc/variables.tf << 'EOF'
variable "cidr" {
  description = "CIDR block for VPC"
  type        = string
}

variable "public_subnet_cidr" {
  description = "CIDR block for public subnet"
  type        = string
}

variable "private_subnet_cidr" {
  description = "CIDR block for private subnet"
  type        = string
  default     = ""
}

variable "environment" {
  description = "Environment name"
  type        = string
  default     = "dev"
}

variable "enable_nat_gateway" {
  description = "Enable NAT gateway"
  type        = bool
  default     = false
}
EOF
    echo "✓ Module variables ایجاد شد"
}

# تابع ایجاد module main
create_module_main() {
    cat > /tmp/modules/vpc/main.tf << 'EOF'
resource "aws_vpc" "this" {
  cidr_block = var.cidr
  
  tags = {
    Name        = "${var.environment}-vpc"
    Environment = var.environment
  }
}

resource "aws_subnet" "public" {
  vpc_id     = aws_vpc.this.id
  cidr_block = var.public_subnet_cidr
  
  tags = {
    Name = "${var.environment}-public-subnet"
  }
}

resource "aws_subnet" "private" {
  count = var.private_subnet_cidr != "" ? 1 : 0
  
  vpc_id     = aws_vpc.this.id
  cidr_block = var.private_subnet_cidr
  
  tags = {
    Name = "${var.environment}-private-subnet"
  }
}

resource "aws_internet_gateway" "this" {
  vpc_id = aws_vpc.this.id
  
  tags = {
    Name = "${var.environment}-igw"
  }
}

resource "aws_nat_gateway" "this" {
  count = var.enable_nat_gateway ? 1 : 0
  
  allocation_id = aws_eip.nat[0].id
  subnet_id     = aws_subnet.public[0].id
  
  tags = {
    Name = "${var.environment}-nat"
  }
  
  depends_on = [aws_internet_gateway.this]
}

resource "aws_eip" "nat" {
  count = var.enable_nat_gateway ? 1 : 0
  
  vpc = true
  
  tags = {
    Name = "${var.environment}-nat-eip"
  }
}
EOF
    echo "✓ Module main ایجاد شد"
}

# تابع ایجاد module outputs
create_module_outputs() {
    cat > /tmp/modules/vpc/outputs.tf << 'EOF'
output "vpc_id" {
  description = "ID of the VPC"
  value       = aws_vpc.this.id
}

output "public_subnet_id" {
  description = "ID of the public subnet"
  value       = aws_subnet.public.id
}

output "private_subnet_id" {
  description = "ID of the private subnet"
  value       = length(aws_subnet.private) > 0 ? aws_subnet.private[0].id : null
}

output "internet_gateway_id" {
  description = "ID of the internet gateway"
  value       = aws_internet_gateway.this.id
}

output "nat_gateway_id" {
  description = "ID of the NAT gateway"
  value       = length(aws_nat_gateway.this) > 0 ? aws_nat_gateway.this[0].id : null
}
EOF
    echo "✓ Module outputs ایجاد شد"
}

# تابع ایجاد module README
create_module_readme() {
    cat > /tmp/modules/vpc/README.md << 'EOF'
# VPC Module

این module یک VPC با public و private subnet ایجاد می‌کند.

## Usage

```hcl
module "vpc" {
  source = "./modules/vpc"
  
  cidr                = "10.0.0.0/16"
  public_subnet_cidr  = "10.0.1.0/24"
  private_subnet_cidr = "10.0.2.0/24"
  environment         = "production"
  enable_nat_gateway  = true
}
```

## Inputs

| Name | Description | Type | Default |
|------|-------------|------|---------|
| cidr | CIDR block برای VPC | string | - |
| public_subnet_cidr | CIDR block برای public subnet | string | - |
| private_subnet_cidr | CIDR block برای private subnet | string | "" |
| environment | Environment name | string | "dev" |
| enable_nat_gateway | Enable NAT gateway | bool | false |

## Outputs

| Name | Description |
|------|-------------|
| vpc_id | ID of the VPC |
| public_subnet_id | ID of the public subnet |
| private_subnet_id | ID of the private subnet |
| internet_gateway_id | ID of the internet gateway |
| nat_gateway_id | ID of the NAT gateway |
EOF
    echo "✓ Module README ایجاد شد"
}

# تابع ایجاد root main.tf
create_root_main() {
    cat > /tmp/main.tf << 'EOF'
provider "aws" {
  region = "us-west-2"
}

module "vpc" {
  source = "./modules/vpc"
  
  cidr                = "10.0.0.0/16"
  public_subnet_cidr  = "10.0.1.0/24"
  private_subnet_cidr = "10.0.2.0/24"
  environment         = "production"
  enable_nat_gateway  = true
}

output "vpc_id" {
  value = module.vpc.vpc_id
}

output "public_subnet_id" {
  value = module.vpc.public_subnet_id
}
EOF
    echo "✓ Root main.tf ایجاد شد"
}

# تابع initialize module
init_module() {
    echo "1. Initialize Module:"
    echo "terraform init"
    echo "✓ Module initialized"
}

# تابع plan module
plan_module() {
    echo -e "\n2. Plan Module:"
    echo "terraform plan"
    echo "✓ Plan generated"
}

# تابع apply module
apply_module() {
    echo -e "\n3. Apply Module:"
    echo "terraform apply -auto-approve"
    echo "✓ Module applied"
}

# تابع show outputs
show_module_outputs() {
    echo -e "\n4. Show Outputs:"
    echo "terraform output vpc_id"
    echo "terraform output public_subnet_id"
}

# تابع cleanup
cleanup() {
    rm -rf /tmp/modules /tmp/main.tf
    echo "✓ Module directory cleaned up"
}

# اجرا
create_module_variables
create_module_main
create_module_outputs
create_module_readme
create_root_main

echo -e "\n=== Module Structure ==="
echo "Module Variables:"
cat /tmp/modules/vpc/variables.tf

echo -e "\nModule Main:"
cat /tmp/modules/vpc/main.tf

echo -e "\nModule Outputs:"
cat /tmp/modules/vpc/outputs.tf

echo -e "\nRoot Main:"
cat /tmp/main.tf

init_module
plan_module
apply_module
show_module_outputs
cleanup
