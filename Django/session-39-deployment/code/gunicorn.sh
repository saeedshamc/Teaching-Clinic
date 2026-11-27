# gunicorn.sh — اجرای Gunicorn در production
# این فایل را در سرویس systemd یا اسکریپت دیپلوی استفاده کنید.

# ابتدا migrate و collectstatic
python manage.py migrate --noinput
python manage.py collectstatic --noinput

# اجرای Gunicorn
# --workers: تعداد پروسه‌ها (توصیه: 2×CPU+1)
# --bind: آدرس و پورت داخلی (Nginx به اینجا وصل می‌شود)
# --timeout: حداکثر زمان هر درخواست
gunicorn config.wsgi:application \
    --bind 127.0.0.1:8000 \
    --workers 4 \
    --timeout 60 \
    --access-logfile /var/log/gunicorn/access.log \
    --error-logfile /var/log/gunicorn/error.log
