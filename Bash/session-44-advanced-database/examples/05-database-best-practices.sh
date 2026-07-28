#!/usr/bin/env bash

echo "=== Database Best Practices ==="

# 1. Performance
echo -e "\n1. Performance:"
echo "✓ Use appropriate indexes"
echo "✓ Optimize queries with EXPLAIN"
echo "✓ Monitor slow queries"
echo "✓ Update statistics regularly"

# Example of performance optimization
performance_example() {
    echo -e "\nPerformance Optimization Example:"
    echo "  Query: SELECT * FROM users WHERE email = 'test@example.com'"
    echo "  Before: Full table scan (1000 rows)"
    echo "  After: Index scan (1 row)"
    echo "  Improvement: 1000x faster"
}

# 2. Security
echo -e "\n2. Security:"
echo "✓ Principle of least privilege"
echo "✓ Encrypt sensitive data"
echo "✓ Enable audit logging"
echo "✓ Use strong passwords"

# Example of security setup
security_example() {
    echo -e "\nSecurity Setup Example:"
    echo "  App user: SELECT, INSERT on app_db only"
    echo "  Read user: SELECT on read_db only"
    echo "  Admin user: Localhost only"
    echo "  Encryption: TDE enabled"
}

# 3. Availability
echo -e "\n3. Availability:"
echo "✓ Implement replication"
echo "✓ Use read replicas"
echo "✓ Plan for failover"
echo "✓ Regular backups"

# Example of HA setup
ha_example() {
    echo -e "\nHigh Availability Setup:"
    echo "  Master: db-master (primary)"
    echo "  Slave-1: db-slave-1 (sync replica)"
    echo "  Slave-2: db-slave-2 (async replica)"
    echo "  Failover: Automatic with Orchestrator"
}

# 4. Scalability
echo -e "\n4. Scalability:"
echo "✓ Consider sharding for scale"
echo "✓ Use connection pooling"
echo "✓ Implement caching layer"
echo "✓ Partition large tables"

# Example of scaling strategy
scaling_example() {
    echo -e "\nScaling Strategy:"
    echo "  Read scaling: 3 read replicas"
    echo "  Write scaling: Sharding by user_id"
    echo "  Caching: Redis for hot data"
    echo "  Connection pool: PgBouncer"
}

# 5. Maintenance
echo -e "\n5. Maintenance:"
echo "✓ Regular backups (daily)"
echo "✓ Update statistics (weekly)"
echo "✓ Monitor disk space"
echo "✓ Test restore procedures"

# Example of maintenance plan
maintenance_example() {
    echo -e "\nMaintenance Plan:"
    echo "  Backups: Daily at 2 AM, retention 30 days"
    echo "  Statistics: Every Sunday at 3 AM"
    echo "  Index rebuild: Monthly"
    echo "  Restore test: Quarterly"
}

# 6. Monitoring
echo -e "\n6. Monitoring:"
echo "✓ Monitor query performance"
echo "✓ Track connection counts"
echo "✓ Monitor disk usage"
echo "✓ Set up alerts"

# Example of monitoring setup
monitoring_example() {
    echo -e "\nMonitoring Setup:"
    echo "  Metrics: Prometheus + Grafana"
    echo "  Logs: ELK Stack"
    echo "  Alerts: PagerDuty"
    echo "  Dashboards: 5 key dashboards"
}

# Execute examples
performance_example
security_example
ha_example
scaling_example
maintenance_example
monitoring_example
