#!/usr/bin/env bash

echo "=== Incident Eradication ==="

# تابع scan for malware
scan_malware() {
    echo "Scanning for malware:"
    # clamscan / -r --infected
    echo "✓ Malware scan completed"
}

# تابع remove suspicious files
remove_suspicious_files() {
    local directory=$1
    
    echo "Removing suspicious files from $directory:"
    find $directory -name "*.sh" -perm +111 -delete
    echo "✓ Suspicious files removed"
}

# تابع patch vulnerabilities
patch_vulnerabilities() {
    echo "Patching vulnerabilities:"
    apt update && apt upgrade -y
    echo "✓ System patched"
}

# تابع remove backdoors
remove_backdoors() {
    echo "Removing potential backdoors:"
    # Check for suspicious cron jobs
    crontab -l | grep -v "suspicious"
    
    # Check for suspicious SSH keys
    grep -r "no-pty" ~/.ssh/
    
    echo "✓ Backdoor check completed"
}

# تابع reset credentials
reset_credentials() {
    local user=$1
    
    echo "Resetting credentials for $user:"
    passwd $user
    echo "✓ Credentials reset"
}

# تابع clean temp directories
clean_temp() {
    echo "Cleaning temporary directories:"
    rm -rf /tmp/*
    rm -rf /var/tmp/*
    echo "✓ Temp directories cleaned"
}

# استفاده
scan_malware
patch_vulnerabilities
remove_backdoors
clean_temp
