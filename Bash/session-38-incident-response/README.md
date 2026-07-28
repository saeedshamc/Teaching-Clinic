# جلسه ۳۸: پاسخ به حوادث (Incident Response)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه incident response
- Incident detection
- Incident containment
- Incident eradication
- Incident recovery
- Post-incident analysis
- مثال‌های عملی

## مفاهیم پایه Incident Response

Incident Response فرآیند مدیریت security incidents است:
- **Detection** - شناسایی incident
- **Containment** - محدود کردن incident
- **Eradication** - حذف threat
- **Recovery** - بازیابی سیستم
- **Lessons Learned** - یادگیری از incident

## Incident Detection

Detection شناسایی security incidents است.

```bash
# Monitor logs
# Alert on anomalies
- Detect suspicious activity
```

## مثال ۱: Incident Detection

فایل [examples/01-incident-detection.sh](examples/01-incident-detection.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Incident Detection ==="

# تابع monitor auth logs
monitor_auth_logs() {
    echo "Monitoring authentication logs:"
    tail -f /var/log/auth.log | grep --line-buffered -i "failed\|invalid"
}

# تابع detect failed logins
detect_failed_logins() {
    echo "Detecting failed login attempts:"
    grep "Failed password" /var/log/auth.log | awk '{print $1, $2, $3, $11, $13}' | sort | uniq -c | sort -rn
}

# تابع detect suspicious IPs
detect_suspicious_ips() {
    echo "Detecting suspicious IPs:"
    grep "Failed password" /var/log/auth.log | awk '{print $11}' | tr -d ')' | sort | uniq -c | sort -rn | head -10
}

# تابع detect port scanning
detect_port_scan() {
    echo "Detecting port scanning attempts:"
    grep "port scan" /var/log/syslog | tail -20
}

# تابع detect file changes
detect_file_changes() {
    echo "Detecting file changes:"
    find /etc -type f -mtime -1 -ls
}

# تابع alert on anomaly
alert_anomaly() {
    local threshold=$1
    
    local failed_count=$(grep "Failed password" /var/log/auth.log | wc -l)
    
    if [ $failed_count -gt $threshold ]; then
        echo "ALERT: High number of failed logins: $failed_count"
        # Send alert notification
    fi
}

# استفاده
detect_failed_logins
detect_suspicious_ips
alert_anomaly 50
```

### توضیح خط به خط:

- `tail -f` - monitor logs in real-time
- `grep` - search for patterns
- `awk` - extract specific fields
- `sort | uniq -c` - count occurrences
- threshold-based alerting

## Incident Containment

Containment محدود کردن spread of incident است.

```bash
# Isolate affected systems
# Block malicious IPs
- Disable compromised accounts
```

## مثال ۲: Incident Containment

فایل [examples/02-incident-containment.sh](examples/02-incident-containment.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Incident Containment ==="

# تابع block IP
block_ip() {
    local ip=$1
    
    echo "Blocking IP $ip:"
    iptables -A INPUT -s $ip -j DROP
    echo "✓ IP blocked"
}

# تابع isolate system
isolate_system() {
    echo "Isolating system from network:"
    # Disconnect from network
    # Disable network interfaces
    echo "✓ System isolated"
}

# تابع disable account
disable_account() {
    local user=$1
    
    echo "Disabling account $user:"
    usermod -L $user
    echo "✓ Account disabled"
}

# تابع kill suspicious processes
kill_suspicious_process() {
    local pid=$1
    
    echo "Killing process $pid:"
    kill -9 $pid
    echo "✓ Process killed"
}

# تابع stop affected service
stop_service() {
    local service=$1
    
    echo "Stopping service $service:"
    systemctl stop $service
    echo "✓ Service stopped"
}

# تابع quarantine file
quarantine_file() {
    local file=$1
    
    echo "Quarantining file $file:"
    mv $file /tmp/quarantine/
    chmod 000 /tmp/quarantine/$(basename $file)
    echo "✓ File quarantined"
}

# استفاده
# block_ip "192.168.1.100"
# disable_account "hacker"
# stop_service "nginx"
```

### توضیح خط به خط:

- `iptables DROP` - block IP
- `usermod -L` - lock account
- `kill -9` - force kill process
- `systemctl stop` - stop service
- quarantine suspicious files

## Incident Eradication

Eradication حذف root cause of incident است.

```bash
# Remove malware
- Patch vulnerabilities
# Remove backdoors
```

## مثال ۳: Incident Eradication

فایل [examples/03-incident-eradication.sh](examples/03-incident-eradication.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- malware scanning با tools
- remove suspicious executables
- system patching
- backdoor detection
- credential reset
- temp directory cleanup

## Incident Recovery

Recovery بازیابی systems به normal operation است.

```bash
# Restore from backup
# Verify system integrity
- Monitor for recurrence
```

## مثال ۴: Incident Recovery

فایل [examples/04-incident-recovery.sh](examples/04-incident-recovery.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Incident Recovery ==="

# تابع restore from backup
restore_backup() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Restoring from $backup_file to $restore_dir:"
    tar -xzf $backup_file -C $restore_dir
    echo "✓ Backup restored"
}

# تابع verify system integrity
verify_integrity() {
    echo "Verifying system integrity:"
    
    # Check file checksums
    # Verify critical files
    # Check system logs
    
    echo "✓ Integrity verified"
}

# تابع restart services
restart_services() {
    echo "Restarting services:"
    systemctl restart nginx
    systemctl restart mysql
    systemctl restart sshd
    echo "✓ Services restarted"
}

# تابع re-enable accounts
reenable_account() {
    local user=$1
    
    echo "Re-enabling account $user:"
    usermod -U $user
    echo "✓ Account re-enabled"
}

# تابع unblock IP
unblock_ip() {
    local ip=$1
    
    echo "Unblocking IP $ip:"
    iptables -D INPUT -s $ip -j DROP
    echo "✓ IP unblocked"
}

# تابع monitor recurrence
monitor_recurrence() {
    echo "Monitoring for incident recurrence:"
    # Set up monitoring
    # Configure alerts
    echo "✓ Monitoring configured"
}

# استفاده
# restore_backup "/backup/latest.tar.gz" "/"
# verify_integrity
# restart_services
```

### توضیح خط به خط:

- restore از backup
- integrity verification
- service restart
- account re-enablement
- IP unblocking
- monitoring setup

## Post-Incident Analysis

Analysis برای learning از incidents است.

```bash
# Document incident
- Analyze root cause
# Improve procedures
```

## مثال ۵: Post-Incident Analysis

فایل [examples/05-post-incident-analysis.sh](examples/05-post-incident-analysis.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Post-Incident Analysis ==="

# تابع create incident report
create_report() {
    local incident_id=$1
    local date=$(date '+%Y-%m-%d')
    
    cat > /tmp/incident-$incident_id.md << EOF
# Incident Report: $incident_id

## Date: $date

## Summary
Brief description of the incident

## Timeline
- Detection time:
- Containment time:
- Eradication time:
- Recovery time:

## Impact
- Systems affected:
- Data compromised:
- Downtime:

## Root Cause
Analysis of what caused the incident

## Actions Taken
List of actions taken during response

## Lessons Learned
What was learned from this incident

## Recommendations
Recommendations to prevent recurrence
EOF
    
    echo "✓ Incident report created"
}

# تابع analyze logs
analyze_logs() {
    local start_date=$1
    local end_date=$2
    
    echo "Analyzing logs from $start_date to $end_date:"
    # Extract relevant log entries
    # Analyze patterns
    # Identify indicators
    echo "✓ Log analysis completed"
}

# تابع identify root cause
identify_root_cause() {
    echo "Identifying root cause:"
    
    # Analyze vulnerability
    # Review access logs
    # Check configuration
    
    echo "✓ Root cause identified"
}

# تابع update procedures
update_procedures() {
    echo "Updating incident response procedures:"
    
    # Update playbooks
    # Add new detection rules
    # Update documentation
    
    echo "✓ Procedures updated"
}

# تابع schedule review
schedule_review() {
    echo "Scheduling post-incident review:"
    echo "Review meeting scheduled with stakeholders"
    echo "✓ Review scheduled"
}

# استفاده
create_report "INC-2024-001"
analyze_logs "2024-01-01" "2024-01-02"
identify_root_cause
update_procedures
```

### توضیح خط به خط:

- incident report creation
- log analysis
- root cause identification
- procedure updates
- review scheduling

## Incident Response Playbook

Playbook step-by-step response procedures است.

```bash
# Define response steps
# Assign responsibilities
- Document procedures
```

## مثال ۶: Incident Response Playbook

فایل [examples/06-ir-playbook.sh](examples/06-ir-playbook.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Incident Response Playbook ==="

# تابع create playbook
create_playbook() {
    local incident_type=$1
    
    cat > /tmp/playbook-$incident_type.md << 'EOF'
# Incident Response Playbook

## Phase 1: Detection
1. Monitor alerts
2. Identify indicators
3. Assess severity
4. Declare incident

## Phase 2: Containment
1. Isolate affected systems
2. Block malicious IPs
3. Disable compromised accounts
4. Preserve evidence

## Phase 3: Eradication
1. Identify root cause
2. Remove malware
3. Patch vulnerabilities
4. Remove backdoors

## Phase 4: Recovery
1. Restore from backup
2. Verify integrity
3. Restart services
4. Monitor for recurrence

## Phase 5: Lessons Learned
1. Document incident
2. Analyze root cause
3. Update procedures
4. Share knowledge
EOF
    
    echo "✓ Playbook created for $incident_type"
}

# تابع execute playbook step
execute_step() {
    local phase=$1
    local step=$2
    
    echo "Executing $phase - Step $step:"
    # Execute predefined action
    echo "✓ Step completed"
}

# تابع assign responsibility
assign_responsibility() {
    local role=$1
    local task=$2
    
    echo "Assigning $task to $role"
    echo "✓ Responsibility assigned"
}

# تابع track progress
track_progress() {
    local incident_id=$1
    
    echo "Tracking progress for $incident_id:"
    # Update status
    # Log actions
    # Report progress
    echo "✓ Progress tracked"
}

# استفاده
create_playbook "ransomware"
execute_step "Containment" "1"
assign_responsibility "Security Team" "IP Blocking"
track_progress "INC-2024-001"
```

### توضیح خط به خط:

- playbook creation
- step-by-step execution
- responsibility assignment
- progress tracking
- structured response

## نکات مهم

### ۱. Documentation

```bash
# Document everything
- Timestamp all actions
# Preserve evidence
```

### ۲. Communication

```bash
# Notify stakeholders
- Regular updates
# Clear communication
```

### ۳. Testing

```bash
# Test response procedures
- Regular drills
- Update based on lessons
```

## مثال ۷: IR Best Practices

فایل [examples/07-ir-best-practices.sh](examples/07-ir-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Incident Response Best Practices ==="

# 1. Preparation
echo -e "\n1. Preparation:"
echo "✓ Have incident response plan ready"
echo "✓ Define roles and responsibilities"
echo "✓ Establish communication channels"

# 2. Detection
echo -e "\n2. Detection:"
echo "✓ Monitor logs and alerts"
echo "✓ Use SIEM for correlation"
echo "✓ Set up automated detection"

# 3. Response
echo -e "\n3. Response:"
echo "✓ Follow playbook procedures"
echo "✓ Document all actions"
echo "✓ Preserve evidence"

# 4. Recovery
echo -e "\n4. Recovery:"
echo "✓ Verify before restore"
echo "✓ Monitor for recurrence"
echo "✓ Update security controls"

# 5. Learning
echo -e "\n5. Learning:"
echo "✓ Conduct post-incident review"
echo "✓ Update procedures"
echo "✓ Share lessons learned"
```

### توضیح خط به خط:

- Preparation برای incidents
- Detection capabilities
- Response procedures
- Recovery verification
- Continuous improvement

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Documentation

❌ اشتباه:
```bash
# بدون documentation
```

✅ درست:
```bash
# document همه actions
```

### ۲: بدون Evidence Preservation

❌ اشتباه:
```bash
# بدون preserve evidence
```

✅ درست:
```bash
# preserve evidence
```

### ۳: بدون Communication

❌ اشتباه:
```bash
# بدون communication
```

✅ درست:
```bash
# regular updates
```

## بهترین شیوه‌ها (Best Practices)

1. **Preparation** - plan ready
2. **Detection** - monitoring و alerts
3. **Response** - follow playbook
4. **Recovery** - verify و monitor
5. **Learning** - lessons learned

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه incident response
- Incident detection
- Incident containment
- Incident eradication
- Incident recovery
- Post-incident analysis
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Detection Script

یک اسکریپت بنویسید که:
1. auth logs را monitor کند
2. failed logins را detect کند
3. alert بفرستد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Response Playbook

یک اسکریپت بنویسید که:
1. incident response playbook ایجاد کند
2. containment steps را execute کند
3. recovery steps را automate کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Disaster Recovery را یاد می‌گیریم.
