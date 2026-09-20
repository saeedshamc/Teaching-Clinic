# جلسه ۱۶: قیود، FK و PRAGMA (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۰۷ و ۱۳ و ۱۵.

## اهداف قابل‌اندازه‌گیری
1. `FOREIGN KEY` را در CREATE TABLE بنویسید
2. `PRAGMA foreign_keys = ON` را قبل از کار اجباری کنید
3. `UNIQUE` و `CHECK` را به کار ببرید
4. خطای نقض FK را تفسیر کنید

## چرا مهم است؟
بدون FK، orphan row (سفارش بدون کاربر) وارد می‌شود.

## مفهوم عمیق
```sql
PRAGMA foreign_keys = ON;

CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  total REAL NOT NULL CHECK (total >= 0),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

در SQLite اگر PRAGMA خاموش باشد، تعریف FK نادیده گرفته می‌شود.

## اشتباه‌های رایج
1. فراموش `PRAGMA foreign_keys = ON`.
2. نوع ستون FK با PK مرجع ناسازگار.
3. حذف والد بدون تعیین رفتار (RESTRICT پیش‌فرض عملی).

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-16-constraints-fk/examples/01-constraints.sql
```

## الگوی بهتر
در ابتدای هر session/اتصال اپ، foreign_keys را روشن کنید.

## تمرین‌ها
### آسان
سفارش با user_id ناموجود درج کنید و خطا ببینید.
### چالشی
CHECK برای total منفی را نشان دهید.

## راهنمای حل
`INSERT ... total = -1` باید شکست بخورد.

## خودآزمایی
1. چرا PRAGMA لازم است؟
2. CHECK چه می‌کند؟
3. orphan یعنی چه؟
4. UNIQUE چندستونه ممکن است؟
5. ON DELETE CASCADE را شنیده‌اید؟ (مفهوم)

## جمع‌بندی و پل جلسهٔ بعد
یکپارچگی. جلسه بعد: ذهنیت امن SQL.
