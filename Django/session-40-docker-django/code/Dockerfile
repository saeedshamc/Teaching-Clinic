# syntax=docker/dockerfile:1
FROM python:3.12-slim AS base

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1

WORKDIR /app

# ۱. فقط requirements — لایهٔ cacheable
COPY requirements.txt .
RUN pip install --upgrade pip && \
    pip install --no-cache-dir -r requirements.txt

# ۲. کد پروژه
COPY . .

# ۳. collectstatic برای production (در dev fail می‌شود — || true)
RUN python manage.py collectstatic --noinput || true

EXPOSE 8000

# برای development: python manage.py runserver 0.0.0.0:8000
# برای production: gunicorn config.wsgi:application --bind 0.0.0.0:8000
CMD ["gunicorn", "config.wsgi:application", "--bind", "0.0.0.0:8000", "--workers", "3"]
