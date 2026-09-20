# جلسه ۰۵: SELECT، WHERE، ORDER BY، LIMIT (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۰۳–۰۴.

## اهداف قابل‌اندازه‌گیری
1. ستون‌های مشخص را SELECT کنید
2. فیلتر با WHERE و عملگرها بنویسید
3. LIKE، IN، BETWEEN را به کار ببرید
4. ORDER BY و LIMIT را ترکیب کنید

## چرا مهم است؟
۹۰٪ کار روزمره خواندن دادهٔ فیلترشده است.

## مفهوم عمیق
```sql
SELECT id, name
FROM users
WHERE email LIKE '%@ex.com'
ORDER BY name
LIMIT 10;
```

ترتیب ذهنی بندها: FROM → WHERE → SELECT → ORDER BY → LIMIT.

## اشتباه‌های رایج
1. `SELECT *` دائمی در کد production.
2. فراموش کوتیشن برای رشته.
3. فرض حساسیت به حروف مثل همهٔ موتورها (در SQLite پیش‌فرض ASCII است).

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-05-select-where/examples/01-select.sql
```

## الگوی بهتر
فقط ستون‌های لازم؛ فیلتر نزدیک‌ترین به نیاز گزارش.

## تمرین‌ها
### آسان
کاربرانی که نامشان با «س» شروع می‌شود.
### چالشی
دو شرط با AND و مرتب‌سازی نزولی id با LIMIT 2.

## راهنمای حل
`WHERE name LIKE 'س%' ORDER BY id DESC LIMIT 2`

## خودآزمایی
1. تفاوت WHERE و HAVING؟ (HAVING جلسه ۹)
2. LIKE چه کار می‌کند؟
3. LIMIT برای چیست؟
4. چرا SELECT * خطرناک است؟
5. ORDER BY چند ستون می‌پذیرد؟

## جمع‌بندی و پل جلسهٔ بعد
خواندن. جلسه بعد: UPDATE و DELETE.
