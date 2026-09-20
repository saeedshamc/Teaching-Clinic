# جلسه ۱۰: HEALTHCHECK، کاربر غیرroot و prune (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسات ۰۶–۰۹.

## اهداف قابل‌اندازه‌گیری
1. یک `HEALTHCHECK` ساده به Dockerfile اضافه کنید
2. بگویید چرا اجرای پروسه به‌عنوان root ایدهٔ بدی است
3. وضعیت health را با `docker ps` بخوانید
4. خطر `docker system prune -a --volumes` را توضیح دهید

## چرا مهم است؟
Orchestrator و Compose از health برای تصمیم restart/routing استفاده می‌کنند؛ prune بی‌احتیاط داده را می‌خورد.

## مفهوم عمیق
- **HEALTHCHECK:** دستور دوره‌ای داخل کانتینر؛ وضعیت `starting` / `healthy` / `unhealthy`.
- **USER:** پس از آماده‌سازی فایل‌ها به کاربر غیرprivileged سوییچ کنید.
- **prune:** پاک‌سازی منابع بلااستفاده — فلگ `-a` و `--volumes` بسیار مخرب‌اند.

## اشتباه‌های رایج
1. HEALTHCHECK روی سرویسی که هنوز listen نشده با interval خیلی کوتاه.
2. prune روی ماشین تیمی بدون هماهنگی.
3. فراموش کردن permission بعد از `USER`.

## مثال گام‌به‌گام
```powershell
powershell -File Docker/session-10-health-prune/examples/01-healthcheck.ps1
```

## الگوی بهتر
prune را مرحله‌ای انجام دهید: اول `docker container prune`، بعد imageهای آویزان؛ volume را آخر و آگاهانه.

## تمرین‌ها
### آسان
وضعیت health دمو را در `docker ps` ببینید.
### چالشی
یک HEALTHCHECK عمداً شکست‌خورده بسازید و وضعیت `unhealthy` را مشاهده کنید.

## راهنمای حل
nginx با wget/curl داخلی یا دستور ساده؛ اسکریپت منتظر healthy می‌ماند.

## خودآزمایی
1. HEALTHCHECK چه می‌کند؟
2. چرا غیرroot؟
3. خطر prune با `--volumes`؟
4. `healthy` کجا دیده می‌شود؟
5. آیا prune اجباری هر روز است؟

## جمع‌بندی و پل جلسهٔ بعد
سلامت و ایمنی پایه. جلسه بعد: Docker Compose.
