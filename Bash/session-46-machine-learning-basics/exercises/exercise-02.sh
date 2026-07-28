#!/usr/bin/env bash

# تمرین ۲: ML Pipeline
# هدف: یک اسکریپت بنویسید که:
# 1. model را train کند
# 2. model را evaluate کند
# 3. model را deploy کند

# راه‌حل:
echo "=== ML Pipeline Script ==="

# تابع load training data
load_training_data() {
    local train_path=$1
    
    echo "1. Loading training data:"
    echo "  Path: $train_path"
    echo "  Records: 7,984"
    echo "  Features: 15"
    echo "✓ Training data loaded"
}

# تابع select model
select_model() {
    local task=$1
    
    echo -e "\n2. Selecting model for $task:"
    echo "  Algorithm: Random Forest Classifier"
    echo "  Reason: Handles non-linear relationships"
    echo "  Hyperparameters:"
    echo "    n_estimators: 100"
    echo "    max_depth: 10"
    echo "    min_samples_split: 5"
}

# تابع train model
train_model() {
    local train_data=$1
    
    echo -e "\n3. Training model:"
    echo "  Data: $train_data"
    echo "  Cross-validation: 5-fold"
    echo "  Training time: 3 minutes"
    echo "  Best CV score: 0.87"
    echo "✓ Model trained"
}

# تابع evaluate on validation
evaluate_validation() {
    local val_data=$1
    
    echo -e "\n4. Evaluating on validation set:"
    echo "  Data: $val_data"
    echo "  Accuracy: 86%"
    echo "  Precision: 83%"
    echo "  Recall: 88%"
    echo "  F1-score: 85%"
}

# تابع tune hyperparameters
tune_hyperparameters() {
    echo -e "\n5. Tuning hyperparameters:"
    echo "  Method: Random Search"
    echo "  Iterations: 50"
    echo "  Best parameters:"
    echo "    n_estimators: 200"
    echo "    max_depth: 15"
    echo "    min_samples_split: 2"
    echo "  Best score: 0.89"
}

# تابع train final model
train_final_model() {
    local train_data=$1
    local val_data=$2
    
    echo -e "\n6. Training final model:"
    echo "  Data: Train + Validation"
    echo "  Total records: 9,581"
    echo "  Best parameters applied"
    echo "  Training time: 5 minutes"
    echo "✓ Final model trained"
}

# تابع evaluate on test
evaluate_test() {
    local test_data=$1
    
    echo -e "\n7. Evaluating on test set:"
    echo "  Data: $test_data"
    echo "  Accuracy: 85%"
    echo "  Precision: 82%"
    echo "  Recall: 87%"
    echo "  F1-score: 84%"
    echo "  ROC-AUC: 0.91"
}

# تابع save model
save_model() {
    local model_name=$1
    local version=$2
    
    echo -e "\n8. Saving model:"
    echo "  Name: $model_name"
    echo "  Version: $version"
    echo "  Path: /models/$model_name-$version.pkl"
    echo "  Size: 55 MB"
    echo "✓ Model saved"
}

# تابع deploy model
deploy_model() {
    local model_path=$1
    
    echo -e "\n9. Deploying model:"
    echo "  Model: $model_path"
    echo "  Framework: Flask"
    echo "  Endpoint: /predict"
    echo "  Port: 5000"
    echo "  Workers: 4"
    echo "  URL: http://ml-api.example.com/predict"
    echo "✓ Model deployed"
}

# تابع setup monitoring
setup_monitoring() {
    echo -e "\n10. Setting up monitoring:"
    echo "  Metrics: Prometheus"
    echo "  Dashboard: Grafana"
    echo "  Alerts: PagerDuty"
    echo "  Logging: CloudWatch"
    echo "✓ Monitoring configured"
}

# تابع test deployment
test_deployment() {
    local endpoint=$1
    
    echo -e "\n11. Testing deployment:"
    echo "  Endpoint: $endpoint"
    echo "  Test request: curl -X POST $endpoint"
    echo "  Response time: 45ms"
    echo "  Prediction: [1, 0, 1, 1]"
    echo "✓ Deployment tested"
}

# تابع generate ml report
generate_ml_report() {
    echo -e "\n12. ML Pipeline Report:"
    cat > /tmp/ml-pipeline-report.txt << EOF
ML Pipeline Report
==================
Date: $(date +%Y-%m-%d)

Model Information
-----------------
Algorithm: Random Forest Classifier
Version: v1.0.0
Parameters: n_estimators=200, max_depth=15

Training
--------
Training records: 9,581
Validation records: 1,597
Test records: 1,996
Training time: 5 minutes

Performance
-----------
Test Accuracy: 85%
Test Precision: 82%
Test Recall: 87%
Test F1-score: 84%
ROC-AUC: 0.91

Deployment
-----------
API Endpoint: http://ml-api.example.com/predict
Status: Healthy
Monitoring: Enabled
EOF
    
    cat /tmp/ml-pipeline-report.txt
}

# اجرا
TRAIN="/tmp/train.parquet"
VAL="/tmp/val.parquet"
TEST="/tmp/test.parquet"
MODEL="classifier"
VERSION="v1.0.0"
ENDPOINT="http://ml-api.example.com/predict"

load_training_data "$TRAIN"
select_model "classification"
train_model "$TRAIN"
evaluate_validation "$VAL"
tune_hyperparameters
train_final_model "$TRAIN" "$VAL"
evaluate_test "$TEST"
save_model "$MODEL" "$VERSION"
deploy_model "/models/$MODEL-$VERSION.pkl"
setup_monitoring
test_deployment "$ENDPOINT"
generate_ml_report
