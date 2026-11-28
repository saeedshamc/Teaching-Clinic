# پروژهٔ جلسه ۱۴۵ — CI/CD Pipeline برای Java Application

یک CI/CD pipeline بسازید:
- `.github/workflows/java.yml` با GitHub Actions
- `Jenkinsfile` با Jenkins pipeline
- `.gitlab-ci.yml` با GitLab CI
- `Dockerfile` برای Docker build
- `mvn test` برای automated testing
- `docker push` برای deployment

نمونه:
```
GitHub Actions:
  push → checkout → setup JDK → build → test → docker build → push

Jenkins:
  Checkout → Build → Test → Docker Build → Docker Push

GitLab CI:
  build → test → docker → deploy
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
