# جلسه ۰۹: ARG و ENV (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسات ۰۶–۰۸.

## اهداف قابل‌اندازه‌گیری
1. تفاوت `ARG` (build-time) و `ENV` (runtime) را بگویید
2. با `--build-arg` مقدار بدهید
3. با `-e` یا Compose متغیر runtime ست کنید
4. بگویید چرا secret را در `ENV` داخل image نگذارید

## چرا مهم است؟
پیکربندی محیط (dev/stage/prod) بدون rebuild بی‌مورد و بدون لو رفتن راز.

## مفهوم عمیق
- **ARG:** فقط در زمان `build`؛ بعد از build در کانتینر دیده نمی‌شود مگر به ENV منتقل شود.
- **ENV:** داخل کانتینر در دسترس است؛ در لایه‌ها قابل مشاهده است → برای پسورد بد است.
- ترجیح: secret از secret store / فایل env محلی gitignored / orchestrator.

## اشتباه‌های رایج
1. گذاشتن API key در Dockerfile به‌صورت `ENV`.
2. قاطی کردن build-arg با runtime env.
3. فرض اینکه پاک کردن ENV از history لایه، secret را محو می‌کند.

## مثال گام‌به‌گام
```powershell
powershell -File Docker/session-09-arg-env/examples/01-arg-env.ps1
```

## الگوی بهتر
مقادیر غیرحساس → ENV؛ رازها → بیرون از image.

## تمرین‌ها
### آسان
`APP_TITLE` را عوض و دوباره build کنید.
### چالشی
همان image را با `-e` متفاوت run کنید و تفاوت ARG/ENV را بنویسید.

## راهنمای حل
اسکریپت با build-arg صفحه می‌سازد و با `-e` هم چک می‌کند.

## خودآزمایی
1. ARG چیست؟
2. ENV چیست؟
3. `--build-arg` کجا مصرف می‌شود؟
4. چرا secret در image ممنوع است؟
5. `-e` چه می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
پیکربندی. جلسه بعد: HEALTHCHECK و پاک‌سازی محتاطانه.
