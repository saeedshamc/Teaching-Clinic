#!/usr/bin/env bash

echo "=== RPO and RTO ==="

# تابع calculate RPO
calculate_rpo() {
    local backup_frequency=$1
    local acceptable_loss=$2
    
    echo "RPO Calculation:"
    echo "Backup frequency: $backup_frequency hours"
    echo "Acceptable data loss: $acceptable_loss hours"
    
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
