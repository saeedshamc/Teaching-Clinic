# جلسه ۱۶: MySQL با PDO و Prepared Statements (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+ · MySQL 8 · افزونه PDO_MySQL فعال

## پیش‌نیازها
جلسات ۰۷، ۱۱، ۱۲.

## اهداف قابل‌اندازه‌گیری
می‌توانید با PDO وصل شوید؛ `ATTR_ERRMODE` را Exception کنید؛ کوئری را با placeholder آماده (**prepared statement**) اجرا کنید؛ و بگویید چرا الحاق رشته در SQL خطرناک است.

## چرا مهم است؟
SQL Injection هنوز رایج است. PDO + prepare راهٔ استاندارد امن برای اپ‌های PHP است. mysqli خام با الحاق رشته را به‌عنوان روش توصیه آموزش نمی‌دهیم.

## مفهوم عمیق
```php
$pdo = new PDO($dsn, $user, $pass, [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
]);
$stmt = $pdo->prepare('SELECT id, email FROM users WHERE email = ?');
$stmt->execute([$email]);
$user = $stmt->fetch();
```

Placeholder مقدار را جدا از دستور نگه می‌دارد.

## اشتباه‌های رایج
1. `"WHERE id = $id"` داخل SQL.
2. خاموش کردن exception و نادیده گرفتن خطا.
3. یک اتصال جدید در هر حلقه بدون نیاز.

## مثال گام‌به‌گام
`examples/01-pdo-basics.php` را بخوانید؛ اگر MySQL ندارید، بخش اتصال را با توضیح محیط خودتان تطبیق دهید. برای دمو بدون سرور، فایل `01-pdo-sqlite-demo.php` با SQLite همان الگو را نشان می‌دهد:

```bash
php session-16-pdo-basics/examples/01-pdo-sqlite-demo.php
```

## الگوی بهتر
DSN و رمز از env؛ هرگز در ریپو commit نکنید. یک تابع/کلاس اتصال متمرکز.

## تمرین‌ها
### آسان
یک ردیف با prepare درج کنید (SQLite دمو OK است).

### چالشی
جست‌وجوی LIKE امن با `?` و مقدار `'%'.$term.'%'` به‌عنوان پارامتر (نه الحاق داخل SQL خام بدون prepare).

## راهنمای حل
`prepare('... WHERE title LIKE ?')` سپس `execute(['%' . $term . '%'])`.

## خودآزمایی
1. prepared statement چیست؟
2. ERRMODE_EXCEPTION چه می‌کند؟
3. چرا الحاق SQL بد است؟
4. FETCH_ASSOC یعنی چه؟
5. تفاوت `?` و `:name`؟
6. رمز دیتابیس را کجا نگه داریم؟

## جمع‌بندی و پل جلسهٔ بعد
اتصال امن دارید. جلسهٔ بعد CRUD کامل‌تر با PDO.
