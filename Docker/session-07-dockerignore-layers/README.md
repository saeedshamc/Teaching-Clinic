# جلسه ۰۷: dockerignore و لایه‌ها (۹۰ دقیقه)

**محیط فرض:** Docker Desktop / Engine · [SETUP.md](../SETUP.md)

## پیش‌نیازها
جلسه ۰۶.

## اهداف قابل‌اندازه‌گیری
1. بگویید ترتیب دستورات Dockerfile روی کش چه اثری دارد
2. یک `.dockerignore` بنویسید که `node_modules` و `.git` را حذف کند
3. توضیح دهید چرا `COPY . .` زودهنگام کش را خراب می‌کند
4. یک rebuild با تغییر فقط فایل آخر را مقایسهٔ ذهنی کنید

## چرا مهم است؟
build کند و image چاق معمولاً از نادیده گرفتن لایه و ignore می‌آید.

## مفهوم عمیق
- Docker لایه‌ها را از بالا کش می‌کند تا اولین تغییر.
- وابستگی‌ها را زودتر کپی/نصب کنید؛ کد منبع را دیرتر.
- `.dockerignore` مثل `.gitignore` برای **context** ارسال‌شده به daemon است.

## اشتباه‌های رایج
1. کپی کل پروژه با `.git` و `node_modules`.
2. یک `RUN` عظیم غیرقابل کش.
3. تغییر فایل بالای Dockerfile برای هر rebuild جزئی.

## مثال گام‌به‌گام
پوشهٔ [`examples/app`](./examples/app/) را ببینید؛ سپس:

```powershell
powershell -File Docker/session-07-dockerignore-layers/examples/01-build-with-ignore.ps1
```

## الگوی بهتر
```
COPY package.json ./
RUN npm ci
COPY . .
```
(در پروژه‌های Node؛ اینجا نمونهٔ آموزشی ساده‌تر است.)

## تمرین‌ها
### آسان
یک فایل `secret.tmp` بسازید و در `.dockerignore` بگذارید؛ مطمئن شوید داخل image نیست.
### چالشی
ترتیب `COPY` را جابه‌جا کنید و توضیح دهید کش چه می‌شود.

## راهنمای حل
اسکریپت build می‌کند و نشان می‌دهد context با ignore سبک‌تر است.

## خودآزمایی
1. `.dockerignore` چه کار می‌کند؟
2. چرا ترتیب لایه مهم است؟
3. چه چیزهایی معمولاً ignore می‌شوند؟
4. context چیست؟
5. یک نشانهٔ bust شدن کش؟

## جمع‌بندی و پل جلسهٔ بعد
کش و ignore. جلسه بعد: multi-stage.
