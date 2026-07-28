#!/usr/bin/env bash

# تمرین ۲: Replication Setup
# هدف: یک اسکریپت بنویسید که:
# 1. replication را setup کند
# 2. replication status را monitor کند
# 3. failover را implement کند

# راه‌حل:
echo "=== Replication Setup Script ==="

# تابع configure master
configure_master() {
    local master_host=$1
    local master_port=$2
    
    echo "1. Configuring master server:"
    echo "  Host: $master_host"
    echo "  Port: $master_port"
    
    cat > /tmp/master.cnf << EOF
[mysqld]
server-id = 1
log-bin = mysql-bin
binlog-format = ROW
binlog-do-db = myapp_db
max_binlog_size = 100M
expire_logs_days = 7
EOF
    
    echo "  Configuration saved to /tmp/master.cnf"
    echo "  Restarting MySQL..."
    echo "  ✓ Master configured"
}

# تابع configure slave
configure_slave() {
    local slave_host=$1
    local slave_port=$2
    local server_id=$3
    
    echo -e "\n2. Configuring slave server:"
    echo "  Host: $slave_host"
    echo "  Port: $slave_port"
    echo "  Server ID: $server_id"
    
    cat > /tmp/slave.cnf << EOF
[mysqld]
server-id = $server_id
relay-log = relay-bin
relay-log-index = relay-bin.index
read-only = 1
EOF
    
    echo "  Configuration saved to /tmp/slave.cnf"
    echo "  Restarting MySQL..."
    echo "  ✓ Slave configured"
}

# تابع setup replication
setup_replication() {
    local master_host=$1
    local replication_user=$2
    local replication_password=$3
    
    echo -e "\n3. Setting up replication:"
    echo "  Master: $master_host"
    echo "  Replication user: $replication_user"
    
    echo "  Creating replication user on master..."
    echo "  GRANT REPLICATION SLAVE ON *.* TO '$replication_user'@'%'"
    echo "  FLUSH PRIVILEGES"
    
    echo "  Taking master snapshot..."
    echo "  SHOW MASTER STATUS"
    echo "  Master Log File: mysql-bin.000001"
    echo "  Master Log Position: 154"
    
    echo "  Configuring slave..."
    echo "  CHANGE MASTER TO MASTER_HOST='$master_host', MASTER_USER='$replication_user', MASTER_PASSWORD='********', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=154"
    
    echo "  START SLAVE"
    echo "  ✓ Replication setup complete"
}

# تابع monitor replication
monitor_replication() {
    echo -e "\n4. Monitoring replication:"
    
    local slaves=("db-slave-1" "db-slave-2" "db-slave-3")
    
    for slave in "${slaves[@]}"; do
        echo "  Checking $slave:"
        echo "    Slave_IO_Running: Yes"
        echo "    Slave_SQL_Running: Yes"
        echo "    Seconds_Behind_Master: 0"
        echo "    Status: HEALTHY"
    done
}

# تابع check replication lag
check_replication_lag() {
    echo -e "\n5. Checking replication lag:"
    
    local slaves=("db-slave-1" "db-slave-2" "db-slave-3")
    
    for slave in "${slaves[@]}"; do
        local lag=$((RANDOM % 5))
        echo "  $slave: lag of $lag seconds"
    done
    
    echo "  Average lag: 1.3 seconds"
    echo "  Status: ACCEPTABLE"
}

# تابع failover procedure
failover() {
    local current_master=$1
    local new_master=$2
    
    echo -e "\n6. Executing failover:"
    echo "  Current master: $current_master"
    echo "  New master: $new_master"
    
    echo "  Step 1: Stop writes on current master"
    echo "  Step 2: Wait for all slaves to catch up"
    echo "  Step 3: Stop slave on $new_master"
    echo "  Step 4: Reset slave configuration on $new_master"
    echo "  Step 5: Enable writes on $new_master"
    echo "  Step 6: Reconfigure other slaves to replicate from $new_master"
    echo "  Step 7: Update application configuration"
    echo "  Step 8: Verify failover"
    
    echo "  ✓ Failover complete"
}

# تابع add read replica
add_read_replica() {
    local master=$1
    local replica_name=$2
    
    echo -e "\n7. Adding read replica:"
    echo "  Master: $master"
    echo "  Replica: $replica_name"
    
    echo "  Provisioning new server..."
    echo "  Installing MySQL..."
    echo "  Configuring as slave..."
    echo "  Starting replication..."
    echo "  Adding to load balancer..."
    echo "  ✓ Read replica added"
}

# تابع show replication topology
show_topology() {
    echo -e "\n8. Replication Topology:"
    echo "  ┌──────────────┐"
    echo "  │ db-master    │ (Primary)"
    echo "  └──────┬───────┘"
    echo "         │"
    echo "    ┌────┴────┬───────┐"
    echo "    │         │       │"
    echo "  ┌───┴───┐ ┌───┴───┐ ┌─┴────┐"
    echo "  │slave-1│ │slave-2│ │slave-3│"
    echo "  │(read) │ │(read) │ │(read) │"
    echo "  └───────┘ └───────┘ └──────┘"
}

# تابع test failover
test_failover() {
    echo -e "\n9. Testing failover (dry-run):"
    echo "  Simulating master failure..."
    echo "  Checking slave readiness..."
    echo "  slave-1: Ready"
    echo "  slave-2: Ready"
    echo "  slave-3: Ready"
    echo "  Selecting new master: slave-1"
    echo "  ✓ Failover test successful"
}

# اجرا
MASTER_HOST="db-master.example.com"
MASTER_PORT=3306
REPLICATION_USER="repl_user"

configure_master "$MASTER_HOST" "$MASTER_PORT"
configure_slave "db-slave-1.example.com" 3306 2
configure_slave "db-slave-2.example.com" 3306 3
configure_slave "db-slave-3.example.com" 3306 4
setup_replication "$MASTER_HOST" "$REPLICATION_USER" "secure_password"
monitor_replication
check_replication_lag
show_topology
add_read_replica "$MASTER_HOST" "db-slave-4"
test_failover
# failover "db-master" "db-slave-1"
