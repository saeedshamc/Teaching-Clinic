# پاسخ تمرین‌های جلسه ۱۴۵

## آسان

**۱ و ۲.**
```yaml
name: Java CI
on:
  push:
    branches: [ main ]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          java-version: '17'
      - run: mvn clean package -DskipTests
      - run: mvn test
```

## متوسط

**۳ و ۴.**
```yaml
stages: [build, test, deploy]
build:
  stage: build
  script: mvn clean package -DskipTests
test:
  stage: test
  script: mvn test
deploy:
  stage: deploy
  script: docker push myapp:$CI_COMMIT_SHA
  only: [main]
```

## سخت

**۵ و ۶.**
```yaml
- name: Push to Docker Hub
  if: github.ref == 'refs/heads/main'
  run: |
    echo ${{ secrets.DOCKER_PASSWORD }} | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin
    docker push myapp:${{ github.sha }}
```
