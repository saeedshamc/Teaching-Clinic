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
