# جلسه ۲۳: مبانی Cloud (Cloud Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه Cloud Computing
- انواع سرویس‌های Cloud
- استفاده از AWS CLI
- استفاده از Azure CLI
- استفاده از Google Cloud CLI
- مدیریت سرویس‌های Cloud
- مثال‌های عملی

## مفاهیم پایه Cloud

Cloud Computing مدل‌های مختلفی دارد:
- **IaaS** - Infrastructure as a Service
- **PaaS** - Platform as a Service
- **SaaS** - Software as a Service

## AWS CLI

```bash
aws configure          - تنظیم credentials
aws ec2 describe-instances  - لیست instances
aws s3 ls              - لیست buckets
```

## مثال ۱: AWS CLI

فایل [examples/01-aws-cli.sh](examples/01-aws-cli.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== AWS CLI ==="

# بررسی نصب AWS CLI
if command -v aws &>/dev/null; then
    echo "AWS CLI نصب است"
    aws --version
else
    echo "AWS CLI نصب نیست"
    echo "نصب با: pip install awscli"
fi

# تنظیم credentials (نمایش)
echo -e "\nتنظیم credentials:"
echo "aws configure"
echo "AWS Access Key ID: YOUR_KEY"
echo "AWS Secret Access Key: YOUR_SECRET"
echo "Default region: us-east-1"

# لیست S3 buckets
echo -e "\nلیست S3 buckets:"
aws s3 ls 2>/dev/null || echo "نیاز به credentials یا هیچ bucketی وجود ندارد"

# لیست EC2 instances
echo -e "\nلیست EC2 instances:"
aws ec2 describe-instances 2>/dev/null || echo "نیاز به credentials یا هیچ instanceای وجود ندارد"
```

### توضیح خط به خط:

- `aws configure` - تنظیم credentials
- `aws s3 ls` - لیست S3 buckets
- `aws ec2 describe-instances` - لیست EC2 instances
- نیاز به AWS credentials

## Azure CLI

```bash
az login               - ورود به Azure
az vm list            - لیست virtual machines
az storage account list  - لیست storage accounts
```

## مثال ۲: Azure CLI

فایل [examples/02-azure-cli.sh](examples/02-azure-cli.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Azure CLI ==="

# بررسی نصب Azure CLI
if command -v az &>/dev/null; then
    echo "Azure CLI نصب است"
    az --version
else
    echo "Azure CLI نصب نیست"
    echo "نصب با: curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash"
fi

# ورود (نمایش)
echo -e "\nورود به Azure:"
echo "az login"

# لیست resource groups
echo -e "\nلیست Resource Groups:"
az group list 2>/dev/null || echo "نیاز به login یا هیچ resource groupی وجود ندارد"

# لیست virtual machines
echo -e "\nلیست Virtual Machines:"
az vm list 2>/dev/null || echo "نیاز به login یا هیچ VMی وجود ندارد"
```

### توضیح خط به خط:

- `az login` - ورود به Azure
- `az group list` - لیست resource groups
- `az vm list` - لیست virtual machines
- نیاز به Azure account

## Google Cloud CLI

```bash
gcloud init            - تنظیم اولیه
gcloud compute instances list  - لیست instances
gsutil ls              - لیست buckets
```

## مثال ۳: GCP CLI

فایل [examples/03-gcp-cli.sh](examples/03-gcp-cli.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Google Cloud CLI ==="

# بررسی نصب gcloud
if command -v gcloud &>/dev/null; then
    echo "gcloud نصب است"
    gcloud --version
else
    echo "gcloud نصب نیست"
    echo "نصب با: curl https://sdk.cloud.google.com | bash"
fi

# تنظیم اولیه (نمایش)
echo -e "\nتنظیم اولیه:"
echo "gcloud init"

# لیست projects
echo -e "\nلیست Projects:"
gcloud projects list 2>/dev/null || echo "نیاز به login یا هیچ projectی وجود ندارد"

# لیست instances
echo -e "\nلیست Compute Instances:"
gcloud compute instances list 2>/dev/null || echo "نیاز به login یا هیچ instanceای وجود ندارد"
```

### توضیح خط به خط:

- `gcloud init` - تنظیم اولیه
- `gcloud projects list` - لیست projects
- `gcloud compute instances list` - لیست instances
- نیاز به GCP account

## مدیریت Cloud با Bash

## مثال ۴: Cloud Management

فایل [examples/04-cloud-management.sh](examples/04-cloud-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مدیریت Cloud با Bash ==="

# تابع بررسی اتصال AWS
check_aws() {
    if aws sts get-caller-identity &>/dev/null; then
        echo "✓ AWS متصل است"
        aws sts get-caller-identity
    else
        echo "✗ AWS متصل نیست"
    fi
}

# تابع بررسی اتصال Azure
check_azure() {
    if az account show &>/dev/null; then
        echo "✓ Azure متصل است"
        az account show
    else
        echo "✗ Azure متصل نیست"
    fi
}

# تابع بررسی اتصال GCP
check_gcp() {
    if gcloud auth list --filter=status:ACTIVE --format="value(account)" &>/dev/null; then
        echo "✓ GCP متصل است"
        gcloud auth list --filter=status:ACTIVE
    else
        echo "✗ GCP متصل نیست"
    fi
}

# بررسی همه
echo -e "\n1. AWS:"
check_aws

echo -e "\n2. Azure:"
check_azure

echo -e "\n3. GCP:"
check_gcp
```

### توضیح خط به خط:

- توابع جداگانه برای هر cloud provider
- `aws sts get-caller-identity` - بررسی AWS
- `az account show` - بررسی Azure
- `gcloud auth list` - بررسی GCP

## مثال‌های عملی

### ایجاد S3 Bucket

```bash
aws s3 mb s3://my-bucket-name
```

### آپلود فایل به S3

```bash
aws s3 cp file.txt s3://my-bucket-name/
```

## مثال ۵: S3 Operations

فایل [examples/05-s3-operations.sh](examples/05-s3-operations.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== عملیات S3 ==="

# ایجاد bucket
echo -e "\n1. ایجاد S3 Bucket:"
echo "aws s3 mb s3://my-unique-bucket-name-$(date +%s)"

# لیست buckets
echo -e "\n2. لیست Buckets:"
aws s3 ls 2>/dev/null || echo "نیاز به credentials"

# آپلود فایل
echo -e "\n3. آپلود فایل:"
echo "aws s3 cp /tmp/file.txt s3://my-bucket/"

# دانلود فایل
echo -e "\n4. دانلود فایل:"
echo "aws s3 cp s3://my-bucket/file.txt /tmp/downloaded.txt"

# حذف فایل
echo -e "\n5. حذف فایل:"
echo "aws s3 rm s3://my-bucket/file.txt"

# حذف bucket
echo -e "\n6. حذف Bucket:"
echo "aws s3 rb s3://my-bucket/"
```

### توضیح خط به خط:

- `aws s3 mb` - ایجاد bucket
- `aws s3 ls` - لیست buckets
- `aws s3 cp` - آپلود/دانلود
- `aws s3 rm` - حذف فایل
- `aws s3 rb` - حذف bucket

## نکات امنیتی مهم

### ۱. محافظت از Credentials

```bash
# هرگز credentials را در کد ذخیره نکنید
# از environment variables استفاده کنید
export AWS_ACCESS_KEY_ID="your_key"
export AWS_SECRET_ACCESS_KEY="your_secret"
```

### ۲. استفاده از IAM Roles

```bash
# به جای credentials، از IAM roles استفاده کنید
# برای EC2 instances
```

### ۳. محدود کردن دسترسی

```bash
# فقط دسترسی لازم را بدهید
# Principle of Least Privilege
```

## مثال ۶: Security Best Practices

فایل [examples/06-cloud-security.sh](examples/06-cloud-security.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== امنیت Cloud ==="

# 1. بررسی IAM users
echo -e "\n1. لیست IAM Users:"
aws iam list-users 2>/dev/null || echo "نیاز به credentials"

# 2. بررسی security groups
echo -e "\n2. لیست Security Groups:"
aws ec2 describe-security-groups 2>/dev/null || echo "نیاز به credentials"

# 3. بررسی S3 bucket permissions
echo -e "\n3. بررسی S3 Permissions:"
aws s3api list-buckets 2>/dev/null || echo "نیاز به credentials"

# 4. بررسی cost
echo -e "\n4. بررسی Cost:"
echo "aws ce get-cost-and-usage --time-period Start=2024-01-01,End=2024-01-31"

# 5. تنظیم alarms
echo -e "\n5. تنظیم Cost Alarm:"
echo "aws cloudwatch put-metric-alarm --alarm-name high-cost --metric-name EstimatedCharges --namespace AWS/Billing --statistic Maximum --period 21600 --threshold 100 --comparison-operator GreaterThanThreshold"
```

### توضیح خط به خط:

- بررسی IAM users
- بررسی security groups
- بررسی S3 permissions
- بررسی cost
- تنظیم cost alarms

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. Hardcoding Credentials

❌ اشتباه:
```bash
AWS_KEY="AKIA..."  - خطرناک
```

✅ درست:
```bash
aws configure  - امنیت
```

### ₂. نادیده گرفتن Cost

❌ اشتباه:
```bash
# بدون کنترل cost
```

✅ درست:
```bash
# تنظیم cost alarms و limits
```

### ۳. عدم Backup

❌ اشتباه:
```bash
# بدون backup
```

✅ درست:
```bash
# backup خودکار تنظیم کنید
```

## بهترین شیوه‌ها (Best Practices)

1. **از IAM roles استفاده کنید** - به جای credentials
2. **cost را کنترل کنید** - alarms و limits
3. **backup داشته باشید** - برای داده‌های مهم
4. **security groups را محدود کنید** - فقط پورت‌های لازم
5. **monitoring داشته باشید** - برای مشکلات

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه Cloud
- AWS CLI
- Azure CLI
- GCP CLI
- مدیریت Cloud با Bash
- عملیات S3
- امنیت Cloud
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): بررسی Cloud

یک اسکریپت بنویسید که:
1. اتصال به AWS، Azure، GCP را بررسی کند
2. لیست resources را نمایش دهد
3. گزارش وضعیت تولید کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): مدیریت S3

یک اسکریپت بنویسید که:
1. یک S3 bucket ایجاد کند
2. فایل آپلود کند
3. لیست فایل‌ها را نمایش دهد
4. cleanup انجام دهد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Docker Basics را یاد می‌گیریم.
