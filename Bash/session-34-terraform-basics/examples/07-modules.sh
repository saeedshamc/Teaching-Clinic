#!/usr/bin/env bash

echo "=== Terraform Modules ==="

# Module usage
cat > /tmp/main.tf << 'EOF'
module "vpc" {
  source = "./modules/vpc"
  
  cidr           = "10.0.0.0/16"
  public_subnet  = "10.0.1.0/24"
  private_subnet = "10.0.2.0/24"
}

module "web" {
  source = "./modules/web"
  
  vpc_id     = module.vpc.vpc_id
  subnet_id  = module.vpc.public_subnet_id
  instance_type = "t2.micro"
}
EOF

echo "1. Module Usage:"
cat /tmp/main.tf

# Module outputs
echo -e "\n2. Module Outputs:"
echo "output 'vpc_id' {"
echo "  value = module.vpc.vpc_id"
echo "}"

# Initialize modules
echo -e "\n3. Initialize Modules:"
echo "terraform init"
echo "✓ Modules downloaded"

# Plan with modules
echo -e "\n4. Plan with Modules:"
echo "terraform plan"
echo "✓ Plan includes modules"

# پاکسازی
rm /tmp/main.tf
