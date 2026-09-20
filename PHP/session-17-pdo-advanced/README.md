# جلسه ۱۷: CRUD با PDO (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+ · SQLite برای دمو یا MySQL

## پیش‌نیازها
جلسه ۱۶.

## اهداف قابل‌اندازه‌گیری
می‌توانید Create/Read/Update/Delete را با prepare پیاده کنید؛ تراکنش ساده بزنید؛ و تعداد ردیف تأثیرگرفته را بخوانید.

## چرا مهم است؟
CRUD اسکلت هر پنل ادمین و API است. الگوی یکسان، باگ و injection را کم می‌کند.

## مفهوم عمیق
- Create: `INSERT`
- Read: `SELECT` (یکی / لیست)
- Update: `UPDATE ... WHERE id = ?`
- Delete: `DELETE WHERE id = ?`

تراکنش:
```php
$pdo->beginTransaction();
try {
    // چند دستور
    $pdo->commit();
} catch (Throwable $e) {
    $pdo->rollBack();
    throw $e;
}
```

## اشتباه‌های رایج
1. UPDATE/DELETE بدون WHERE.
2. اعتماد به `rowCount` روی همه درایورها یکسان (روی SQLite گاهی متفاوت).
3. نیمه‌کاره رها کردن تراکنش بعد از خطا.

## مثال گام‌به‌گام
```bash
php session-17-pdo-advanced/examples/01-crud.php
```

## الگوی بهتر
Repository نازک دور PDO؛ ورودی را قبل از SQL validate کنید.

## تمرین‌ها
### آسان
متد خواندن همه یادداشت‌ها.

### چالشی
Update عنوان فقط اگر ردیف وجود داشته باشد؛ در غیر این صورت پیام مشخص.

## راهنمای حل
بعد از execute اگر `rowCount() === 0` یعنی پیدا نشد (با آگاهی از محدودیت درایور).

## خودآزمایی
1. چهار عمل CRUD؟
2. تراکنش چه زمانی لازم است؟
3. چرا WHERE اجباری است؟
4. rollBack چه می‌کند؟
5. id را از کجا بگیرید بهتر است؟
6. چرا validate قبل از SQL؟

## جمع‌بندی و پل جلسهٔ بعد
CRUD آماده است. جلسهٔ بعد Composer و PSR-4.
