# جلسه ۰۶: Dockerfile پایه (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسات ۰۱–۰۵.

## اهداف قابل‌اندازه‌گیری
1. یک Dockerfile با `FROM`، `WORKDIR`، `COPY`، `CMD` بنویسید
2. `docker build -t ...` را اجرا کنید
3. تفاوت `CMD` و `ENTRYPOINT` را بگویید
4. image ساخته‌شده را با `-p` اجرا کنید

## چرا مهم است؟
Image آماده از Docker Hub کافی نیست؛ اپ شما باید reproducible build شود.

## مفهوم عمیق
هر دستور Dockerfile معمولاً یک **لایه** می‌سازد.

| دستور | نقش |
|-------|-----|
| `FROM` | پایه |
| `WORKDIR` | پوشهٔ کاری |
| `COPY`/`ADD` | فایل به image (`COPY` ترجیح) |
| `RUN` | دستور در زمان build |
| `CMD` | پیش‌فرض اجرا (قابل جایگزینی) |
| `ENTRYPOINT` | اجرایی ثابت‌تر (آرگومان‌پذیر) |

## اشتباه‌های رایج
1. `ADD` به‌جای `COPY` بدون نیاز به URL/tar.
2. `run` بدون build یا tag اشتباه.
3. کپی secrets داخل image.

## مثال گام‌به‌گام
```powershell
cd Docker/session-06-dockerfile/examples/app
docker build -t clinic-s06 .
docker run --rm -p 8080:80 clinic-s06
```

یا:

```powershell
powershell -File Docker/session-06-dockerfile/examples/01-build-run.ps1
```

## الگوی بهتر
یک مسئولیت برای هر image؛ تگ معنادار مثل `clinic-s06:latest`.

## تمرین‌ها
### آسان
متن `index.html` را عوض کنید و دوباره build کنید.
### چالشی
`CMD` را طوری عوض کنید که `nginx -g 'daemon off;'` صریح بماند و تفاوت را توضیح دهید.

## راهنمای حل
اپ استاتیک کوچک با nginx:alpine سرو می‌شود.

## خودآزمایی
1. `FROM` چیست؟
2. `COPY` در برابر `ADD`؟
3. `CMD` در برابر `ENTRYPOINT`؟
4. `-t` در build چیست؟
5. چرا secret در Dockerfile ممنوع است؟

## جمع‌بندی و پل جلسهٔ بعد
ساخت image. جلسه بعد: `.dockerignore` و کش لایه.
