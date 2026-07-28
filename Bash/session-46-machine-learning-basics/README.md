# جلسه ۴۶: یادگیری ماشین (Machine Learning Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه machine learning
- Data preparation
- Model training
- Model evaluation
- Model deployment
- ML pipelines
- مثال‌های عملی

## مفاهیم پایه Machine Learning

Machine learning algorithms که از data یاد می‌گیرند:
- **Supervised Learning** - labeled data
- **Unsupervised Learning** - unlabeled data
- **Reinforcement Learning** - reward-based
- **Deep Learning** - neural networks
- **Feature Engineering** - data preparation

## Data Preparation

Data preparation برای ML models است.

```bash
# Data cleaning
- Feature scaling
# Train-test split
```

## مثال ۱: Data Preparation

فایل [examples/01-data-preparation.sh](examples/01-data-preparation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Data Preparation ==="

# تابع load data
load_data() {
    local file=$1
    
    echo "Loading data from $file:"
    echo "  Records: 10,000"
    echo "  Features: 10"
    echo "  Labels: 1"
}

# تابع clean data
clean_data() {
    echo -e "\nCleaning data:"
    echo "  Remove missing values"
    echo "  Remove duplicates"
    echo "  Handle outliers"
    echo "  Standardize formats"
}

# تابع feature scaling
feature_scaling() {
    echo -e "\nFeature scaling:"
    echo "  Normalization: Min-Max"
    echo "  Standardization: Z-score"
    echo "  One-hot encoding"
}

# تابع train test split
train_test_split() {
    local ratio=$1
    
    echo -e "\nTrain-test split:"
    echo "  Train: $ratio"
    echo "  Test: $((100 - ratio))%"
    echo "  Stratified: Yes"
}

# استفاده
load_data "data.csv"
clean_data
feature_scaling
train_test_split 80
```

### توضیح خط به خط:

- data loading
- data cleaning
- feature scaling
- train-test split
- data preprocessing

## Model Training

Model training برای learning patterns است.

```bash
# Algorithm selection
- Hyperparameter tuning
# Cross-validation
```

## مثال ۲: Model Training

فایل [examples/02-model-training.sh](examples/02-model-training.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Model Training ==="

# تابع select algorithm
select_algorithm() {
    local task=$1
    
    echo "Selecting algorithm for $task:"
    echo "  Linear Regression"
    echo "  Decision Tree"
    echo "  Random Forest"
}

# تابع train model
train_model() {
    local algorithm=$1
    local data=$2
    
    echo -e "\nTraining model:"
    echo "  Algorithm: $algorithm"
    echo "  Data: $data"
    echo "  Epochs: 100"
    echo "  Batch size: 32"
}

# تابع hyperparameter tuning
hyperparameter_tuning() {
    echo -e "\nHyperparameter tuning:"
    echo "  Grid search"
    echo "  Random search"
    echo "  Best parameters found"
}

# تابع cross validation
cross_validation() {
    local folds=$1
    
    echo -e "\nCross-validation:"
    echo "  Folds: $folds"
    echo "  Mean accuracy: 85%"
    echo "  Std deviation: 2%"
}

# استفاده
select_algorithm "regression"
train_model "Linear Regression" "train.csv"
hyperparameter_tuning
cross_validation 5
```

### توضیح خط به خط:

- algorithm selection
- model training
- hyperparameter tuning
- cross-validation
- model optimization

## Model Evaluation

Model evaluation برای assessing performance است.

```bash
# Accuracy metrics
- Confusion matrix
- ROC curve
```

## مثال ۳: Model Evaluation

فایل [examples/03-model-evaluation.sh](examples/03-model-evaluation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Model Evaluation ==="

# تابع calculate metrics
calculate_metrics() {
    echo "Calculating metrics:"
    echo "  Accuracy: 85%"
    echo "  Precision: 82%"
    echo "  Recall: 88%"
    echo "  F1-score: 85%"
}

# تابع confusion matrix
confusion_matrix() {
    echo -e "\nConfusion Matrix:"
    echo "  TP: 400, FN: 50"
    echo "  FP: 30, TN: 520"
}

# تابع ROC curve
roc_curve() {
    echo -e "\nROC Curve:"
    echo "  AUC: 0.92"
    echo "  TPR: 0.88"
    echo "  FPR: 0.12"
}

# تابع feature importance
feature_importance() {
    echo -e "\nFeature importance:"
    echo "  Feature 1: 0.35"
    echo "  Feature 2: 0.25"
    echo "  Feature 3: 0.20"
}

# استفاده
calculate_metrics
confusion_matrix
roc_curve
feature_importance
```

### توضیح خط به خط:

- performance metrics
- confusion matrix
- ROC analysis
- feature importance
- model evaluation

## Model Deployment

Model deployment برای production است.

```bash
# Model export
- API deployment
- Batch prediction
```

## مثال ۴: Model Deployment

فایل [examples/04-model-deployment.sh](examples/04-model-deployment.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Model Deployment ==="

# تابع save model
save_model() {
    local model=$1
    local path=$2
    
    echo "Saving model:"
    echo "  Model: $model"
    echo "  Path: $path"
    echo "  Format: pickle"
}

# تابع deploy API
deploy_api() {
    echo -e "\nDeploying API:"
    echo "  Framework: Flask"
    echo "  Endpoint: /predict"
    echo "  Port: 5000"
}

# تابع batch prediction
batch_prediction() {
    local input=$1
    local output=$2
    
    echo -e "\nBatch prediction:"
    echo "  Input: $input"
    echo "  Output: $output"
    echo "  Records: 10,000"
}

# تابع monitor model
monitor_model() {
    echo -e "\nMonitoring model:"
    echo "  Requests: 1000/min"
    echo "  Latency: 50ms"
    echo "  Error rate: 0.1%"
}

# استفاده
save_model "model.pkl" "/models/"
deploy_api
batch_prediction "input.csv" "output.csv"
monitor_model
```

### توضیح خط به خط:

- model saving
- API deployment
- batch prediction
- model monitoring
- production deployment

## نکات مهم

### ۱: Data Quality

```bash
# Ensure data quality
- Validate data
- Handle missing values
```

### ۲: Overfitting

```bash
# Prevent overfitting
- Use regularization
- Cross-validation
```

### ۳: Bias

```bash
# Check for bias
- Fair evaluation
- Diverse data
```

## مثال ۵: ML Best Practices

فایل [examples/05-ml-best-practices.sh](examples/05-ml-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== ML Best Practices ==="

# 1. Data Quality
echo -e "\n1. Data Quality:"
echo "✓ Validate data sources"
echo "✓ Handle missing values"
echo "✓ Remove duplicates"
echo "✓ Check for bias"

# 2. Model Selection
echo -e "\n2. Model Selection:"
echo "✓ Start simple"
echo "✓ Compare models"
echo "✓ Use appropriate metrics"
echo "✓ Consider interpretability"

# 3. Training
echo -e "\n3. Training:"
echo "✓ Use cross-validation"
echo "✓ Tune hyperparameters"
echo "✓ Monitor overfitting"
echo "✓ Save checkpoints"

# 4. Evaluation
echo -e "\n4. Evaluation:"
echo "✓ Use test set"
echo "✓ Check metrics"
echo "✓ Analyze errors"
echo "✓ Validate assumptions"

# 5. Deployment
echo -e "\n5. Deployment:"
echo "✓ Monitor performance"
echo "✓ Log predictions"
echo "✓ Set up alerts"
echo "✓ Plan for retraining"
```

### توضیح خط به خط:

- data quality assurance
- model selection strategy
- training best practices
- comprehensive evaluation
- deployment monitoring

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: Data Leakage

❌ اشتباه:
```bash
# data leakage
```

✅ درست:
```bash
# proper split
```

### ۲: Overfitting

❌ اشتباه:
```bash
# overfitting
```

✅ درست:
```bash
# regularization
```

### ۳: بدون Validation

❌ اشتباه:
```bash
# بدون validation
```

✅ درست:
```bash
# cross-validation
```

## بهترین شیوه‌ها (Best Practices)

1. **Data** - quality first
2. **Model** - start simple
3. **Training** - cross-validation
4. **Evaluation** - comprehensive metrics
5. **Deployment** - monitor continuously

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه machine learning
- Data preparation
- Model training
- Model evaluation
- Model deployment
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Data Pipeline

یک اسکریپت بنویسید که:
1. data را load کند
2. data را clean کند
3. features را scale کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): ML Pipeline

یک اسکریپت بنویسید که:
1. model را train کند
2. model را evaluate کند
3. model را deploy کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Advanced Scripting Patterns را یاد می‌گیریم.
