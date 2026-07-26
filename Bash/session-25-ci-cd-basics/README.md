# جلسه ۲۵: مبانی CI/CD (CI/CD Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه CI/CD
- Continuous Integration
- Continuous Deployment
- GitHub Actions
- GitLab CI
- Jenkins Basics
- مثال‌های عملی

## مفاهیم پایه CI/CD

CI/CD مخفف Continuous Integration و Continuous Deployment است:
- **CI** - ادغام مداوم کدها
- **CD** - استقرار مداوم برنامه‌ها
- **Pipeline** - فرآیند اتوماتیک build و deploy

## GitHub Actions

GitHub Actions یک پلتفرم CI/CD است که در GitHub ادغام شده است.

```yaml
name: CI
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - run: echo "Hello CI/CD"
```

## مثال ۱: GitHub Actions

فایل [examples/01-github-actions.sh](examples/01-github-actions.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== GitHub Actions ==="

# ایجاد دایرکتوری workflow
mkdir -p /tmp/ci-test/.github/workflows

# ایجاد workflow file
cat > /tmp/ci-test/.github/workflows/ci.yml << 'EOF'
name: CI Pipeline
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v2
      
      - name: Run tests
        run: |
          echo "Running tests..."
          bash test.sh
      
      - name: Build
        run: |
          echo "Building application..."
          make build
EOF

echo "GitHub Actions Workflow ایجاد شد"
cat /tmp/ci-test/.github/workflows/ci.yml

# پاکسازی
rm -rf /tmp/ci-test
```

### توضیح خط به خط:

- `.github/workflows` - مسیر workflow files
- `on: [push]` - trigger روی push
- `runs-on: ubuntu-latest` - runner
- `steps` - مراحل pipeline

## GitLab CI

GitLab CI یک پلتفرم CI/CD داخلی GitLab است.

```yaml
stages:
  - build
  - test
  - deploy

build:
  stage: build
  script:
    - echo "Building"
```

## مثال ۲: GitLab CI

فایل [examples/02-gitlab-ci.sh](examples/02-gitlab-ci.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== GitLab CI ==="

# ایجاد .gitlab-ci.yml
cat > /tmp/gitlab-ci-test/.gitlab-ci.yml << 'EOF'
stages:
  - build
  - test
  - deploy

build:
  stage: build
  script:
    - echo "Building application..."
    - make build
  artifacts:
    paths:
      - build/

test:
  stage: test
  script:
    - echo "Running tests..."
    - make test

deploy:
  stage: deploy
  script:
    - echo "Deploying..."
    - make deploy
  only:
    - main
EOF

echo "GitLab CI Pipeline ایجاد شد"
cat /tmp/gitlab-ci-test/.gitlab-ci.yml

# پاکسازی
rm -rf /tmp/gitlab-ci-test
```

### توضیح خط به خط:

- `stages` - مراحل pipeline
- `stage` - مرحله هر job
- `script` - دستورات اجرایی
- `artifacts` - فایل‌های خروجی
- `only` - شاخه‌های خاص

## Jenkins Basics

Jenkins یک سرور CI/CD open-source است.

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'make build'
            }
        }
    }
}
```

## مثال ۳: Jenkins Pipeline

فایل [examples/03-jenkins-pipeline.sh](examples/03-jenkins-pipeline.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Jenkins Pipeline ==="

# ایجاد Jenkinsfile
cat > /tmp/jenkins-test/Jenkinsfile << 'EOF'
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'make build'
            }
        }
        
        stage('Test') {
            steps {
                sh 'make test'
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'make deploy'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline completed'
        }
    }
}
EOF

echo "Jenkinsfile ایجاد شد"
cat /tmp/jenkins-test/Jenkinsfile

# پاکسازی
rm -rf /tmp/jenkins-test
```

### توضیح خط به خط:

- `pipeline` - تعریف pipeline
- `agent any` - هر agent
- `stages` - مراحل
- `steps` - دستورات
- `post` - اقدامات پس از pipeline

## CI Pipeline با Bash

## مثال ۴: CI Script

