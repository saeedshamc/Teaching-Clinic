#!/usr/bin/env bash

echo "=== Big Data Best Practices ==="

# 1. Data Ingestion
echo -e "\n1. Data Ingestion:"
echo "✓ Use batch for large volumes"
echo "✓ Use streaming for real-time"
echo "✓ Validate on ingest"
echo "✓ Handle duplicates"
echo "✓ Track lineage"

# Example of ingestion strategy
ingestion_example() {
    echo -e "\nIngestion Strategy:"
    echo "  Batch: Daily at 2 AM"
    echo "  Streaming: Kafka -> Spark Streaming"
    echo "  Validation: Schema validation"
    echo "  Deduplication: Idempotent writes"
}

# 2. Storage
echo -e "\n2. Storage:"
echo "✓ Use columnar formats (Parquet)"
echo "✓ Partition strategically"
echo "✓ Compress data (Snappy)"
echo "✓ Use tiered storage"
echo "✓ Set retention policies"

# Example of storage strategy
storage_example() {
    echo -e "\nStorage Strategy:"
    echo "  Format: Parquet with Snappy"
    echo "  Partition: date, region"
    echo "  Compression: 70% reduction"
    echo "  Tiering: Hot (SSD), Warm (HDD), Cold (S3)"
}

# 3. Processing
echo -e "\n3. Processing:"
echo "✓ Use Spark for speed"
echo "✓ Optimize joins"
echo "✓ Cache frequently used data"
echo "✓ Monitor performance"
echo "✓ Use broadcast joins"

# Example of processing optimization
processing_example() {
    echo -e "\nProcessing Optimization:"
    echo "  Framework: Spark"
    echo "  Memory: 64 GB executor"
    echo "  Caching: Cache reference data"
    echo "  Joins: Broadcast for small tables"
    echo "  Performance: 5x faster"
}

# 4. Security
echo -e "\n4. Security:"
echo "✓ Encrypt at rest (SSE-S3)"
echo "✓ Encrypt in transit (TLS)"
echo "✓ Implement RBAC"
echo "✓ Audit access"
echo "✓ Mask sensitive data"

# Example of security setup
security_example() {
    echo -e "\nSecurity Setup:"
    echo "  Encryption: AES-256"
    echo "  Access: IAM roles"
    echo "  Audit: CloudTrail enabled"
    echo "  Masking: PII data masked"
}

# 5. Cost Optimization
echo -e "\n5. Cost Optimization:"
echo "✓ Use spot instances"
echo "✓ Auto-scale clusters"
echo "✓ Archive old data"
echo "✓ Monitor usage"
echo "✓ Use lifecycle policies"

# Example of cost optimization
cost_example() {
    echo -e "\nCost Optimization:"
    echo "  Compute: Spot instances (70% savings)"
    echo "  Storage: Lifecycle policies"
    echo "  Archive: Glacier for old data"
    echo "  Monthly savings: $500"
}

# Execute examples
ingestion_example
storage_example
processing_example
security_example
cost_example
