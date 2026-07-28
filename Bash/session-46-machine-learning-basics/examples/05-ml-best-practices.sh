#!/usr/bin/env bash

echo "=== ML Best Practices ==="

# 1. Data Quality
echo -e "\n1. Data Quality:"
echo "✓ Validate data sources"
echo "✓ Handle missing values appropriately"
echo "✓ Remove duplicates"
echo "✓ Check for bias in data"
echo "✓ Document data lineage"

# Example of data quality checks
data_quality_example() {
    echo -e "\nData Quality Example:"
    echo "  Validation: Schema validation on ingest"
    echo "  Missing values: Impute with median for numeric"
    echo "  Duplicates: Remove exact duplicates"
    echo "  Bias check: Compare feature distributions"
}

# 2. Model Selection
echo -e "\n2. Model Selection:"
echo "✓ Start with simple models"
echo "✓ Compare multiple algorithms"
echo "✓ Use appropriate evaluation metrics"
echo "✓ Consider model interpretability"
echo "✓ Document model choices"

# Example of model selection
model_selection_example() {
    echo -e "\nModel Selection Example:"
    echo "  Baseline: Logistic Regression (82% accuracy)"
    echo "  Complex: Random Forest (85% accuracy)"
    echo "  Selected: Random Forest (better performance)"
    echo "  Reason: 3% improvement worth complexity"
}

# 3. Training
echo -e "\n3. Training:"
echo "✓ Use cross-validation"
echo "✓ Tune hyperparameters systematically"
echo "✓ Monitor for overfitting"
echo "✓ Save model checkpoints"
echo "✓ Log training metrics"

# Example of training setup
training_example() {
    echo -e "\nTraining Setup:"
    echo "  Cross-validation: 5-fold stratified"
    echo "  Hyperparameter tuning: Grid search"
    echo "  Early stopping: Validation loss patience"
    echo "  Checkpoints: Save best model"
}

# 4. Evaluation
echo -e "\n4. Evaluation:"
echo "✓ Use held-out test set"
echo "✓ Check multiple metrics"
echo "✓ Analyze prediction errors"
echo "✓ Validate model assumptions"
echo "✓ Perform error analysis"

# Example of evaluation
evaluation_example() {
    echo -e "\nEvaluation Example:"
    echo "  Test set: 20% held-out"
    echo "  Metrics: Accuracy, Precision, Recall, F1, AUC"
    echo "  Error analysis: Confusion matrix analysis"
    echo "  Assumptions: Linear relationship validated"
}

# 5. Deployment
echo -e "\n5. Deployment:"
echo "✓ Monitor model performance"
echo "✓ Log all predictions"
echo "✓ Set up performance alerts"
echo "✓ Plan for model retraining"
echo "✓ Document deployment process"

# Example of deployment
deployment_example() {
    echo -e "\nDeployment Example:"
    echo "  Monitoring: Prometheus + Grafana"
    echo "  Logging: Prediction logs to S3"
    echo "  Alerts: Performance degradation > 10%"
    echo "  Retraining: Weekly schedule"
}

# Execute examples
data_quality_example
model_selection_example
training_example
evaluation_example
deployment_example
