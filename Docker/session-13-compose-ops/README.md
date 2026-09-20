# جلسه ۱۳: depends_on، restart و volume در Compose (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسه ۱۲.

## اهداف قابل‌اندازه‌گیری
1. `depends_on` را در Compose به کار ببرید و محدودیتش را بگویید
2. یک `restart` policy ست کنید
3. named volume را به سرویس دیتابیس/فایل وصل کنید
4. بگویید `depends_on` به‌تنهایی «آماده بودن اپ» را تضمین نمی‌کند

## چرا مهم است؟
ترتیب استارت و ماندگاری داده در استک واقعی حیاتی است.

## مفهوم عمیق
- **depends_on:** ترتیب ساخت/شروع کانتینر؛ نه لزوماً ready بودن پروتکل (مگر با condition و healthcheck در Compose جدید).
- **restart:** مثلاً `unless-stopped` برای دموهای محلی پایدار.
- **volumes:** تعریف سطح‌بالا + mount در سرویس.

## اشتباه‌های رایج
1. فرض اینکه بعد از depends_on، DB فوراً query می‌پذیرد.
2. volume را در `down -v` بی‌خبر پاک کردن.
3. restart=always روی کانتینر خراب بدون نگاه به لاگ.

## مثال گام‌به‌گام
```powershell
powershell -File Docker/session-13-compose-ops/examples/01-restart-volume.ps1
```

## الگوی بهتر
برای وابستگی واقعی: HEALTHCHECK + `depends_on: condition: service_healthy` وقتی Compose پشتیبانی می‌کند.

## تمرین‌ها
### آسان
بعد از down بدون `-v`، volume را در `docker volume ls` ببینید.
### چالشی
`down -v` را فقط در sandbox بزنید و اثرش را مشاهده کنید.

## راهنمای حل
سرویس writer در volume می‌نویسد؛ reader می‌خواند؛ restart روی writer تنظیم شده.

## خودآزمایی
1. محدودیت depends_on؟
2. `unless-stopped` یعنی چه؟
3. `down -v` چه می‌کند؟
4. volume سطح compose کجا تعریف می‌شود؟
5. health condition چه کمکی می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
عملیات Compose. جلسه بعد: دیباگ روزمره.
