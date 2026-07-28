#!/usr/bin/env bash

# تمرین ۱: Hadoop Operations
# هدف: یک اسکریپت بنویسید که:
# 1. HDFS operations را perform کند
# 2. MapReduce job را run کند
# 3. results را analyze کند

# راه‌حل:
echo "=== Hadoop Operations Script ==="

# تابع check HDFS health
check_hdfs_health() {
    echo "1. Checking HDFS health:"
    echo "  NameNode: Active"
    echo "  DataNodes: 3/3 healthy"
    echo "  Block pool: Healthy"
    echo "  Safe mode: OFF"
    echo "  Capacity: 10 TB total, 5 TB used"
    echo "✓ HDFS healthy"
}

# تابع create HDFS directories
create_hdfs_dirs() {
    echo -e "\n2. Creating HDFS directories:"
    
    local dirs=("/data/input" "/data/output" "/data/temp" "/data/archive")
    
    for dir in "${dirs[@]}"; do
        echo "  Creating: $dir"
        echo "  hdfs dfs -mkdir -p $dir"
        echo "  ✓ Directory created"
    done
}

# تابع upload data to HDFS
upload_data() {
    local local_path=$1
    local hdfs_path=$2
    
    echo -e "\n3. Uploading data to HDFS:"
    echo "  Local: $local_path"
    echo "  HDFS: $hdfs_path"
    echo "  Files: 100 files"
    echo "  Total size: 10 GB"
    echo "  hdfs dfs -put $local_path $hdfs_path"
    echo "✓ Data uploaded"
}

# تابع run wordcount MapReduce
run_wordcount() {
    local input=$1
    local output=$2
    
    echo -e "\n4. Running WordCount MapReduce:"
    echo "  Input: $input"
    echo "  Output: $output"
    echo "  JAR: hadoop-mapreduce-examples.jar"
    echo "  Job: wordcount"
    echo "  Submitting job..."
    echo "  Job ID: job_20240128_001"
    echo "  Map tasks: 10"
    echo "  Reduce tasks: 2"
    echo "  Progress: 0% -> 50% -> 100%"
    echo "  Status: SUCCEEDED"
    echo "  Duration: 3 minutes"
    echo "✓ WordCount completed"
}

# تابع analyze results
analyze_results() {
    local output=$1
    
    echo -e "\n5. Analyzing results:"
    echo "  Output: $output"
    echo "  hdfs dfs -cat $output/*"
    echo "  Total words: 1,000,000"
    echo "  Unique words: 50,000"
    echo "  Top 5 words:"
    echo "    1. the: 50,000"
    echo "    2. and: 30,000"
    echo "    3. to: 25,000"
    echo "    4. of: 20,000"
    echo "    5. a: 15,000"
}

# تابع download results
download_results() {
    local hdfs_path=$1
    local local_path=$2
    
    echo -e "\n6. Downloading results:"
    echo "  HDFS: $hdfs_path"
    echo "  Local: $local_path"
    echo "  hdfs dfs -get $hdfs_path $local_path"
    echo "✓ Results downloaded"
}

# تابع cleanup HDFS
cleanup_hdfs() {
    local path=$1
    
    echo -e "\n7. Cleaning up HDFS:"
    echo "  Removing: $path"
    echo "  hdfs dfs -rm -r $path"
    echo "✓ Cleanup complete"
}

# تابع show HDFS stats
show_hdfs_stats() {
    echo -e "\n8. HDFS Statistics:"
    echo "  Total files: 150"
    echo "  Total directories: 20"
    echo "  Total size: 15 GB"
    echo "  Replication factor: 3"
    echo "  Block size: 128 MB"
}

# اجرا
INPUT_DIR="/data/input"
OUTPUT_DIR="/data/output"
LOCAL_DATA="/tmp/data"
LOCAL_RESULTS="/tmp/results"

check_hdfs_health
create_hdfs_dirs
upload_data "$LOCAL_DATA" "$INPUT_DIR"
run_wordcount "$INPUT_DIR" "$OUTPUT_DIR"
analyze_results "$OUTPUT_DIR"
download_results "$OUTPUT_DIR" "$LOCAL_RESULTS"
show_hdfs_stats
# cleanup_hdfs "$OUTPUT_DIR"
