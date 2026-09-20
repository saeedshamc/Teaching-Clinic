# جلسه ۰۶: UPDATE و DELETE (۹۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسه ۰۵.

## اهداف قابل‌اندازه‌گیری
1. UPDATE با WHERE هدفمند بنویسید
2. DELETE امن با WHERE بنویسید
3. خطر دستور بدون WHERE را توضیح دهید
4. قبل از تغییر مخرب، با SELECT همان شرط را تست کنید

## چرا مهم است؟
یک UPDATE بدون WHERE می‌تواند کل جدول را خراب کند.

## مفهوم عمیق
```sql
-- اول تست
SELECT * FROM users WHERE id = 2;
-- بعد تغییر
UPDATE users SET city = 'شیراز' WHERE id = 2;
DELETE FROM users WHERE id = 4;
```

## اشتباه‌های رایج
1. `UPDATE users SET city = 'x';` بدون WHERE.
2. DELETE برای «پاکسازی تست» روی DB واقعی.
3. فراموش تراکنش (جلسه ۱۵) برای چند تغییر وابسته.

## مثال گام‌به‌گام
```bash
sqlite3 clinic.db < session-06-update-delete/examples/01-update-delete.sql
```

## الگوی بهتر
همیشه SELECT با همان WHERE؛ در اپ از تراکنش استفاده کنید.

## تمرین‌ها
### آسان
شهر یک کاربر را عوض کنید.
### چالشی
همهٔ کاربران بدون city را حذف کنید — اول SELECT شمارش.

## راهنمای حل
`SELECT COUNT(*) FROM users WHERE city IS NULL;` سپس DELETE همان شرط.

## خودآزمایی
1. خطر UPDATE بدون WHERE؟
2. چرا اول SELECT؟
3. تفاوت DELETE و DROP TABLE؟
4. آیا می‌توان چند ستون را یکجا UPDATE کرد؟
5. soft delete مفهومی چیست؟

## جمع‌بندی و پل جلسهٔ بعد
CRUD کامل شد. جلسه بعد: INNER JOIN.
