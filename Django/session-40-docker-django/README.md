# جلسه ۴۰ — Docker برای پروژهٔ Django

## درس

Docker پروژه را با همهٔ وابستگی‌ها بسته‌بندی می‌کند — «روی دستگاه من کار می‌کند» را حذف می‌کند.

### Dockerfile (multi-stage)

```dockerfile
# syntax=docker/dockerfile:1
FROM python:3.12-slim AS base
ENV PYTHONDONTWRITEBYTECODE=1 PYTHONUNBUFFERED=1
WORKDIR /app

# وابستگی‌ها را اول کپی تا لایه‌ها cache شوند
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .
RUN python manage.py collectstatic --noinput || true

EXPOSE 8000
CMD ["gunicorn", "config.wsgi:application", "--bind", "0.0.0.0:8000"]
```

### docker-compose.yml — وب + db + redis

```yaml
services:
  web:
    build: .
    command: python manage.py runserver 0.0.0.0:8000   # dev
    volumes: [".:/app"]
    ports: ["8000:8000"]
    env_file: .env
    depends_on: [db, redis]

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: library
      POSTGRES_USER: django
      POSTGRES_PASSWORD: secret
    volumes: ["pgdata:/var/lib/postgresql/data"]

  redis:
    image: redis:7-alpine

volumes:
  pgdata:
```

### نکات

- `.dockerignore`: `.venv`, `__pycache__`, `media/`, `staticfiles/`, `.git`
- در settings از env بخوانید: `os.environ.get("DATABASE_URL")` یا django-environ
- برای production از `gunicorn` در CMD استفاده کنید نه runserver

## کد نمونه

`code/Dockerfile` و `code/docker-compose.yml` را ببینید.

## تمرین

یک `docker-compose.yml` بنویسید که سرویس‌های `web`، `db` (postgres) و `redis` را بالا بیاورد و `web` به آن‌ها `depends_on` داشته باشد.

## جواب تمرین

همان YAML بالا — فقط `env_file` و `volumes` را با مقادیر پروژهٔ خود تنظیم کنید.

## 📖 شرح فایل‌های `code/`

**`Dockerfile`** — `python:3.12-slim` base؛ `PYTHONDONTWRITEBYTECODE`/`PYTHONUNBUFFERED` برای لاگ تمیز؛ `requirements.txt` جدا کپی می‌شود تا لایهٔ pip cache شود؛ `collectstatic` با `|| true` چون در dev ممکن است settings ناقص باشد؛ `CMD` نهایی `gunicorn` برای production.

**`docker-compose.yml`** — سرویس‌ها: `web` (build + runserver dev + volume live + env_file + depends_on)، `db` (postgres:16 با healthcheck `pg_isready`)، `redis` (برای Celery/Channels)؛ `pgdata` volume برای ماندگاری دیتابیس.
