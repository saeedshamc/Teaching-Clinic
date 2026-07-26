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
