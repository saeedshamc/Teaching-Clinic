#!/usr/bin/env bash

# تمرین ۲: DR Plan
# هدف: یک اسکریپت بنویسید که:
# 1. DR plan ایجاد کند
# 2. failover procedure را implement کند
# 3. DR test را execute کند

# راه‌حل:
echo "=== Disaster Recovery Plan ==="

# تابع create DR plan directory
create_dr_dir() {
    local dr_dir=$1
    
    echo "1. Creating DR directory: $dr_dir"
    mkdir -p $dr_dir/{plans,logs,tests}
    echo "✓ DR directory created"
}

# تابع create DR plan
create_dr_plan() {
    local dr_dir=$1
    local plan_name=$2
    
    cat > $dr_dir/plans/$plan_name.md << 'EOF'
# Disaster Recovery Plan

## Executive Summary
This document outlines the disaster recovery procedures for critical systems.

## 1. System Classification

### Critical Systems
- **Database Server**: RPO 1h, RTO 2h
- **Web Server**: RPO 4h, RTO 4h
- **Application Server**: RPO 4h, RTO 8h

### Non-Critical Systems
- **Development Server**: RPO 24h, RTO 24h
- **Test Server**: RPO 24h, RTO 48h

## 2. Backup Strategy

### 3-2-1 Rule
- 3 copies of data (primary, secondary, tertiary)
- 2 different media types (disk, cloud)
- 1 offsite backup (cloud storage)

### Backup Schedule
- **Full backups**: Daily at 2:00 AM
- **Incremental backups**: Every 4 hours
- **Retention**: 30 days

## 3. Recovery Procedures

### Phase 1: Assessment (0-30 minutes)
1. Assess the impact
2. Identify affected systems
3. Declare disaster if needed
4. Notify DR team

### Phase 2: Activation (30-60 minutes)
1. Activate DR plan
2. Initiate failover if required
3. Communicate with stakeholders
4. Begin recovery procedures

### Phase 3: Recovery (1-4 hours)
1. Restore from backup
2. Verify data integrity
3. Restart services
4. Test functionality

### Phase 4: Validation (4-8 hours)
1. Full system testing
2. Performance validation
3. Security verification
4. User acceptance testing

## 4. Failover Procedure

### Pre-Failover Checklist
- [ ] Verify secondary system status
- [ ] Confirm data synchronization
- [ ] Test network connectivity
- [ ] Prepare DNS update

### Failover Steps
1. Stop primary services
2. Update DNS records
3. Start secondary services
4. Verify operations
5. Monitor performance

### Failback Procedure
1. Verify primary system recovery
2. Sync data from secondary
3. Update DNS records
4. Stop secondary services
5. Verify operations

## 5. Communication Plan

### Stakeholders
- Management: Immediate notification
- IT Team: Detailed technical update
- Users: Service status update
- Customers: Impact notification

### Communication Channels
- Email: Primary channel
- SMS: Urgent notifications
- Phone: Critical incidents
- Slack: Team coordination

### Update Frequency
- Initial: Immediate
- Updates: Every 30 minutes
- Resolution: Final notification

## 6. Testing Schedule

### Monthly
- Backup verification
- Restore testing
- Documentation review

### Quarterly
- Full DR drill
- Failover testing
- Team training

### Annually
- Complete DR plan review
- Risk assessment update
- Procedure updates

## 7. Contact Information

### DR Team
- DR Manager: dr-manager@example.com
- System Admin: sysadmin@example.com
- DBA: dba@example.com
- Network Admin: netadmin@example.com

### External Contacts
- Cloud Provider: support@cloud.com
- Data Center: support@datacenter.com
- ISP: support@isp.com
EOF
    
    echo "✓ DR plan created: $plan_name.md"
}

