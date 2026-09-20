# جلسه ۰۳: CREATE TABLE و PRIMARY KEY (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۰۱–۰۲.

## اهداف قابل‌اندازه‌گیری
1. جدول با چند ستون بسازید
2. `PRIMARY KEY` را درست بگذارید
3. `NOT NULL` و `UNIQUE` ساده را به کار ببرید
4. `DROP TABLE IF EXISTS` را برای اسکریپت تکرارپذیر بنویسید

## چرا مهم است؟
اسکیما قرارداد داده است. بدون کلید، به‌روزرسانی و ارتباط جداول بی‌ثبات می‌شود.

## مفهوم عمیق
```sql
CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  name TEXT NOT NULL
);
```

در SQLite، `INTEGER PRIMARY KEY` معمولاً نقش شناسهٔ خودکار را بازی می‌کند.

## اشتباه‌های رایج
1. جدول بدون کلید معنادار.
2. `UNIQUE` روی ستونی که واقعاً یکتا نیست.
3. تغییر اسکیما با دستکاری فایل بدون اسکریپت نسخه.

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-03-create-table/examples/01-create-users.sql
sqlite3 clinic.db ".schema users"
```

## الگوی بهتر
اسکریپت‌های مهاجرت‌مانند: همیشه از وضعیت تمیز قابل‌ساخت مجدد.

## تمرین‌ها
### آسان
جدول `products(id, title, price)` بسازید.
### چالشی
`email` را `NOT NULL UNIQUE` کنید و درج تکراری را ببینید (باید خطا بدهد).

## راهنمای حل
دومین `INSERT` با همان email با خطای UNIQUE شکست می‌خورد.

## خودآزمایی
1. PRIMARY KEY چیست؟
2. NOT NULL چه اجباری می‌آورد؟
3. UNIQUE چه فرقی با PK دارد؟
4. چرا IF EXISTS مفید است؟
5. INTEGER PRIMARY KEY در SQLite چه ویژه‌ای دارد؟

## جمع‌بندی و پل جلسهٔ بعد
جدول. جلسه بعد: INSERT.