فایل [examples/04-ci-script.sh](examples/04-ci-script.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== CI Pipeline با Bash ==="

# تابع build
build() {
    echo "1. Build:"
    echo "Compiling code..."
    # make build
    echo "✓ Build complete"
}

# تابع test
test() {
    echo -e "\n2. Test:"
    echo "Running tests..."
    # make test
    echo "✓ Tests passed"
}

# تابع deploy
deploy() {
    echo -e "\n3. Deploy:"
    echo "Deploying to production..."
    # make deploy
    echo "✓ Deploy complete"
}

# تابع rollback
rollback() {
    echo -e "\n4. Rollback:"
    echo "Rolling back to previous version..."
    # make rollback
    echo "✓ Rollback complete"
}

# اجرا pipeline
build
test
deploy
```

### توضیح خط به خط:

- توابع جداگانه برای هر مرحله
- `build` - ساخت برنامه
- `test` - اجرای تست‌ها
- `deploy` - استقرار
- `rollback` - بازگشت به نسخه قبلی

## مثال‌های عملی

### Automated Testing

```bash
#!/bin/bash
# اجرای تست‌ها اتوماتیک
npm test || pytest || make test
```

## مثال ۵: Automated Testing

فایل [examples/05-automated-testing.sh](examples/05-automated-testing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Automated Testing ==="

# ایجاد فایل تست نمونه
cat > /tmp/test_project/test.sh << 'EOF'
#!/bin/bash
echo "Running unit tests..."
# تست‌ها اینجا اجرا می‌شوند
exit 0
EOF

chmod +x /tmp/test_project/test.sh

# اجرای تست
echo "1. اجرای Unit Tests:"
/tmp/test_project/test.sh
if [ $? -eq 0 ]; then
    echo "✓ Unit tests passed"
else
    echo "✗ Unit tests failed"
    exit 1
fi

# Integration tests
echo -e "\n2. اجرای Integration Tests:"
echo "Running integration tests..."
# integration tests
echo "✓ Integration tests passed"

# E2E tests
echo -e "\n3. اجرای E2E Tests:"
echo "Running E2E tests..."
# e2e tests
echo "✓ E2E tests passed"

# پاکسازی
rm -rf /tmp/test_project
```

### توضیح خط به خط:

- Unit tests - تست واحدها
- Integration tests - تست یکپارچگی
- E2E tests - تست end-to-end
- exit code برای نتیجه تست

## Deployment Automation

## مثال ۶: Deployment Script

فایل [examples/06-deployment.sh](examples/06-deployment.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Deployment Automation ==="

# تابع deploy to staging
deploy_staging() {
    echo "1. Deploy to Staging:"
    echo "Building Docker image..."
    # docker build -t app:staging .
    echo "Pushing to registry..."
    # docker push app:staging
    echo "Deploying to staging..."
    # kubectl apply -f staging.yaml
    echo "✓ Staging deployed"
}

# تابع deploy to production
deploy_production() {
    echo -e "\n2. Deploy to Production:"
    echo "Building Docker image..."
    # docker build -t app:production .
    echo "Tagging image..."
    # docker tag app:staging app:production
    echo "Pushing to registry..."
    # docker push app:production
    echo "Deploying to production..."
    # kubectl apply -f production.yaml
    echo "✓ Production deployed"
}

# تابع health check
health_check() {
    echo -e "\n3. Health Check:"
    echo "Checking application health..."
    # curl -f http://app/health || exit 1
    echo "✓ Application healthy"
}

# اجرا
deploy_staging
health_check
# deploy_production  - با تأیید manual
```

### توضیح خط به خط:

- `deploy_staging` - استقرار به staging
- `deploy_production` - استقرار به production
- `health_check` - بررسی سلامت
- استفاده از Docker و Kubernetes

## نکات مهم

### ۱. استفاده از Environment Variables

```bash
export ENV=production
export DB_HOST=$DB_HOST
```

### ۲. Secrets Management

```bash
# از secret managers استفاده کنید
# AWS Secrets Manager, HashiCorp Vault
```

### ۳. Rollback Strategy

```bash
# همیشه plan برای rollback داشته باشید
# نسخه‌های قبلی را نگه دارید
```

## مثال ۷: CI/CD Best Practices

فایل [examples/07-ci-cd-best-practices.sh](examples/07-ci-cd-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== CI/CD Best Practices ==="

# 1. Fast feedback
echo -e "\n1. Fast Feedback:"
echo "تست‌های سریع را اول اجرا کنید"
echo "parallel execution برای تست‌ها"

# 2. Caching
echo -e "\n2. Caching:"
echo "cache dependencies برای سرعت"
echo "docker layer caching"

# 3. Security scanning
echo -e "\n3. Security Scanning:"
echo "اسکن vulnerabilities در هر build"
echo "SAST, DAST, dependency scanning"

# 4. Monitoring
echo -e "\n4. Monitoring:"
echo "monitoring در production"
echo "alerts برای مشکلات"

# 5. Documentation
echo -e "\n5. Documentation:"
echo "document pipeline steps"
echo "runbook برای troubleshooting"
```

### توضیح خط به خط:

- Fast feedback - بازخورد سریع
- Caching - cache برای سرعت
- Security scanning - اسکن امنیتی
- Monitoring - مانیتورینگ
- Documentation - مستندسازی

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. تست‌های کند

❌ اشتباه:
```bash
# تست‌های کند در CI
```

✅ درست:
```bash
# تست‌های سریع و parallel
```

### ۲. Hardcoded Secrets

❌ خطرناک:
```bash
API_KEY="secret"  - خطرناک
```

✅ درست:
```bash
export API_KEY=$CI_API_KEY  - secure
```

### ۳. بدون Rollback

❌ اشتباه:
```bash
# بدون plan برای rollback
```

✅ درست:
```bash
# همیشه rollback داشته باشید
```

## بهترین شیوه‌ها (Best Practices)

1. **تست‌های سریع اجرا کنید** - برای feedback سریع
2. **از caching استفاده کنید** - برای سرعت
3. **secrets را محرمانه نگه دارید** - از secret managers
4. **rollback داشته باشید** - برای اطمینان
5. **monitoring داشته باشید** - برای مشکلات

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه CI/CD
- GitHub Actions
- GitLab CI
- Jenkins Pipeline
- CI Script با Bash
- Automated Testing
- Deployment Automation
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): CI Script

یک اسکریپت بنویسید که:
1. کد را build کند
2. تست‌ها را اجرا کند
3. نتیجه را گزارش دهد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Deployment Pipeline

یک اسکریپت بنویسید که:
1. به staging deploy کند
2. health check انجام دهد
3. به production deploy کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Advanced Monitoring را یاد می‌گیریم.
