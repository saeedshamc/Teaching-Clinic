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