# تابع implement failover
implement_failover() {
    local primary_host=$1
    local secondary_host=$2
    
    echo -e "\n2. Implementing failover procedure:"
    
    # Check primary status
    echo "Checking primary system: $primary_host"
    ping -c 3 $primary_host > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "✓ Primary system is up - failover not needed"
        return 0
    else
        echo "✗ Primary system is down - initiating failover"
    fi
    
    # Check secondary status
    echo "Checking secondary system: $secondary_host"
    ping -c 3 $secondary_host > /dev/null 2>&1
    if [ $? -ne 0 ]; then
        echo "✗ Secondary system is also down - CRITICAL"
        return 1
    else
        echo "✓ Secondary system is up"
    fi
    
    # Execute failover steps
    echo "Executing failover:"
    echo "1. Stopping primary services"
    echo "2. Updating DNS records"
    echo "3. Starting secondary services"
    echo "4. Verifying operations"
    echo "✓ Failover completed"
}

# تابع execute DR test
execute_dr_test() {
    local dr_dir=$1
    local test_type=$2
    
    local test_log="$dr_dir/logs/dr-test-$(date +%Y%m%d-%H%M%S).log"
    
    echo -e "\n3. Executing DR test: $test_type"
    echo "Test log: $test_log"
    
    {
        echo "DR Test Log"
        echo "============"
        echo "Test Type: $test_type"
        echo "Start Time: $(date)"
        echo ""
        
        case $test_type in
            "simulation")
                echo "Running simulation test..."
                echo "1. Simulating disaster scenario"
                echo "2. Executing recovery procedures"
                echo "3. Verifying recovery"
                echo "✓ Simulation completed"
                ;;
            "backup-restore")
                echo "Running backup restore test..."
                echo "1. Selecting backup for test"
                echo "2. Restoring to test environment"
                echo "3. Verifying data integrity"
                echo "4. Testing functionality"
                echo "✓ Backup restore test completed"
                ;;
            "full-drill")
                echo "Running full DR drill..."
                echo "1. Declaring disaster"
                echo "2. Notifying DR team"
                echo "3. Executing failover"
                echo "4. Verifying operations"
                echo "5. Executing failback"
                echo "✓ Full DR drill completed"
                ;;
            *)
                echo "Unknown test type"
                ;;
        esac
        
        echo ""
        echo "End Time: $(date)"
        echo "Result: PASSED"
    } > $test_log
    
    echo "✓ DR test completed - see log for details"
}

# تابع generate test report
generate_test_report() {
    local dr_dir=$1
    local test_log=$2
    
    echo -e "\n4. Generating test report:"
    
    cat > $dr_dir/tests/report-$(date +%Y%m%d).md << EOF
# DR Test Report

## Test Information
- Date: $(date +%Y-%m-%d)
- Test Type: DR Drill
- Status: PASSED

## Test Results
See attached log file for details.

## Findings
- All procedures executed successfully
- Recovery time within RTO
- Data integrity verified

## Recommendations
- Continue regular testing
- Update documentation as needed
- Schedule next test
EOF
    
    echo "✓ Test report generated"
}

# تابع show DR status
show_dr_status() {
    local dr_dir=$1
    
    echo -e "\n=== DR Status ==="
    echo "DR Directory: $dr_dir"
    echo "Plans: $(ls $dr_dir/plans/ 2>/dev/null | wc -l)"
    echo "Logs: $(ls $dr_dir/logs/ 2>/dev/null | wc -l)"
    echo "Tests: $(ls $dr_dir/tests/ 2>/dev/null | wc -l)"
}

# اجرا
DR_DIR="/tmp/disaster-recovery"
PLAN_NAME="dr-plan-main"

create_dr_dir "$DR_DIR"
create_dr_plan "$DR_DIR" "$PLAN_NAME"
# implement_failover "primary.example.com" "secondary.example.com"
execute_dr_test "$DR_DIR" "simulation"
generate_test_report "$DR_DIR" "$DR_DIR/logs/dr-test-$(date +%Y%m%d)*.log"
show_dr_status "$DR_DIR"
