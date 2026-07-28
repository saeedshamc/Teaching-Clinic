#!/usr/bin/env bash

echo "=== Serverless Best Practices ==="

# 1. Function Design
echo -e "\n1. Function Design:"
echo "✓ Single responsibility per function"
echo "✓ Short execution time (< 15 minutes)"
echo "✓ Stateless functions (no local state)"
echo "✓ Idempotent operations"

# Example of good function design
good_function_design() {
    echo -e "\nExample - Good Function:"
    echo "  Name: process-payment"
    echo "  Responsibility: Process single payment"
    echo "  Memory: 512 MB"
    echo "  Timeout: 30 seconds"
    echo "  Dependencies: Minimal"
}

# 2. Performance
echo -e "\n2. Performance:"
echo "✓ Minimize cold starts"
echo "✓ Optimize package size (< 50 MB)"
echo "✓ Use appropriate memory setting"
echo "✓ Keep dependencies minimal"

# Example of performance optimization
performance_optimization() {
    echo -e "\nPerformance Optimization:"
    echo "  Package size: 15 MB (zipped)"
    echo "  Memory: 256 MB (optimal for cost/performance)"
    echo "  Provisioned concurrency: 5 (reduce cold starts)"
    echo "  Initialization time: 200ms"
}

# 3. Security
echo -e "\n3. Security:"
echo "✓ Least privilege IAM roles"
echo "✓ Encrypt sensitive data at rest"
echo "✓ Use VPC for database access"
echo "✓ Enable X-Ray tracing"

# Example of security setup
security_setup() {
    echo -e "\nSecurity Configuration:"
    echo "  IAM: Custom role with specific permissions"
    echo "  Encryption: AWS KMS for secrets"
    echo "  VPC: Private subnets for database access"
    echo "  Secrets: AWS Secrets Manager"
}

# 4. Monitoring
echo -e "\n4. Monitoring:"
echo "✓ Enable CloudWatch Logs"
echo "✓ Monitor key metrics"
echo "✓ Set up CloudWatch alarms"
echo "✓ Enable X-Ray tracing"

# Example of monitoring setup
monitoring_setup() {
    echo -e "\nMonitoring Stack:"
    echo "  Logs: CloudWatch Logs with retention"
    echo "  Metrics: Invocations, Errors, Duration"
    echo "  Alarms: Error rate > 1%, Duration > 5s"
    echo "  Tracing: AWS X-Ray enabled"
}

# 5. Cost Optimization
echo -e "\n5. Cost Optimization:"
echo "✓ Right-size memory allocation"
echo "✓ Use provisioned concurrency"
echo "✓ Monitor and optimize duration"
echo "✓ Use Graviton2 for cost savings"

# Example of cost optimization
cost_optimization() {
    echo -e "\nCost Optimization:"
    echo "  Memory: 256 MB (optimal for cost)"
    echo "  Duration: Average 500ms"
    echo "  Provisioned concurrency: 2"
    echo "  Architecture: arm64 (Graviton2)"
    echo "  Estimated monthly cost: $5.00"
}

# 6. Error Handling
echo -e "\n6. Error Handling:"
echo "✓ Implement retry logic"
echo "✓ Use dead-letter queues"
echo "✓ Set appropriate timeouts"
echo "✓ Handle exceptions gracefully"

# Example of error handling
error_handling_example() {
    echo -e "\nError Handling Strategy:"
    echo "  Retry: Exponential backoff (max 3 attempts)"
    echo "  DLQ: SQS for failed messages"
    echo "  Timeout: 30 seconds (with early exit)"
    echo "  Monitoring: Alert on error rate > 1%"
}

# Execute examples
good_function_design
performance_optimization
security_setup
monitoring_setup
cost_optimization
error_handling_example
