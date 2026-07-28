#!/usr/bin/env bash

echo "=== Audit Procedures ==="

# تابع system audit
system_audit() {
    echo "System Audit:"
    echo "1. Check OS version"
    uname -a
    echo "2. Check installed packages"
    dpkg -l | head -20
    echo "3. Check running services"
    systemctl list-units --type=service --state=running
}

# تابع access audit
access_audit() {
    echo -e "\nAccess Audit:"
    echo "1. List users"
    cut -d: -f1 /etc/passwd
    echo "2. Check sudo access"
    grep -E '^sudo' /etc/group
    echo "3. Check failed logins"
    grep "Failed" /var/log/auth.log | tail -10
}

# تابع configuration audit
config_audit() {
    echo -e "\nConfiguration Audit:"
    echo "1. Check SSH config"
    cat /etc/ssh/sshd_config | grep -E "PermitRootLogin|PasswordAuthentication"
    echo "2. Check firewall rules"
    iptables -L -n
    echo "3. Check file permissions"
    find /etc -perm 777 -type f
}

# تابع log audit
log_audit() {
    echo -e "\nLog Audit:"
    echo "1. Check auth logs"
    tail -20 /var/log/auth.log
    echo "2. Check system logs"
    tail -20 /var/log/syslog
    echo "3. Check kernel logs"
    dmesg | tail -20
}

# استفاده
system_audit
access_audit
config_audit
log_audit
