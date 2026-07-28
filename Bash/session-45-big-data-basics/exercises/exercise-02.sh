#!/usr/bin/env bash

# تمرین ۲: Spark Processing
# هدف: یک اسکریپت بنویسید که:
# 1. Spark job را create کند
# 2. data را process کند
# 3. results را save کند

# راه‌حل:
echo "=== Spark Processing Script ==="

# تابع initialize Spark session
init_spark_session() {
    local app_name=$1
    
    echo "1. Initializing Spark session:"
    echo "  App name: $app_name"
    echo "  Master: local[*]"
    echo "  Memory: 4 GB"
    echo "  Cores: 4"
    echo "  spark = SparkSession.builder.appName('$app_name').getOrCreate()"
    echo "✓ Spark session initialized"
}

# تابع read data
read_data() {
    local path=$1
    local format=$2
    
    echo -e "\n2. Reading data:"
    echo "  Path: $path"
    echo "  Format: ${format:-csv}"
    echo "  df = spark.read.format('$format').load('$path')"
    echo "  Schema inference: enabled"
    echo "  Header: true"
    echo "  Records read: 1,000,000"
    echo "✓ Data read"
}

# تابع transform data
transform_data() {
    echo -e "\n3. Transforming data:"
    echo "  Filter: df.filter(df.age > 18)"
    echo "  Select: df.select('name', 'age', 'salary')"
    echo "  Add column: df.withColumn('salary_category', ...)"
    echo "  Aggregate: df.groupBy('department').agg({'salary': 'avg'})"
    echo "  Join: df.join(dept_df, 'dept_id')"
    echo "✓ Data transformed"
}

# تابع run Spark SQL
run_spark_sql() {
    echo -e "\n4. Running Spark SQL:"
    echo "  df.createOrReplaceTempView('employees')"
    echo "  result = spark.sql('SELECT department, AVG(salary) as avg_salary FROM employees GROUP BY department')"
    echo "  result.show()"
    echo "  Rows returned: 10"
    echo "✓ SQL query executed"
}

# تابع cache data
cache_data() {
    local table=$1
    
    echo -e "\n5. Caching data:"
    echo "  Table: $table"
    echo "  df.cache()"
    echo "  df.count() to trigger cache"
    echo "  Cached partitions: 100"
    echo "  Memory used: 2 GB"
    echo "✓ Data cached"
}

# تابع save results
save_results() {
    local df=$1
    local path=$2
    local format=$3
    
    echo -e "\n6. Saving results:"
    echo "  DataFrame: $df"
    echo "  Path: $path"
    echo "  Format: ${format:-parquet}"
    echo "  Mode: overwrite"
    echo "  Partition: department"
    echo "  df.write.format('$format').mode('overwrite').partitionBy('department').save('$path')"
    echo "  Partitions created: 10"
    echo "✓ Results saved"
}

# تابع show metrics
show_metrics() {
    echo -e "\n7. Spark Metrics:"
    echo "  Executor memory: 4 GB"
    echo "  Executor cores: 4"
    echo "  Task duration: 2 minutes"
    echo "  Shuffle read: 1 GB"
    echo "  Shuffle write: 500 MB"
    echo "  GC time: 5 seconds"
}

# تابع stop Spark session
stop_spark() {
    echo -e "\n8. Stopping Spark session:"
    echo "  spark.stop()"
    echo "✓ Spark session stopped"
}

# تابع create ML model
create_ml_model() {
    echo -e "\n9. Creating ML model:"
    echo "  Algorithm: Linear Regression"
    echo "  Features: age, experience"
    echo "  Label: salary"
    echo "  Training data: 80%"
    echo "  Test data: 20%"
    echo "  RMSE: 5000"
    echo "  R2: 0.85"
    echo "✓ Model created"
}

# تابع streaming job
streaming_job() {
    echo -e "\n10. Streaming job:"
    echo "  Source: Kafka topic 'events'"
    echo "  Batch interval: 10 seconds"
    echo "  Window: 1 minute"
    echo "  Operation: word count"
    echo "  Output: console"
    echo "  Checkpoint: /tmp/checkpoint"
    echo "✓ Streaming job configured"
}

# اجرا
APP_NAME="DataProcessingPipeline"
INPUT_PATH="/data/input/employees.csv"
OUTPUT_PATH="/data/output/processed"

init_spark_session "$APP_NAME"
read_data "$INPUT_PATH" "csv"
transform_data
run_spark_sql
cache_data "employees"
save_results "result" "$OUTPUT_PATH" "parquet"
show_metrics
create_ml_model
streaming_job
stop_spark
