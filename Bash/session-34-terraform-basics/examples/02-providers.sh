#!/usr/bin/env bash

echo "=== Terraform Providers ==="

# AWS Provider
cat > /tmp/aws-provider.tf << 'EOF'
provider "aws" {
  region = "us-west-2"
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}
EOF

echo "1. AWS Provider:"
cat /tmp/aws-provider.tf

# Google Provider
cat > /tmp/gcp-provider.tf << 'EOF'
provider "google" {
  project = var.gcp_project
  region  = "us-central1"
  credentials = var.gcp_credentials
}
EOF

echo -e "\n2. Google Provider:"
cat /tmp/gcp-provider.tf

# Azure Provider
cat > /tmp/azure-provider.tf << 'EOF'
provider "azurerm" {
  features {}
}
EOF

echo -e "\n3. Azure Provider:"
cat /tmp/azure-provider.tf

# Initialize providers
echo -e "\n4. Initialize Providers:"
echo "terraform init"
echo "✓ Providers downloaded"

# پاکسازی
rm /tmp/aws-provider.tf /tmp/gcp-provider.tf /tmp/azure-provider.tf
