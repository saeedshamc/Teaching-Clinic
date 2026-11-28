# تمرین‌های جلسه ۱۴۴ — Docker for Java

## آسان
1. `Dockerfile` با multi-stage build بسازید.
2. `docker build -t myapp:latest .` برای image build استفاده کنید.

## متوسط
3. `docker-compose.yml` با `app`, `db`, `redis` services بسازید.
4. `docker run -p 8080:8080` برای container run استفاده کنید.

## سخت
5. `-Xms`, `-Xmx` flags برای JVM memory tuning در container استفاده کنید.
6. `docker-compose up -d` برای multi-container startup استفاده کنید.
