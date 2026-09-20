# جلسه ۲۰: مسیر پایانی و پل به PHP (۱۲۰ دقیقه)

**محیط فرض:** SQLite 3

## پیش‌نیازها
جلسات ۱۸–۱۹ و ۱۷.

## اهداف قابل‌اندازه‌گیری
1. اسکیما + seed + حداقل ۳ گزارش را از صفر اجرا کنید
2. چک‌لیست مرور ۲۰ جلسه را صادقانه بزنید
3. مسیر لایهٔ اپ را در [`PHP/session-16-pdo-basics`](../../PHP/session-16-pdo-basics/) پیدا کنید
4. بگویید تفاوت کار با SQL خام و PDO چیست

## چرا مهم است؟
جمع‌بندی؛ اطمینان که فقط با `SQL/` به سطح قابل‌اتکا رسیده‌اید.

## مفهوم عمیق
فایل‌های capstone یک مسیر خطی می‌سازند:
1. `01-reset-schema.sql`
2. `02-seed.sql`
3. `03-reports.sql`

## اشتباه‌های رایج
1. رد شدن از PRAGMA.
2. گزارش روی DB خالی.
3. دست زدن به پروژه‌های اپ ریپو به‌جای خواندن جلسهٔ PHP.

## مثال گام‌به‌گام
```bash
cd SQL
sqlite3 library.db < session-20-capstone/examples/01-reset-schema.sql
sqlite3 library.db < session-20-capstone/examples/02-seed.sql
sqlite3 library.db < session-20-capstone/examples/03-reports.sql
```

## الگوی بهتر
همین سه مرحله را در پروژه‌های واقعی به migration/seed/report جدا کنید.

## تمرین‌ها
### آسان
یک کتاب و یک امانت جدید اضافه و گزارش باز را دوباره ببینید.
### چالشی
گزارش اعضای بدون هیچ امانت.

## راهنمای حل
`members LEFT JOIN loans ... WHERE loans.id IS NULL`

## خودآزمایی
1. ترتیب reset → seed → report چرا؟
2. FK را چگونه روشن می‌کنید؟
3. قدم بعدی PHP چیست؟
4. prepared statement کجا وارد می‌شود؟
5. Git/Docker در این دوره بود؟

## جمع‌بندی و پل
SQL کاربردی با SQLite تمام شد. برای اتصال از اپ: PHP PDO. بعداً Git و Docker جدا پلن می‌شوند.
