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
