#!/usr/bin/env bash

echo "=== Terraform Outputs ==="

# outputs.tf
cat > /tmp/outputs.tf << 'EOF'
output "vpc_id" {
  description = "ID of the VPC"
  value       = aws_vpc.main.id
}

output "public_subnet_id" {
  description = "ID of the public subnet"
  value       = aws_subnet.public.id
}

output "instance_public_ip" {
  description = "Public IP of the instance"
  value       = aws_instance.example.public_ip
}
EOF

echo "1. Outputs:"
cat /tmp/outputs.tf

# Apply
echo -e "\n2. Apply and Show Outputs:"
echo "terraform apply"
echo "terraform output"
echo "✓ Outputs displayed"

# Output specific value
echo -e "\n3. Output Specific Value:"
echo "terraform output vpc_id"

# JSON output
echo -e "\n4. JSON Output:"
echo "terraform output -json"

# پاکسازی
rm /tmp/outputs.tf
