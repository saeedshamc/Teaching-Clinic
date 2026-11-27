# جلسه ۳۹ — دیپلوی Django (Gunicorn + Nginx)

## درس

### سه لایه در production

```
Client → Nginx (reverse proxy, static, SSL) → Gunicorn (WSGI server) → Django
```

### Gunicorn — اجرای WSGI

```bash
pip install gunicorn
# سرویس systemd:
gunicorn config.wsgi:application --bind 127.0.0.1:8000 --workers 3
# workers ≈ 2×CPU + 1
```

### Nginx — سرو static/media + proxy

```nginx
server {
    listen 80;
    server_name example.com;

    location /static/ { alias /path/to/staticfiles/; }
    location /media/  { alias /path/to/media/; }

    location / {
        proxy_pass http://127.0.0.1:8000;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

### collectstatic در production

```bash
python manage.py collectstatic --noinput   # فایل‌ها را در STATIC_ROOT جمع می‌کند
# settings: STATIC_ROOT = BASE_DIR / "staticfiles"
```

### Checklist دیپلوی

- [ ] `DEBUG=False`
- [ ] `ALLOWED_HOSTS` = دامنهٔ واقعی
- [ ] `SECRET_KEY` از env
- [ ] `SECURE_SSL_REDIRECT=True` + HTTPS
- [ ] DB production (PostgreSQL) نه SQLite
- [ ] `collectstatic` اجرا شده
- [ ] لاگ‌ها و error tracking (Sentry)

## کد نمونه

`code/nginx.conf` و `code/gunicorn.sh` را ببینید.

## 📖 شرح فایل‌های `code/`

**`gunicorn.sh`** — `migrate` + `collectstatic` قبل از اجرا؛ `gunicorn config.wsgi:application --bind 127.0.0.1:8000 --workers 4` — workers ≈ 2×CPU+1؛ Nginx به همین پورت داخلی proxy می‌کند.

**`nginx.conf`** — `location /static/` و `/media/` مستقیم از دیسک با `expires`/cache header سرو می‌شوند (سریع، بدون لود Django)؛ `location /` با `proxy_pass` به Gunicorn + headerهای `Host`/`X-Forwarded-*` برای HTTPS detection.
