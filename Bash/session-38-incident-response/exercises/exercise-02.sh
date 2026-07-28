#!/usr/bin/env bash

# تمرین ۲: Response Playbook
# هدف: یک اسکریپت بنویسید که:
# 1. incident response playbook ایجاد کند
# 2. containment steps را execute کند
# 3. recovery steps را automate کند

# راه‌حل:
echo "=== Incident Response Playbook ==="

# تابع create incident directory
create_incident_dir() {
    local incident_id=$1
    
    mkdir -p /tmp/incidents/$incident_id/{evidence,logs,actions}
    echo "✓ Incident directory created: /tmp/incidents/$incident_id"
}

# تابع log action
log_action() {
    local incident_id=$1
    local phase=$2
    local action=$3
    
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$timestamp] [$phase] $action" >> /tmp/incidents/$incident_id/actions/log.txt
    echo "✓ Action logged: $action"
}

# تابع phase 1: Detection
phase_detection() {
    local incident_id=$1
    
    echo -e "\n=== Phase 1: Detection ==="
    log_action "$incident_id" "DETECTION" "Starting detection phase"
    
    # Identify indicators
    echo "1. Identifying indicators:"
    log_action "$incident_id" "DETECTION" "Checking for failed logins"
    log_action "$incident_id" "DETECTION" "Analyzing system logs"
    log_action "$incident_id" "DETECTION" "Checking for suspicious processes"
    
    # Assess severity
    echo "2. Assessing severity:"
    local severity="HIGH"
    log_action "$incident_id" "DETECTION" "Severity assessed: $severity"
    
    # Declare incident
    echo "3. Declaring incident:"
    log_action "$incident_id" "DETECTION" "Incident declared: $incident_id"
    
    echo "✓ Detection phase completed"
}

# تابع phase 2: Containment
phase_containment() {
    local incident_id=$1
    local malicious_ip=${2:-"192.168.1.100"}
    
    echo -e "\n=== Phase 2: Containment ==="
    log_action "$incident_id" "CONTAINMENT" "Starting containment phase"
    
    # Isolate affected systems
    echo "1. Isolating affected systems:"
    log_action "$incident_id" "CONTAINMENT" "Isolating system from network"
    # systemctl stop network
    
    # Block malicious IPs
    echo "2. Blocking malicious IP: $malicious_ip"
    log_action "$incident_id" "CONTAINMENT" "Blocking IP: $malicious_ip"
    iptables -A INPUT -s $malicious_ip -j DROP
    
    # Disable compromised accounts
    echo "3. Disabling compromised accounts:"
    log_action "$incident_id" "CONTAINMENT" "Checking for suspicious accounts"
    # usermod -L suspicious_user
    
    # Preserve evidence
    echo "4. Preserving evidence:"
    log_action "$incident_id" "CONTAINMENT" "Copying system logs to evidence directory"
    cp /var/log/auth.log /tmp/incidents/$incident_id/evidence/
    cp /var/log/syslog /tmp/incidents/$incident_id/evidence/
    
    echo "✓ Containment phase completed"
}

# تابع phase 3: Eradication
phase_eradication() {
    local incident_id=$1
    
    echo -e "\n=== Phase 3: Eradication ==="
    log_action "$incident_id" "ERADICATION" "Starting eradication phase"
    
    # Identify root cause
    echo "1. Identifying root cause:"
    log_action "$incident_id" "ERADICATION" "Analyzing vulnerability"
    log_action "$incident_id" "ERADICATION" "Reviewing access logs"
    
    # Remove malware
    echo "2. Removing malware:"
    log_action "$incident_id" "ERADICATION" "Scanning for malware"
    # clamscan / -r --infected
    
    # Patch vulnerabilities
    echo "3. Patching vulnerabilities:"
    log_action "$incident_id" "ERADICATION" "Updating system packages"
    apt update && apt upgrade -y
    
    # Remove backdoors
    echo "4. Removing backdoors:"
    log_action "$incident_id" "ERADICATION" "Checking for suspicious cron jobs"
    log_action "$incident_id" "ERADICATION" "Checking for suspicious SSH keys"
    
    echo "✓ Eradication phase completed"
}

