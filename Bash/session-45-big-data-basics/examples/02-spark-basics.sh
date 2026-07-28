#!/usr/bin/env bash

echo "=== Spark Basics ==="

# تابع start Spark
start_spark() {
    echo "Starting Spark:"
    echo "  Spark Master: spark://master:7077"
    echo "  Workers: 3"
    echo "  Memory per worker: 8 GB"
    echo "  Cores per worker: 4"
    echo "  Application UI: http://master:4040"
    echo "✓ Spark started"
}

# تابع Spark operations
spark_operations() {
    echo -e "\nSpark Operations:"
    echo "  Read data: spark.read.csv('data.csv')"
    echo "  Transform: df.filter(df.age > 18)"
    echo "  Aggregate: df.groupBy('department').count()"
    echo "  Write: df.write.parquet('output')"
    echo "  Cache: df.cache()"
}

# تابع Spark SQL
spark_sql() {
    echo -e "\nSpark SQL:"
    echo "  Create view: df.createOrReplaceTempView('users')"
    echo "  Query: spark.sql('SELECT * FROM users WHERE age > 18')"
    echo "  Join: spark.sql('SELECT * FROM users JOIN orders ON users.id = orders.user_id')"
    echo "  Window: spark.sql('SELECT *, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) as rank FROM employees')"
}

# تابع Spark Streaming
spark_streaming() {
    echo -e "\nSpark Streaming:"
    echo "  Source: Kafka topic 'events'"
    echo "  Batch interval: 5 seconds"
    echo "  Process: window operation"
    echo "  Output: console"
    echo "  Checkpoint: /tmp/checkpoint"
}

# تابع Spark MLlib
spark_mllib() {
    echo -e "\nSpark MLlib:"
    echo "  Linear Regression: LinearRegression()"
    echo "  Decision Tree: DecisionTreeClassifier()"
    echo "  Clustering: KMeans()"
    echo "  Pipeline: Pipeline(stages=[...])"
}

# استفاده
start_spark
spark_operations
spark_sql
spark_streaming
spark_mllib
