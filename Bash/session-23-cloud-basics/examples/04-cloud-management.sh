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
