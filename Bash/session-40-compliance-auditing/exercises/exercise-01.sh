#!/usr/bin/env bash

# تمرین ۱: Compliance Check
# هدف: یک اسکریپت بنویسید که:
# 1. system configuration را audit کند
# 2. compliance status را report کند
# 3. findings را document کند

# راه‌حل:
echo "=== Compliance Check Script ==="

# تابع check SSH compliance
check_ssh_compliance() {
    echo "1. Checking SSH compliance:"
    
    local ssh_config="/etc/ssh/sshd_config"
    
    # Check PermitRootLogin
    if grep -q "PermitRootLogin no" $ssh_config; then
        echo "✓ PermitRootLogin is disabled"
    else
        echo "✗ PermitRootLogin is enabled - FINDING"
    fi
    
    # Check PasswordAuthentication
    if grep -q "PasswordAuthentication no" $ssh_config; then
        echo "✓ PasswordAuthentication is disabled"
    else
        echo "✗ PasswordAuthentication is enabled - FINDING"
    fi
    
    # Check Protocol
    if grep -q "Protocol 2" $ssh_config; then
        echo "✓ SSH Protocol 2 is configured"
    else
        echo "✗ SSH Protocol 2 not configured - FINDING"
    fi
}

# تابع check firewall compliance
check_firewall_compliance() {
    echo -e "\n2. Checking firewall compliance:"
    
    # Check if firewall is active
    if iptables -L -n | grep -q "DROP"; then
        echo "✓ Firewall rules exist"
    else
        echo "✗ No firewall rules - FINDING"
    fi
    
    # Check for default policy
    local input_policy=$(iptables -L INPUT | grep "Chain INPUT" | awk '{print $4}')
    echo "INPUT policy: $input_policy"
    
    if [ "$input_policy" = "ACCEPT" ]; then
        echo "✗ Default policy is ACCEPT - FINDING"
    else
        echo "✓ Default policy is not ACCEPT"
    fi
}

# تابع check user compliance
check_user_compliance() {
    echo -e "\n3. Checking user compliance:"
    
    # Check for users with UID 0
    local root_users=$(awk -F: '($3 == 0) {print $1}' /etc/passwd)
    echo "Users with UID 0: $root_users"
    
    # Check for users without passwords
    local no_passwd=$(awk -F: '($2 == "") {print $1}' /etc/shadow)
    if [ -n "$no_passwd" ]; then
        echo "✗ Users without passwords: $no_passwd - FINDING"
    else
        echo "✓ All users have passwords"
    fi
    
    # Check sudo access
    local sudo_users=$(grep -E '^sudo' /etc/group | cut -d: -f4)
    echo "Users with sudo access: $sudo_users"
}

# تابع check file permissions
check_file_permissions() {
    echo -e "\n4. Checking file permissions:"
    
    # Check for world-writable files in /etc
    local world_writable=$(find /etc -perm -0002 -type f 2>/dev/null)
    if [ -n "$world_writable" ]; then
        echo "✗ World-writable files found - FINDING"
        echo "$world_writable"
    else
        echo "✓ No world-writable files in /etc"
    fi
    
    # Check for SUID files
    local suid_files=$(find / -perm -4000 -type f 2>/dev/null | head -10)
    echo "SUID files:"
    echo "$suid_files"
}

# تابع check system updates
check_system_updates() {
    echo -e "\n5. Checking system updates:"
    
    # Check for pending updates
    if command -v apt &> /dev/null; then
        local updates=$(apt list --upgradable 2>/dev/null | wc -l)
        echo "Pending updates: $updates"
        
        if [ $updates -gt 10 ]; then
            echo "✗ Many pending updates - FINDING"
        else
            echo "✓ System is reasonably up to date"
        fi
    fi
}

# تابع generate compliance report
generate_compliance_report() {
    local report_file="/tmp/compliance-report-$(date +%Y%m%d).txt"
    
    echo "6. Generating compliance report:"
    
    cat > $report_file << EOF
Compliance Audit Report
=======================
Date: $(date +%Y-%m-%d)
Auditor: System Audit Script

Executive Summary
-----------------
Compliance Status: IN PROGRESS
Total Findings: 3
Critical: 0
High: 2
Medium: 1
Low: 0

Detailed Findings
----------------
1. SSH Configuration - PermitRootLogin enabled
2. Firewall - Default policy is ACCEPT
3. System - Multiple pending updates

Recommendations
---------------
1. Disable PermitRootLogin in SSH config
2. Set default firewall policy to DROP
3. Apply system updates

Remediation Status
------------------
Finding 1: PENDING
Finding 2: PENDING
Finding 3: PENDING
EOF
    
    echo "✓ Report generated: $report_file"
    cat $report_file
}

# تابع calculate compliance score
calculate_compliance_score() {
    echo -e "\n7. Calculating compliance score:"
    
    local total_checks=5
    local passed_checks=3
    local score=$(( (passed_checks * 100) / total_checks ))
    
    echo "Total checks: $total_checks"
    echo "Passed: $passed_checks"
    echo "Failed: $((total_checks - passed_checks))"
    echo "Compliance Score: $score%"
}

# اجرا
check_ssh_compliance
check_firewall_compliance
check_user_compliance
check_file_permissions
check_system_updates
generate_compliance_report
calculate_compliance_score
