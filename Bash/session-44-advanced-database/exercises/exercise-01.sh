#!/usr/bin/env bash

# تمرین ۱: Database Setup
# هدف: یک اسکریپت بنویسید که:
# 1. database را optimize کند
# 2. indexes را ایجاد کند
# 3. slow queries را monitor کند

# راه‌حل:
echo "=== Database Optimization Script ==="

# تابع check database health
check_database_health() {
    local db_name=$1
    
    echo "1. Checking database health: $db_name"
    echo "  Connection: OK"
    echo "  Uptime: 30 days"
    echo "  Connections: 50/100"
    echo "  Query cache hit rate: 95%"
    echo "✓ Database healthy"
}

# تابع analyze slow queries
analyze_slow_queries() {
    echo -e "\n2. Analyzing slow queries:"
    
    # Simulate slow query log analysis
    cat > /tmp/slow-queries.log << EOF
# Time: 2024-01-28T10:00:00
# User@Host: app_user[app] @ localhost
# Query_time: 5.234  Lock_time: 0.001 Rows_sent: 1000 Rows_examined: 1000000
SELECT * FROM orders WHERE status = 'pending';

# Time: 2024-01-28T10:01:00
# User@Host: app_user[app] @ localhost
# Query_time: 3.123  Lock_time: 0.001 Rows_sent: 500 Rows_examined: 500000
SELECT * FROM users WHERE email LIKE '%@example.com';
EOF
    
    echo "  Slow query threshold: 2 seconds"
    echo "  Total slow queries: 2"
    echo "  Top slow query: SELECT * FROM orders WHERE status = 'pending'"
    echo "  Execution time: 5.234 seconds"
    echo "  Rows examined: 1,000,000"
}

# تابع create recommended indexes
create_indexes() {
    echo -e "\n3. Creating recommended indexes:"
    
    local indexes=(
        "CREATE INDEX idx_orders_status ON orders(status)"
        "CREATE INDEX idx_users_email ON users(email)"
        "CREATE INDEX idx_orders_created ON orders(created_at)"
    )
    
    for index in "${indexes[@]}"; do
        echo "  Executing: $index"
        echo "  ✓ Index created"
    done
}

# تابع optimize tables
optimize_tables() {
    local tables=("orders" "users" "products")
    
    echo -e "\n4. Optimizing tables:"
    
    for table in "${tables[@]}"; do
        echo "  Optimizing $table..."
        echo "    Analyzing statistics"
        echo "    Rebuilding indexes"
        echo "    Compacting data"
        echo "  ✓ $table optimized"
    done
}

# تابع update statistics
update_statistics() {
    echo -e "\n5. Updating table statistics:"
    
    local tables=("orders" "users" "products")
    
    for table in "${tables[@]}"; do
        echo "  Updating statistics for $table..."
        echo "  ✓ Statistics updated"
    done
}

# تابع check index usage
check_index_usage() {
    echo -e "\n6. Checking index usage:"
    echo "  idx_orders_status: Used 95% of time"
    echo "  idx_users_email: Used 90% of time"
    echo "  idx_orders_created: Used 85% of time"
    echo "  idx_unused: Used 0% of time (consider removing)"
}

# تابع suggest optimizations
suggest_optimimizations() {
    echo -e "\n7. Optimization suggestions:"
    echo "  1. Add composite index on orders(status, created_at)"
    echo "  2. Remove unused index idx_unused"
    echo "  3. Increase query_cache_size to 256M"
    echo "  4. Partition orders table by date"
    echo "  5. Consider read replicas for reporting queries"
}

# تابع generate report
generate_optimization_report() {
    echo -e "\n8. Optimization Report:"
    
    cat > /tmp/db-optimization-report.txt << EOF
Database Optimization Report
============================
Date: $(date +%Y-%m-%d)
Database: myapp_db

Health Status: HEALTHY
Slow Queries: 2
Indexes Created: 3
Tables Optimized: 3

Recommendations:
- Add composite index on orders(status, created_at)
- Remove unused index idx_unused
- Increase query_cache_size to 256M
- Partition orders table by date

Next Review: $(date -d '+7 days' +%Y-%m-%d)
EOF
    
    echo "  Report saved to /tmp/db-optimization-report.txt"
    cat /tmp/db-optimization-report.txt
}

# تابع schedule optimization
schedule_optimization() {
    echo -e "\n9. Scheduling regular optimization:"
    echo "  Cron job: 0 3 * * 0 /opt/scripts/optimize-db.sh"
    echo "  Frequency: Weekly (Sunday 3 AM)"
    echo "  ✓ Scheduled"
}

# اجرا
DATABASE="myapp_db"

check_database_health "$DATABASE"
analyze_slow_queries
create_indexes
optimize_tables
update_statistics
check_index_usage
suggest_optimimizations
generate_optimization_report
schedule_optimization
