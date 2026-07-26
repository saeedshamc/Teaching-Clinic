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