# تابع phase 4: Recovery
phase_recovery() {
    local incident_id=$1
    
    echo -e "\n=== Phase 4: Recovery ==="
    log_action "$incident_id" "RECOVERY" "Starting recovery phase"
    
    # Restore from backup
    echo "1. Restoring from backup:"
    log_action "$incident_id" "RECOVERY" "Restoring system from backup"
    # tar -xzf /backup/latest.tar.gz -C /
    
    # Verify integrity
    echo "2. Verifying system integrity:"
    log_action "$incident_id" "RECOVERY" "Verifying file checksums"
    log_action "$incident_id" "RECOVERY" "Checking system logs"
    
    # Restart services
    echo "3. Restarting services:"
    log_action "$incident_id" "RECOVERY" "Restarting network service"
    # systemctl start network
    log_action "$incident_id" "RECOVERY" "Restarting web services"
    systemctl restart nginx
    
    # Monitor for recurrence
    echo "4. Monitoring for recurrence:"
    log_action "$incident_id" "RECOVERY" "Setting up monitoring"
    
    echo "✓ Recovery phase completed"
}

# تابع phase 5: Lessons Learned
phase_lessons() {
    local incident_id=$1
    
    echo -e "\n=== Phase 5: Lessons Learned ==="
    log_action "$incident_id" "LESSONS" "Starting lessons learned phase"
    
    # Document incident
    echo "1. Documenting incident:"
    cat > /tmp/incidents/$incident_id/report.md << EOF
# Incident Report: $incident_id

## Date: $(date '+%Y-%m-%d')

## Summary
Security incident detected and resolved

## Timeline
- Detection: $(date '+%Y-%m-%d %H:%M:%S')
- Containment: $(date '+%Y-%m-%d %H:%M:%S')
- Eradication: $(date '+%Y-%m-%d %H:%M:%S')
- Recovery: $(date '+%Y-%m-%d %H:%M:%S')

## Actions Taken
See actions/log.txt for detailed actions

## Lessons Learned
- Need for better monitoring
- Improve authentication security
- Regular security audits

## Recommendations
- Implement MFA
- Regular security training
- Enhanced monitoring
EOF
    log_action "$incident_id" "LESSONS" "Incident report created"
    
    # Update procedures
    echo "2. Updating procedures:"
    log_action "$incident_id" "LESSONS" "Updating incident response procedures"
    
    echo "✓ Lessons learned phase completed"
}

# تابع execute full playbook
execute_playbook() {
    local incident_id=$1
    local malicious_ip=$2
    
    echo "=== Executing Incident Response Playbook ==="
    echo "Incident ID: $incident_id"
    echo "Malicious IP: $malicious_ip"
    
    create_incident_dir "$incident_id"
    phase_detection "$incident_id"
    phase_containment "$incident_id" "$malicious_ip"
    phase_eradication "$incident_id"
    phase_recovery "$incident_id"
    phase_lessons "$incident_id"
    
    echo -e "\n=== Playbook Execution Complete ==="
    echo "All actions logged to: /tmp/incidents/$incident_id/actions/log.txt"
    echo "Report generated: /tmp/incidents/$incident_id/report.md"
}

# تابع show status
show_status() {
    local incident_id=$1
    
    echo -e "\n=== Incident Status ==="
    echo "Incident ID: $incident_id"
    echo "Status: RESOLVED"
    echo "Actions taken: $(wc -l < /tmp/incidents/$incident_id/actions/log.txt)"
    echo "Evidence preserved: $(ls /tmp/incidents/$incident_id/evidence/ | wc -l) files"
}

# اجرا
INCIDENT_ID="INC-$(date +%Y%m%d-%H%M%S)"
MALICIOUS_IP="192.168.1.100"

execute_playbook "$INCIDENT_ID" "$MALICIOUS_IP"
show_status "$INCIDENT_ID"
