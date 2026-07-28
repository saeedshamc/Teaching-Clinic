# جلسه ۴۴: دیتابیس پیشرفته (Advanced Database)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پیشرفته database
- Database optimization
- Replication
- Sharding
- Database security
- Backup and recovery
- مثال‌های عملی

## مفاهیم پیشرفته Database

Advanced database شامل optimization و scaling است:
- **Indexing** - improve query performance
- **Replication** - data redundancy
- **Sharding** - horizontal scaling
- **Partitioning** - data organization
- **Caching** - reduce database load

## Database Optimization

Database optimization برای performance improvement است.

```bash
# Query optimization
- Index optimization
# Configuration tuning
```

## مثال ۱: Database Optimization

فایل [examples/01-database-optimization.sh](examples/01-database-optimization.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Optimization ==="

# تابع analyze query
analyze_query() {
    local query=$1
    
    echo "Analyzing query:"
    echo "  Query: $query"
    echo "  Execution plan: EXPLAIN $query"
    echo "  Index usage: Yes"
    echo "  Rows scanned: 100"
}

# تابع create index
create_index() {
    local table=$1
    local column=$2
    
    echo -e "\nCreating index:"
    echo "  Table: $table"
    echo "  Column: $column"
    echo "  Index type: B-tree"
    echo "✓ Index created"
}

# تابع optimize table
optimize_table() {
    local table=$1
    
    echo -e "\nOptimizing table $table:"
    echo "  Analyzing table statistics"
    echo "  Rebuilding indexes"
    echo "  Compacting data"
    echo "✓ Table optimized"
}

# تابع check slow queries
check_slow_queries() {
    echo -e "\nChecking slow queries:"
    echo "  Slow query log: /var/log/mysql/slow.log"
    echo "  Threshold: 2 seconds"
    echo "  Count: 5 slow queries"
}

# استفاده
analyze_query "SELECT * FROM users WHERE name = 'John'"
create_index "users" "name"
optimize_table "users"
check_slow_queries
```

### توضیح خط به خط:

- query analysis
- index creation
- table optimization
- slow query monitoring
- performance tuning

## Replication

Replication برای data redundancy و high availability است.

```bash
# Master-slave replication
- Master-master replication
# Read replicas
```

## مثال ۲: Replication

فایل [examples/02-replication.sh](examples/02-replication.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Replication ==="

# تابع setup master-slave
setup_master_slave() {
    local master=$1
    local slave=$2
    
    echo "Setting up master-slave replication:"
    echo "  Master: $master"
    echo "  Slave: $slave"
    echo "  Configuring binlog on master"
    echo "  Configuring relay log on slave"
    echo "✓ Replication configured"
}

# تابع check replication status
check_replication_status() {
    echo -e "\nChecking replication status:"
    echo "  Slave_IO_Running: Yes"
    echo "  Slave_SQL_Running: Yes"
    echo "  Seconds_Behind_Master: 0"
    echo "✓ Replication healthy"
}

# تابع promote slave
promote_slave() {
    local slave=$1
    
    echo -e "\nPromoting slave to master:"
    echo "  Stopping replication"
    echo "  Enabling writes"
    echo "  Updating application config"
    echo "✓ Slave promoted"
}

# تابع add read replica
add_read_replica() {
    local master=$1
    local replica=$2
    
    echo -e "\nAdding read replica:"
    echo "  Master: $master"
    echo "  Replica: $replica"
    echo "  Configuring replication"
    echo "✓ Read replica added"
}

# استفاده
setup_master_slave "db-master" "db-slave-1"
check_replication_status
add_read_replica "db-master" "db-slave-2"
# promote_slave "db-slave-1"
```

### توضیح خط به خط:

- master-slave setup
- replication status check
- slave promotion
- read replica addition
- high availability

## Sharding

Sharding برای horizontal scaling است.

```bash
# Horizontal partitioning
- Consistent hashing
# Shard key selection
```

## مثال ۳: Sharding

فایل [examples/03-sharding.sh](examples/03-sharding.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Sharding ==="

# تابع create shard
create_shard() {
    local shard_name=$1
    local range=$2
    
    echo "Creating shard:"
    echo "  Shard: $shard_name"
    echo "  Key range: $range"
    echo "✓ Shard created"
}

# تابع route query
route_query() {
    local shard_key=$1
    
    echo -e "\nRouting query:"
    echo "  Shard key: $shard_key"
    echo "  Target shard: shard-1"
    echo "  Query routed"
}

# تابع rebalance shards
rebalance_shards() {
    echo -e "\nRebalancing shards:"
    echo "  Analyzing data distribution"
    echo "  Moving data between shards"
    echo "✓ Shards rebalanced"
}

# تابع check shard health
check_shard_health() {
    echo -e "\nChecking shard health:"
    echo "  Shard-1: Healthy"
    echo "  Shard-2: Healthy"
    echo "  Shard-3: Degraded"
}

# استفاده
create_shard "shard-1" "0-1000"
create_shard "shard-2" "1001-2000"
route_query "user-500"
rebalance_shards
check_shard_health
```

### توضیح خط به خط:

- shard creation
- query routing
- shard rebalancing
- health monitoring
- horizontal scaling

## Database Security

Database security برای protecting data است.

```bash
# Access control
- Encryption
- Audit logging
```

## مثال ۴: Database Security

فایل [examples/04-database-security.sh](examples/04-database-security.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Security ==="

# تابع create user
create_user() {
    local username=$1
    local privileges=$2
    
    echo "Creating database user:"
    echo "  Username: $username"
    echo "  Privileges: $privileges"
    echo "✓ User created"
}

# تابع grant privileges
grant_privileges() {
    local user=$1
    local database=$2
    local privileges=$3
    
    echo -e "\nGranting privileges:"
    echo "  User: $user"
    echo "  Database: $database"
    echo "  Privileges: $privileges"
    echo "✓ Privileges granted"
}

# تابع enable encryption
enable_encryption() {
    echo -e "\nEnabling encryption:"
    echo "  At-rest encryption: AES-256"
    echo "  In-transit encryption: TLS"
    echo "✓ Encryption enabled"
}

# تابع audit logging
enable_audit_log() {
    echo -e "\nEnabling audit logging:"
    echo "  Log file: /var/log/mysql/audit.log"
    echo "  Log all queries: Yes"
    echo "✓ Audit logging enabled"
}

# استفاده
create_user "app_user" "SELECT,INSERT"
grant_privileges "app_user" "mydb" "SELECT,INSERT,UPDATE"
enable_encryption
enable_audit_log
```

### توضیح خط به خط:

- user creation
- privilege management
- encryption setup
- audit logging
- security best practices

## نکات مهم

### ۱: Regular Backups

```bash
# Schedule regular backups
- Test restore procedures
# Monitor backup status
```

### ۲: Monitoring

```bash
# Monitor performance
- Track metrics
- Set up alerts
```

### ۳: Indexing Strategy

```bash
# Index wisely
- Monitor index usage
- Remove unused indexes
```

## مثال ۵: Database Best Practices

فایل [examples/05-database-best-practices.sh](examples/05-database-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Database Best Practices ==="

# 1. Performance
echo -e "\n1. Performance:"
echo "✓ Use appropriate indexes"
echo "✓ Optimize queries"
echo "✓ Monitor slow queries"

# 2. Security
echo -e "\n2. Security:"
echo "✓ Principle of least privilege"
echo "✓ Encrypt sensitive data"
echo "✓ Enable audit logging"

# 3. Availability
echo -e "\n3. Availability:"
echo "✓ Implement replication"
echo "✓ Use read replicas"
echo "✓ Plan for failover"

# 4. Scalability
echo -e "\n4. Scalability:"
echo "✓ Consider sharding"
echo "✓ Use connection pooling"
echo "✓ Implement caching"

# 5. Maintenance
echo -e "\n5. Maintenance:"
echo "✓ Regular backups"
echo "✓ Update statistics"
echo "✓ Monitor disk space"
```

### توضیح خط به خط:

- performance optimization
- security measures
- high availability
- scalability strategies
- maintenance tasks

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Indexes

❌ اشتباه:
```bash
# بدون indexes
```

✅ درست:
```bash
# appropriate indexes
```

### ۲: بدون Backups

❌ اشتباه:
```bash
# بدون backups
```

✅ درست:
```bash
# regular backups
```

### ۳: بدون Monitoring

❌ اشتباه:
```bash
# بدون monitoring
```

✅ درست:
```bash
# comprehensive monitoring
```

## بهترین شیوه‌ها (Best Practices)

1. **Performance** - indexes و optimization
2. **Security** - least privilege
3. **Availability** - replication
4. **Scalability** - sharding
5. **Maintenance** - backups

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پیشرفته database
- Database optimization
- Replication
- Sharding
- Database security
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Database Setup

یک اسکریپت بنویسید که:
1. database را optimize کند
2. indexes را ایجاد کند
3. slow queries را monitor کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Replication Setup

یک اسکریپت بنویسید که:
1. replication را setup کند
2. replication status را monitor کند
3. failover را implement کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Big Data Basics را یاد می‌گیریم.
