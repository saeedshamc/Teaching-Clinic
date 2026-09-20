# جلسه ۰۲: Image و Container — چرخهٔ حیات (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسه ۰۱؛ daemon روشن.

## اهداف قابل‌اندازه‌گیری
1. تفاوت image و container را بگویید
2. `pull`، `run`، `ps`، `stop`، `rm`، `rmi` را اجرا کنید
3. معنی `--rm` را توضیح دهید
4. یک container متوقف را از لیست پاک کنید

## چرا مهم است؟
۹۰٪ کار روزمره همین چرخه است؛ بدون آن Dockerfile و Compose بی‌معنی‌اند.

## مفهوم عمیق
```
registry  --pull-->  image (دیسک محلی)
image     --run--->  container (پروسه)
container --stop-->  متوقف (هنوز روی دیسک مگر --rm)
container --rm---->  حذف container
image     --rmi--->  حذف image (اگر container وابسته نباشد)
```

## اشتباه‌های رایج
1. `rmi` روی imageای که هنوز container دارد.
2. فراموش کردن `docker ps -a` برای دیدن متوقف‌ها.
3. فکر کردن که `stop` فایل‌های داخل volume نام‌دار را پاک می‌کند (نمی‌کند).

## مثال گام‌به‌گام
```powershell
powershell -File Docker/session-02-image-container/examples/01-lifecycle.ps1
```

## الگوی بهتر
برای آزمایش یک‌بارمصرف: `docker run --rm ...` تا container خودکار پاک شود.

## تمرین‌ها
### آسان
`docker pull alpine:3.20` سپس `docker images`.
### چالشی
یک container از `alpine` بسازید، `echo hello` بزنید، متوقف و حذف کنید.

## راهنمای حل
اسکریپت `hello-world` و `alpine` را می‌کشد، اجرا می‌کند و پاک می‌کند.

## خودآزمایی
1. image در برابر container؟
2. `--rm` چه می‌کند؟
3. `ps` در برابر `ps -a`؟
4. چه وقت `rmi` شکست می‌خورد؟
5. `pull` چه می‌آورد؟

## جمع‌بندی و پل جلسهٔ بعد
چرخهٔ پایه. جلسه بعد: لاگ، exec و پورت.
