# جلسه ۰۸: Multi-stage build (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسات ۰۶–۰۷.

## اهداف قابل‌اندازه‌گیری
1. یک Dockerfile دو مرحله‌ای بنویسید (`AS builder` و stage نهایی)
2. بگویید چرا ابزار build نباید در image نهایی بماند
3. image تک‌مرحله‌ای و چندمرحله‌ای را از نظر اندازه مقایسهٔ ذهنی کنید
4. فقط artifact لازم را به stage نهایی `COPY --from=` کنید

## چرا مهم است؟
image کوچک‌تر = pull سریع‌تر و سطح حمله کمتر.

## مفهوم عمیق
```
stage builder:  نصب ابزار + تولید خروجی
stage final:    فقط runtime + کپی خروجی
```

`COPY --from=builder /out /dest` پل بین مرحله‌هاست.

## اشتباه‌های رایج
1. گذاشتن compiler و cache در image نهایی.
2. فراموش کردن نام stage (`AS ...`).
3. کپی کل `/` از builder.

## مثال گام‌به‌گام
```powershell
powershell -File Docker/session-08-multistage/examples/01-multistage.ps1
```

## الگوی بهتر
stage نهایی را روی تصویر runtime مینیمال (`nginx:alpine` یا `node:*-alpine`) نگه دارید.

## تمرین‌ها
### آسان
متن تولیدشده در builder را عوض و rebuild کنید.
### چالشی
یک stage سوم برای تست ذهنی اضافه کنید (حتی اگر اجرا نشود) و نقشش را بنویسید.

## راهنمای حل
builder یک HTML می‌سازد؛ nginx فقط همان فایل را سرو می‌کند.

## خودآزمایی
1. multi-stage چیست؟
2. `--from` چه می‌کند؟
3. یک مزیت امنیتی؟
4. چرا alpine رایج است؟
5. آیا همهٔ پروژه‌ها به multi-stage نیاز دارند؟

## جمع‌بندی و پل جلسهٔ بعد
image لاغر. جلسه بعد: `ARG` و `ENV`.
