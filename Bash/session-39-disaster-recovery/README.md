# جلسه ۳۹: بازیابی از حوادث (Disaster Recovery)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه disaster recovery
- Backup strategies
- Recovery procedures
- Business continuity
- DR planning
- Testing DR plans
- مثال‌های عملی

## مفاهیم پایه Disaster Recovery

Disaster Recovery فرآیند بازیابی از disasters است:
- **RPO** - Recovery Point Objective
- **RTO** - Recovery Time Objective
- **Backup** - copy of data
- **Restore** - recover from backup
- **Failover** - switch to backup system

## RPO و RTO

RPO و RTO metrics برای DR planning هستند.

```bash
# RPO: Maximum acceptable data loss
# RTO: Maximum acceptable downtime
```

## مثال ۱: RPO RTO Calculation

فایل [examples/01-rpo-rto.sh](examples/01-rpo-rto.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== RPO and RTO ==="

# تابع calculate RPO
calculate_rpo() {
    local backup_frequency=$1
    local acceptable_loss=$2
    
    echo "RPO Calculation:"
    echo "Backup frequency: $backup_frequency"
    echo "Acceptable data loss: $acceptable_loss"
    
    if [ "$backup_frequency" -le "$acceptable_loss" ]; then
        echo "✓ RPO met: $backup_frequency hours"
    else
        echo "✗ RPO not met: Need more frequent backups"
    fi
}

# تابع calculate RTO
calculate_rto() {
    local recovery_time=$1
    local acceptable_downtime=$2
    
    echo -e "\nRTO Calculation:"
    echo "Recovery time: $recovery_time hours"
    echo "Acceptable downtime: $acceptable_downtime hours"
    
    if [ "$recovery_time" -le "$acceptable_downtime" ]; then
        echo "✓ RTO met: $recovery_time hours"
    else
        echo "✗ RTO not met: Need faster recovery"
    fi
}

# تابع define SLA
define_sla() {
    local service=$1
    local rpo=$2
    local rto=$3
    
    echo -e "\nSLA for $service:"
    echo "RPO: $rpo hours"
    echo "RTO: $rto hours"
}

# استفاده
calculate_rpo 1 4
calculate_rto 2 4
define_sla "Database" 1 2
```

### توضیح خط به خط:

- RPO - maximum data loss tolerance
- RTO - maximum downtime tolerance
- SLA definition
- metrics comparison
- compliance checking

## Backup Strategies

Backup strategies برای data protection هستند.

```bash
# Full backup
- Incremental backup
# Differential backup
```

## مثال ۲: Backup Strategies

فایل [examples/02-backup-strategies.sh](examples/02-backup-strategies.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Strategies ==="

# تابع full backup
full_backup() {
    local source=$1
    local destination=$2
    
    echo "Creating full backup of $source:"
    tar -czf $destination/full-$(date +%Y%m%d).tar.gz $source
    echo "✓ Full backup created"
}

# تابع incremental backup
incremental_backup() {
    local source=$1
    local destination=$2
    local last_backup=$3
    
    echo "Creating incremental backup:"
    find $source -newer $last_backup -print0 | \
        tar -czf $destination/inc-$(date +%Y%m%d).tar.gz --null -T -
    echo "✓ Incremental backup created"
}

# تابع differential backup
differential_backup() {
    local source=$1
    local destination=$2
    local full_backup=$3
    
    echo "Creating differential backup:"
    find $source -newer $full_backup -print0 | \
        tar -czf $destination/diff-$(date +%Y%m%d).tar.gz --null -T -
    echo "✓ Differential backup created"
}

# تابع backup rotation
backup_rotation() {
    local backup_dir=$1
    local keep_days=$2
    
    echo "Rotating backups (keep $keep_days days):"
    find $backup_dir -name "*.tar.gz" -mtime +$keep_days -delete
    echo "✓ Old backups removed"
}

# تابع verify backup
verify_backup() {
    local backup_file=$1
    
    echo "Verifying backup $backup_file:"
    tar -tzf $backup_file > /dev/null
    if [ $? -eq 0 ]; then
        echo "✓ Backup is valid"
    else
        echo "✗ Backup is corrupted"
    fi
}

# استفاده
# full_backup "/var/www" "/backup"
# backup_rotation "/backup" 7
# verify_backup "/backup/full-20240128.tar.gz"
```

### توضیح خط به خط:

- full backup - complete copy
- incremental - changes since last
- differential - changes since full
- rotation policy
- backup verification

## Recovery Procedures

Recovery procedures برای restoring systems هستند.

```bash
# Restore from backup
- Verify integrity
# Test functionality
```

## مثال ۳: Recovery Procedures

فایل [examples/03-recovery-procedures.sh](examples/03-recovery-procedures.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Recovery Procedures ==="

# تابع restore from backup
restore_backup() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Restoring from $backup_file to $restore_dir:"
    tar -xzf $backup_file -C $restore_dir
    echo "✓ Backup restored"
}

# تابع restore database
restore_database() {
    local backup_file=$1
    local database=$2
    
    echo "Restoring database $database:"
    mysql $database < $backup_file
    echo "✓ Database restored"
}

# تابع verify restore
verify_restore() {
    local restore_dir=$1
    
    echo "Verifying restore:"
    # Check file integrity
    # Verify file counts
    # Check critical files
    echo "✓ Restore verified"
}

# تابع test functionality
test_functionality() {
    echo "Testing functionality:"
    # Test services
    # Test connectivity
    # Test applications
    echo "✓ Functionality tested"
}

# تابع rollback
rollback() {
    local previous_backup=$1
    
    echo "Rolling back to $previous_backup:"
    # Restore previous backup
    echo "✓ Rollback completed"
}

# استفاده
# restore_backup "/backup/full-20240128.tar.gz" "/var/www"
# verify_restore "/var/www"
# test_functionality
```

### توضیح خط به خط:

- restore from tar archive
- database restore
- integrity verification
- functionality testing
- rollback procedure

## Business Continuity

Business continuity برای maintaining operations است.

```bash
# Failover procedures
- Redundant systems
# Load balancing
```

## مثال ۴: Business Continuity

فایل [examples/04-business-continuity.sh](examples/04-business-continuity.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Business Continuity ==="

# تابع check primary system
check_primary() {
    local host=$1
    
    echo "Checking primary system $host:"
    ping -c 3 $host > /dev/null
    if [ $? -eq 0 ]; then
        echo "✓ Primary system is up"
        return 0
    else
        echo "✗ Primary system is down"
        return 1
    fi
}

# تابع initiate failover
initiate_failover() {
    local secondary_host=$1
    
    echo "Initiating failover to $secondary_host:"
    # Update DNS
    # Start services on secondary
    # Redirect traffic
    echo "✓ Failover completed"
}

# تابع check secondary
check_secondary() {
    local host=$1
    
    echo "Checking secondary system $host:"
    ping -c 3 $host > /dev/null
    if [ $? -eq 0 ]; then
        echo "✓ Secondary system is up"
        return 0
    else
        echo "✗ Secondary system is down"
        return 1
    fi
}

# تابع sync data
sync_data() {
    local primary=$1
    local secondary=$2
    
    echo "Syncing data from $primary to $secondary:"
    rsync -avz $primary/ $secondary/
    echo "✓ Data synced"
}

# تابع failback
failback() {
    local primary=$1
    
    echo "Failing back to primary $primary:"
    # Sync data back
    # Update DNS
    # Stop secondary services
    echo "✓ Failback completed"
}

# استفاده
# check_primary "primary.example.com"
# initiate_failover "secondary.example.com"
# sync_data "/data/primary" "/data/secondary"
```

### توضیح خط به خط:

- primary system health check
- failover initiation
- secondary system check
- data synchronization
- failback procedure

## DR Planning

DR planning برای preparing برای disasters است.

```bash
# Risk assessment
- Impact analysis
# Recovery procedures
```

## مثال ۵: DR Planning

فایل [examples/05-dr-planning.sh](examples/05-dr-planning.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Disaster Recovery Planning ==="

# تابع risk assessment
risk_assessment() {
    echo "Risk Assessment:"
    echo "1. Natural disasters (flood, fire, earthquake)"
    echo "2. Hardware failures (server, storage, network)"
    echo "3. Software failures (OS, application, database)"
    echo "4. Security incidents (malware, breach, ransomware)"
    echo "5. Human errors (deletion, misconfiguration)"
}

# تابع impact analysis
impact_analysis() {
    echo -e "\nImpact Analysis:"
    echo "Critical systems:"
    echo "- Database server: RPO 1h, RTO 2h"
    echo "- Web server: RPO 4h, RTO 4h"
    echo "- Application server: RPO 4h, RTO 8h"
}

# تابع create DR plan
create_dr_plan() {
    cat > /tmp/dr-plan.md << 'EOF'
# Disaster Recovery Plan

## 1. Prevention
- Regular backups
- Redundant systems
- Security measures

## 2. Detection
- Monitoring alerts
- Health checks
- Automated detection

## 3. Response
- Incident declaration
- Team notification
- Procedure execution

## 4. Recovery
- System restore
- Data recovery
- Service restart

## 5. Validation
- System testing
- Data verification
- Performance check
EOF
    
    echo "✓ DR plan created"
}

# تابع define roles
define_roles() {
    echo -e "\nRoles and Responsibilities:"
    echo "DR Manager: Overall coordination"
    echo "System Admin: System recovery"
    echo "DBA: Database recovery"
    echo "Network Admin: Network recovery"
}

# تابع communication plan
communication_plan() {
    echo -e "\nCommunication Plan:"
    echo "Stakeholders: Management, IT, Users"
    echo "Channels: Email, SMS, Phone"
    echo "Frequency: Hourly updates"
}

# استفاده
risk_assessment
impact_analysis
create_dr_plan
define_roles
communication_plan
```

### توضیح خط به خط:

- risk identification
- impact analysis
- DR plan creation
- role definition
- communication planning

## Testing DR Plans

Testing DR plans برای validation است.

```bash
# Simulation tests
- Full DR drill
# Documentation update
```

## مثال ۶: Testing DR Plans

فایل [examples/06-testing-dr-plans.sh](examples/06-testing-dr-plans.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Testing DR Plans ==="

# تابع simulation test
simulation_test() {
    local scenario=$1
    
    echo "Running simulation: $scenario"
    echo "1. Simulate disaster"
    echo "2. Execute recovery procedures"
    echo "3. Verify recovery"
    echo "4. Document results"
    echo "✓ Simulation completed"
}

# تابع full DR drill
full_dr_drill() {
    echo "Running full DR drill:"
    echo "1. Declare disaster"
    echo "2. Notify team"
    echo "3. Execute failover"
    echo "4. Verify operations"
    echo "5. Execute failback"
    echo "✓ DR drill completed"
}

# تابع backup restore test
backup_restore_test() {
    local backup_file=$1
    
    echo "Testing backup restore:"
    echo "1. Restore to test environment"
    echo "2. Verify data integrity"
    echo "3. Test functionality"
    echo "4. Document results"
    echo "✓ Backup restore tested"
}

# تابع document results
document_results() {
    local test_type=$1
    local result=$2
    
    echo "Documenting test results:"
    echo "Test: $test_type"
    echo "Result: $result"
    echo "Date: $(date)"
    echo "✓ Results documented"
}

# تابع update procedures
update_procedures() {
    echo "Updating procedures based on test results:"
    echo "1. Review test results"
    echo "2. Identify gaps"
    echo "3. Update procedures"
    echo "4. Train team"
    echo "✓ Procedures updated"
}

# استفاده
simulation_test "Server failure"
# full_dr_drill
# backup_restore_test "/backup/full-20240128.tar.gz"
document_results "Simulation" "Passed"
```

### توضیح خط به خط:

- simulation testing
- full DR drill execution
- backup restore validation
- results documentation
- procedure updates

## نکات مهم

### ۱: Regular Testing

```bash
# Test DR plans regularly
- Update based on results
# Continuous improvement
```

### ۲: Documentation

```bash
# Document everything
- Keep procedures current
# Share with team
```

### ۳: Communication

```bash
# Clear communication
- Regular updates
# Stakeholder notification
```

## مثال ۷: DR Best Practices

فایل [examples/07-dr-best-practices.sh](examples/07-dr-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Disaster Recovery Best Practices ==="

# 1. 3-2-1 Rule
echo -e "\n1. 3-2-1 Rule:"
echo "✓ 3 copies of data"
echo "✓ 2 different media types"
echo "✓ 1 offsite backup"

# 2. Regular Testing
echo -e "\n2. Regular Testing:"
echo "✓ Test backups monthly"
echo "✓ Run DR drills quarterly"
echo "✓ Update procedures annually"

# 3. Documentation
echo -e "\n3. Documentation:"
echo "✓ Document all procedures"
echo "✓ Keep documentation current"
echo "✓ Share with team"

# 4. Monitoring
echo -e "\n4. Monitoring:"
echo "✓ Monitor backup status"
echo "✓ Alert on failures"
echo "✓ Track recovery metrics"

# 5. Security
echo -e "\n5. Security:"
echo "✓ Encrypt backups"
echo "✓ Secure offsite storage"
echo "✓ Access controls"
```

### توضیح خط به خط:

- 3-2-1 backup rule
- regular testing schedule
- comprehensive documentation
- proactive monitoring
- backup security

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Testing

❌ اشتباه:
```bash
# بدون test backups
```

✅ درست:
```bash
# regular backup testing
```

### ۲: بدون Offsite Backup

❌ اشتباه:
```bash
# بدون offsite backup
```

✅ درست:
```bash
# offsite backup
```

### ۳: بدون Documentation

❌ اشتباه:
```bash
# بدون documentation
```

✅ درست:
```bash
# comprehensive documentation
```

## بهترین شیوه‌ها (Best Practices)

1. **3-2-1 Rule** - multiple backup copies
2. **Regular Testing** - validate backups
3. **Documentation** - current procedures
4. **Monitoring** - proactive alerts
5. **Security** - encrypt backups

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه disaster recovery
- RPO و RTO
- Backup strategies
- Recovery procedures
- Business continuity
- DR planning
- Testing DR plans
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Backup Script

یک اسکریپت بنویسید که:
1. full backup ایجاد کند
2. backup را verify کند
3. old backups را rotate کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): DR Plan

یک اسکریپت بنویسید که:
1. DR plan ایجاد کند
2. failover procedure را implement کند
3. DR test را execute کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Compliance Auditing را یاد می‌گیریم.
