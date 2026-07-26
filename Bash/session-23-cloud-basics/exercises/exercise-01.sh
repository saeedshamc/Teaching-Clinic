#!/usr/bin/env bash

# تمرین ۱: بررسی Cloud
# هدف: یک اسکریپت بنویسید که:
# 1. اتصال به AWS، Azure، GCP را بررسی کند
# 2. لیست resources را نمایش دهد
# 3. گزارش وضعیت تولید کند

# راه‌حل:
echo "=== بررسی اتصال Cloud ==="
REPORT_FILE="/tmp/cloud_report.txt"

# خالی کردن فایل گزارش
> "$REPORT_FILE"

# 1. بررسی AWS
echo "1. بررسی AWS:" | tee -a "$REPORT_FILE"
if command -v aws &>/dev/null; then
    if aws sts get-caller-identity &>/dev/null; then
        echo "✓ AWS CLI نصب و متصل است" | tee -a "$REPORT_FILE"
        echo "Account: $(aws sts get-caller-identity --query Account --output text 2>/dev/null)" | tee -a "$REPORT_FILE"
        echo "Region: $(aws configure get region 2>/dev/null || echo 'تنظیم نشده')" | tee -a "$REPORT_FILE"
    else
        echo "✗ AWS CLI نصب است اما متصل نیست" | tee -a "$REPORT_FILE"
    fi
else
    echo "✗ AWS CLI نصب نیست" | tee -a "$REPORT_FILE"
fi

# 2. بررسی Azure
echo -e "\n2. بررسی Azure:" | tee -a "$REPORT_FILE"
if command -v az &>/dev/null; then
    if az account show &>/dev/null; then
        echo "✓ Azure CLI نصب و متصل است" | tee -a "$REPORT_FILE"
        echo "Subscription: $(az account show --query name -o tsv 2>/dev/null)" | tee -a "$REPORT_FILE"
    else
        echo "✗ Azure CLI نصب است اما متصل نیست" | tee -a "$REPORT_FILE"
    fi
else
    echo "✗ Azure CLI نصب نیست" | tee -a "$REPORT_FILE"
fi

# 3. بررسی GCP
echo -e "\n3. بررسی GCP:" | tee -a "$REPORT_FILE"
if command -v gcloud &>/dev/null; then
    ACTIVE_ACCOUNT=$(gcloud auth list --filter=status:ACTIVE --format="value(account)" 2>/dev/null)
    if [ -n "$ACTIVE_ACCOUNT" ]; then
        echo "✓ gcloud نصب و متصل است" | tee -a "$REPORT_FILE"
        echo "Account: $ACTIVE_ACCOUNT" | tee -a "$REPORT_FILE"
        echo "Project: $(gcloud config get project 2>/dev/null || echo 'تنظیم نشده')" | tee -a "$REPORT_FILE"
    else
        echo "✗ gcloud نصب است اما متصل نیست" | tee -a "$REPORT_FILE"
    fi
else
    echo "✗ gcloud نصب نیست" | tee -a "$REPORT_FILE"
fi

echo -e "\nگزارش در $REPORT_FILE ذخیره شد"
cat "$REPORT_FILE"

# پاکسازی
rm "$REPORT_FILE"
