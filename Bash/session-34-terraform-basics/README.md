# جلسه ۳۴: مبانی Terraform (Terraform Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه Terraform
- HCL syntax
- Resources و Providers
- Variables و Outputs
- State Management
- Modules
- مثال‌های عملی

## مفاهیم پایه Terraform

Terraform یک ابزار Infrastructure as Code است:
- **Provider** - پلاگین برای cloud providers
- **Resource** - منابع infrastructure
- **Variable** - متغیرهای ورودی
- **Output** - خروجی‌ها
- **State** - وضعیت infrastructure

## HCL Syntax

HCL (HashiCorp Configuration Language) زبان Terraform است.

```hcl
resource "aws_instance" "example" {
  ami           = "ami-12345"
  instance_type = "t2.micro"
}
```

## مثال ۱: HCL Basics

فایل [examples/01-hcl-basics.sh](examples/01-hcl-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `provider` - تعریف provider
- `resource` - تعریف resource
- `terraform init` - initialize
- `terraform plan` - preview changes
- `terraform apply` - apply changes

## Providers

Provider پلاگین برای ارتباط با cloud providers است.

```hcl
provider "aws" {
  region = "us-west-2"
}

provider "google" {
  project = "my-project"
  region  = "us-central1"
}
```

## مثال ۲: Providers

فایل [examples/02-providers.sh](examples/02-providers.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- AWS provider configuration
- Google Cloud provider
- Azure provider
- `terraform init` downloads providers
- credentials از variables

## Resources

Resource منابع infrastructure را تعریف می‌کند.

```hcl
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "public" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.1.0/24"
}
```

## مثال ۳: Resources

فایل [examples/03-resources.sh](examples/03-resources.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `aws_vpc` - VPC resource
- `aws_subnet` - subnet resource
- `aws_security_group` - security group
- dependency با resource reference
- `terraform show` - show state

## Variables

Variables برای parameterization استفاده می‌شوند.

```hcl
variable "region" {
  description = "AWS region"
  default     = "us-west-2"
}
```

## مثال ۴: Variables

فایل [examples/04-variables.sh](examples/04-variables.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `variable` block - تعریف variable
- `type` - type variable
- `default` - default value
- `terraform.tfvars` - variable values
- `-var` - override variable

## Outputs

Outputs خروجی‌ها از Terraform را تعریف می‌کنند.

```hcl
output "instance_ip" {
  value = aws_instance.example.public_ip
}
```

## مثال ۵: Outputs

فایل [examples/05-outputs.sh](examples/05-outputs.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `output` block - تعریف output
- `value` - value output
- `terraform output` - نمایش outputs
- `-json` - JSON format
- استفاده outputs در سایر tools

## State Management

State وضعیت فعلی infrastructure را نگه می‌دارد.

```bash
terraform state list
terraform state show aws_instance.example
terraform refresh
```

## مثال ۶: State Management

فایل [examples/06-state-management.sh](examples/06-state-management.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `terraform state list` - لیست resources
- `terraform state show` - جزئیات resource
- `terraform import` - import existing
- `terraform state mv` - move resource
- `terraform state rm` - remove from state
- `terraform refresh` - sync state

## Modules

Modules برای reusability استفاده می‌شوند.

```hcl
module "vpc" {
  source = "./modules/vpc"
  cidr   = "10.0.0.0/16"
}
```

## مثال ۷: Modules

فایل [examples/07-modules.sh](examples/07-modules.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `module` block - استفاده module
- `source` - مسیر module
- module outputs - خروجی module
- `terraform init` - download modules
- reusability با modules

## نکات مهم

### ۱. State Backend

```hcl
terraform {
  backend "s3" {
    bucket = "my-terraform-state"
    key    = "prod/terraform.tfstate"
    region = "us-west-2"
  }
}
```

### ۲. Workspaces

```bash
terraform workspace new dev
terraform workspace list
terraform workspace select prod
```

### ۳. Locking

```hcl
terraform {
  backend "s3" {
    bucket = "my-terraform-state"
    key    = "prod/terraform.tfstate"
    region = "us-west-2"
    dynamodb_table = "terraform-locks"
  }
}
```

## مثال ۸: Terraform Best Practices

فایل [examples/08-terraform-best-practices.sh](examples/08-terraform-best-practices.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- Remote state backend برای collaboration
- Workspaces برای environments
- Modules برای reusability
- Variables برای parameterization
- Documentation برای maintainability

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: Local State

❌ اشتباه:
```bash
# local state در team
```

✅ درست:
```bash
# remote state backend
```

### ۲: Hardcoded Values

❌ اشتباه:
```hcl
# hardcoded values
```

✅ درست:
```hcl
# variables
```

### ۳: بدون Version Control

❌ اشتباه:
```bash
# بدون version control
```

✅ درست:
```bash
# git برای Terraform code
```

## بهترین شیوه‌ها (Best Practices)

1. **Remote State** - S3 backend
2. **Workspaces** - برای environments
3. **Modules** - برای reusability
4. **Variables** - برای parameterization
5. **Documentation** - README و examples

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه Terraform
- HCL syntax
- Resources و Providers
- Variables و Outputs
- State Management
- Modules
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Basic Infrastructure

یک Terraform config بنویسید که:
1. VPC ایجاد کند
2. subnet ایجاد کند
3. security group ایجاد کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Module Structure

یک Terraform module بنویسید که:
1. reusable VPC module باشد
2. variables داشته باشد
3. outputs داشته باشد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Ansible Basics را یاد می‌گیریم.
