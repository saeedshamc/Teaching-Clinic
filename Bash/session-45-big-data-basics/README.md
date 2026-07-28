# جلسه ۴۵: دیتای بزرگ (Big Data Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه big data
- Hadoop ecosystem
- Spark basics
- Data processing
- Data storage
- Data analysis
- مثال‌های عملی

## مفاهیم پایه Big Data

Big data شامل large و complex datasets است:
- **Volume** - حجم بزرگ داده
- **Velocity** - سرعت بالا تولید
- **Variety** - انواع مختلف داده
- **Veracity** - کیفیت و اعتبار
- **Value** - ارزش استخراج شده

## Hadoop Ecosystem

Hadoop framework برای processing big data است.

```bash
# HDFS
- MapReduce
# YARN
```

## مثال ۱: Hadoop Basics

فایل [examples/01-hadoop-basics.sh](examples/01-hadoop-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Hadoop Basics ==="

# تابع check Hadoop status
check_hadoop_status() {
    echo "Checking Hadoop status:"
    echo "  HDFS: Running"
    echo "  YARN: Running"
    echo "  NameNode: Active"
    echo "  DataNodes: 3/3"
}

# تابع HDFS operations
hdfs_operations() {
    echo -e "\nHDFS Operations:"
    echo "  List files: hdfs dfs -ls /"
    echo "  Create directory: hdfs dfs -mkdir /data"
    echo "  Copy file: hdfs dfs -put file.txt /data/"
    echo "  Get file: hdfs dfs -get /data/file.txt"
}

# تابع run MapReduce job
run_mapreduce() {
    echo -e "\nRunning MapReduce job:"
    echo "  Input: /data/input"
    echo "  Output: /data/output"
    echo "  Mapper: wordcount_mapper.py"
    echo "  Reducer: wordcount_reducer.py"
    echo "✓ Job completed"
}

# تابع YARN status
yarn_status() {
    echo -e "\nYARN Status:"
    echo "  ResourceManager: Running"
    echo "  NodeManagers: 3"
    echo "  Available memory: 24 GB"
    echo "  Available vCores: 12"
}

# استفاده
check_hadoop_status
hdfs_operations
run_mapreduce
yarn_status
```

### توضیح خط به خط:

- Hadoop status check
- HDFS file operations
- MapReduce job execution
- YARN resource management
- distributed processing

## Spark Basics

Spark برای fast data processing است.

```bash
# Spark Core
- Spark SQL
# Spark Streaming
```

## مثال ۲: Spark Basics

فایل [examples/02-spark-basics.sh](examples/02-spark-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Spark Basics ==="

# تابع start Spark
start_spark() {
    echo "Starting Spark:"
    echo "  Spark Master: spark://master:7077"
    echo "  Workers: 3"
    echo "  Memory per worker: 8 GB"
    echo "✓ Spark started"
}

# تابع Spark operations
spark_operations() {
    echo -e "\nSpark Operations:"
    echo "  Read data: spark.read.csv('data.csv')"
    echo "  Transform: df.filter(df.age > 18)"
    echo "  Aggregate: df.groupBy('department').count()"
    echo "  Write: df.write.parquet('output')"
}

# تابع Spark SQL
spark_sql() {
    echo -e "\nSpark SQL:"
    echo "  Create view: df.createOrReplaceTempView('users')"
    echo "  Query: spark.sql('SELECT * FROM users WHERE age > 18')"
    echo "  Join: spark.sql('SELECT * FROM users JOIN orders ON users.id = orders.user_id')"
}

# تابع Spark Streaming
spark_streaming() {
    echo -e "\nSpark Streaming:"
    echo "  Source: Kafka topic 'events'"
    echo "  Batch interval: 5 seconds"
    echo "  Process: window operation"
    echo "  Output: console"
}

# استفاده
start_spark
spark_operations
spark_sql
spark_streaming
```

### توضیح خط به خط:

- Spark cluster start
- DataFrame operations
- Spark SQL queries
- streaming processing
- in-memory computing

## Data Processing

Data processing برای transforming data است.

```bash
# ETL processes
- Data cleaning
# Data transformation
```

## مثال ۳: Data Processing

فایل [examples/03-data-processing.sh](examples/03-data-processing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Data Processing ==="

# تابع ETL process
etl_process() {
    local input=$1
    local output=$2
    
    echo "ETL Process:"
    echo "  Extract: Reading from $input"
    echo "  Transform: Cleaning and transforming"
    echo "  Load: Writing to $output"
    echo "✓ ETL completed"
}

# تابع data cleaning
data_cleaning() {
    echo -e "\nData Cleaning:"
    echo "  Remove duplicates"
    echo "  Handle missing values"
    echo "  Normalize formats"
    echo "  Validate data"
}

# تابع data transformation
data_transformation() {
    echo -e "\nData Transformation:"
    echo "  Aggregate data"
    echo "  Join datasets"
    echo "  Calculate metrics"
    echo "  Enrich data"
}

# تابع data validation
data_validation() {
    echo -e "\nData Validation:"
    echo "  Check data types"
    echo "  Validate ranges"
    echo "  Check constraints"
    echo "  Verify integrity"
}

# استفاده
etl_process "/data/raw" "/data/processed"
data_cleaning
data_transformation
data_validation
```

### توضیح خط به خط:

- ETL pipeline
- data cleaning
- data transformation
- data validation
- quality assurance

## Data Storage

Data storage برای big data است.

```bash
# HDFS
- NoSQL databases
# Data lakes
```

## مثال ۴: Data Storage

فایل [examples/04-data-storage.sh](examples/04-data-storage.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Data Storage ==="

# تابع HDFS storage
hdfs_storage() {
    echo "HDFS Storage:"
    echo "  Replication factor: 3"
    echo "  Block size: 128 MB"
    echo "  Total capacity: 10 TB"
    echo "  Used: 5 TB"
}

# تابع NoSQL storage
nosql_storage() {
    echo -e "\nNoSQL Storage:"
    echo "  Cassandra: Distributed key-value"
    echo "  MongoDB: Document store"
    echo "  HBase: Column-family"
    echo "  Redis: In-memory cache"
}

# تابع data lake
data_lake() {
    echo -e "\nData Lake:"
    echo "  Raw data: S3://lake/raw/"
    echo "  Processed: S3://lake/processed/"
    echo "  Curated: S3://lake/curated/"
    echo "  Formats: Parquet, Avro, JSON"
}

# تابع partitioning
partition_data() {
    echo -e "\nData Partitioning:"
    echo "  Partition by date"
    echo "  Partition by region"
    echo "  Partition by category"
    echo "  Improves query performance"
}

# استفاده
hdfs_storage
nosql_storage
data_lake
partition_data
```

### توضیح خط به خط:

- HDFS distributed storage
- NoSQL databases
- data lake architecture
- data partitioning
- storage optimization

## نکات مهم

### ۱: Data Quality

```bash
# Validate data quality
- Monitor data lineage
# Track metadata
```

### ۲: Performance

```bash
# Optimize queries
- Use appropriate formats
- Partition data
```

### ۳: Security

```bash
# Encrypt sensitive data
- Control access
- Audit access
```

## مثال ۵: Big Data Best Practices

فایل [examples/05-big-data-best-practices.sh](examples/05-big-data-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Big Data Best Practices ==="

# 1. Data Ingestion
echo -e "\n1. Data Ingestion:"
echo "✓ Use batch for large volumes"
echo "✓ Use streaming for real-time"
echo "✓ Validate on ingest"
echo "✓ Handle duplicates"

# 2. Storage
echo -e "\n2. Storage:"
echo "✓ Use columnar formats"
echo "✓ Partition strategically"
echo "✓ Compress data"
echo "✓ Use tiered storage"

# 3. Processing
echo -e "\n3. Processing:"
echo "✓ Use Spark for speed"
echo "✓ Optimize joins"
echo "✓ Cache frequently used data"
echo "✓ Monitor performance"

# 4. Security
echo -e "\n4. Security:"
echo "✓ Encrypt at rest"
echo "✓ Encrypt in transit"
echo "✓ Implement RBAC"
echo "✓ Audit access"

# 5. Cost
echo -e "\n5. Cost Optimization:"
echo "✓ Use spot instances"
echo "✓ Auto-scale clusters"
echo "✓ Archive old data"
echo "✓ Monitor usage"
```

### توضیح خط به خط:

- data ingestion strategies
- storage optimization
- processing efficiency
- security measures
- cost optimization

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Partitioning

❌ اشتباه:
```bash
# بدون partitioning
```

✅ درست:
```bash
# strategic partitioning
```

### ۲: بدون Compression

❌ اشتباه:
```bash
# بدون compression
```

✅ درست:
```bash
# compress data
```

### ۳: بدون Validation

❌ اشتباه:
```bash
# بدون validation
```

✅ درست:
```bash
# validate data
```

## بهترین شیوه‌ها (Best Practices)

1. **Ingestion** - validate on ingest
2. **Storage** - partition و compress
3. **Processing** - use Spark
4. **Security** - encrypt everything
5. **Cost** - monitor usage

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه big data
- Hadoop ecosystem
- Spark basics
- Data processing
- Data storage
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Hadoop Operations

یک اسکریپت بنویسید که:
1. HDFS operations را perform کند
2. MapReduce job را run کند
3. results را analyze کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Spark Processing

یک اسکریپت بنویسید که:
1. Spark job را create کند
2. data را process کند
3. results را save کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Machine Learning Basics را یاد می‌گیریم.
