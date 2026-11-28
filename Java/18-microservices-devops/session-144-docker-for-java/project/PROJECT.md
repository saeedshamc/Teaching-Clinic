# پروژهٔ جلسه ۱۴۴ — کانتینرسازی Java Application با Docker

یک پروژهٔ کانتینرسازی با Docker بسازید:
- `Dockerfile` با multi-stage build
- `docker-compose.yml` با `app`, `db`, `redis`
- `Application.java` با Spring Boot
- `application.properties` با environment variables
- `-Xms`, `-Xmx` برای JVM memory tuning

نمونه:
```
docker build -t myapp:latest .
docker run -p 8080:8080 myapp:latest

docker-compose up -d
docker-compose logs -f app
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
