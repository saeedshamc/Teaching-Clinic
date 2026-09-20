# جلسه ۱۱: خطاها و Exception (۹۰ دقیقه)

**محیط فرض:** PHP 8.2+ · `declare(strict_types=1);`

## پیش‌نیازها
جلسات ۰۱–۱۰.

## اهداف قابل‌اندازه‌گیری
می‌توانید بین Notice/Warning و `Exception` تفاوت بگذارید؛ با `try/catch/finally` کار کنید؛ exception سفارشی بسازید؛ و در CLI خطای قابل‌فهم نشان دهید.

## چرا مهم است؟
بدون مدیریت خطا، اپ یا با صفحهٔ سفید می‌میرد یا دادهٔ نیمه‌کاره می‌نویسد. Exception مسیر کنترل‌شده برای شکست‌های قابل‌پیش‌بینی است.

## مفهوم عمیق
در PHP 8+ بسیاری از خطاهای نوعی TypeError پرتاب می‌کنند. برای منطق دامنه از `throw new InvalidArgumentException(...)` استفاده کنید. `finally` برای آزادسازی منبع است (بستن فایل)، چه موفق چه ناموفق.

```php
try {
    $n = parsePositive($_POST['n'] ?? '');
} catch (InvalidArgumentException $e) {
    echo $e->getMessage();
}
```

## اشتباه‌های رایج
1. `catch (Exception $e) {}` خالی — بلعیدن خطا.
2. پرتاب رشته به‌جای Exception.
3. استفاده از `@` برای سرکوب خطا به‌جای رفع علت.

## مثال گام‌به‌گام
```bash
cd session-11-error-handling/examples
php 01-errors.php
```

## الگوی بهتر
لایهٔ پایین exception پرتاب کند؛ لایهٔ UI پیام کاربرپسند بسازد؛ لاگ جزئیات فنی جدا باشد.

## تمرین‌ها
### آسان
تابعی بنویسید که اگر تقسیم بر صفر بود exception بدهد.

### چالشی
تابع `readConfig(string $path): array` که اگر فایل نبود `RuntimeException` و اگر JSON نامعتبر بود exception جدا بدهد.

## راهنمای حل
`file_exists` / `json_decode(..., true, 512, JSON_THROW_ON_ERROR)` در PHP 8.

## خودآزمایی
1. تفاوت Exception و Error چیست (در حد مفهوم)؟
2. finally چه زمانی اجرا می‌شود؟
3. چرا catch خالی خطرناک است؟
4. JSON_THROW_ON_ERROR چه می‌کند؟
5. پیام exception برای کاربر باید چه قدر جزئی باشد؟
6. `@` چرا ضدالگو است؟

## جمع‌بندی و پل جلسهٔ بعد
خطاها قابل‌کنترل شدند. جلسهٔ بعد OOP پایه: class، constructor، visibility.
