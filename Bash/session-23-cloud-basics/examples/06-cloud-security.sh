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
