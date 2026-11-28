# جلسه ۱۴۵ — CI/CD: Continuous Integration & Deployment

## اهداف یادگیری
- `CI/CD` را برای automated build/test/deploy بشناسید
- `Jenkins`, `GitHub Actions`, `GitLab CI` را بفهمید
- `Pipeline` برای automated workflow بسازید
- `Docker` integration برای deployment استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۴۴ (Docker for Java)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### CI/CD چیست؟

`CI` — Continuous Integration: merge code frequently, automated build/test.
`CD` — Continuous Deployment/Delivery: automated deployment to production.

### GitHub Actions — workflow

```yaml
name: Java CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven

      - name: Build with Maven
        run: mvn clean package -DskipTests

      - name: Run tests
        run: mvn test

      - name: Build Docker image
        run: docker build -t myapp:${{ github.sha }} .

      - name: Push to Docker Hub
        if: github.ref == 'refs/heads/main'
        run: |
          echo ${{ secrets.DOCKER_PASSWORD }} | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin
          docker push myapp:${{ github.sha }}
```

### Jenkins Pipeline — declarative

```groovy
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp:${BUILD_NUMBER} .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker push myapp:${BUILD_NUMBER}'
            }
        }
    }
}
```

### GitLab CI — .gitlab-ci.yml

```yaml
stages:
  - build
  - test
  - deploy

build:
  stage: build
  image: maven:3.9-eclipse-temurin-17
  script:
    - mvn clean package -DskipTests
  artifacts:
    paths:
      - target/*.jar

test:
  stage: test
  image: maven:3.9-eclipse-temurin-17
  script:
    - mvn test

deploy:
  stage: deploy
  image: docker:latest
  script:
    - docker build -t myapp:$CI_COMMIT_SHA .
    - docker push myapp:$CI_COMMIT_SHA
  only:
    - main
```

## کد مهم

`.github/workflows/java.yml` — GitHub Actions workflow.
`Jenkinsfile` — Jenkins pipeline.
`.gitlab-ci.yml` — GitLab CI config.
`Dockerfile` — Docker build for CI/CD.

## خطاهای رایج
- `mvn test` را بدون `mvn clean package` استفاده کردن → missing dependencies.
- `docker build` را بدون `context` استفاده کردن → files not found.
- `secrets` را بدون `if` condition استفاده کردن → secrets exposed.
- `deploy` را بدون `only`/`if` استفاده کردن → deploy to all branches.

## جمع‌بندی
- CI/CD برای automated build/test/deploy.
- GitHub Actions, Jenkins, GitLab CI برای CI/CD platforms.
- Pipeline stages: Checkout → Build → Test → Docker → Deploy.
- `Docker` integration برای containerized deployment.

**جلسهٔ بعد:** Message Queues.
