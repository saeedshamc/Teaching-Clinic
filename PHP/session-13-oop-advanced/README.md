# جلسه ۱۳: OOP پیشرفته، interface و autoload ساده (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+

## پیش‌نیازها
جلسه ۱۲.

## اهداف قابل‌اندازه‌گیری
می‌توانید `interface` تعریف و پیاده‌سازی کنید؛ تفاوت composition و inheritance را بگویید؛ با `spl_autoload_register` یا ساختار ساده فایل، کلاس را خودکار بارگذاری کنید؛ و وابستگی را به abstraction تزریق کنید.

## چرا مهم است؟
بدون interface و autoload، پروژه‌های متوسط غرق `require` دستی و coupling سخت می‌شوند.

## مفهوم عمیق
Interface قرارداد است. کلاس می‌تواند چند interface را implement کند. برای آموزش، یک autoload ساده بر اساس PSR-4 ذهنی: نام کلاس ↔ مسیر فایل.

```php
spl_autoload_register(function (string $class): void {
    $path = __DIR__ . '/src/' . str_replace('\\', '/', $class) . '.php';
    if (is_file($path)) require $path;
});
```

(در جلسه Composer همین را رسمی می‌کنیم.)

## اشتباه‌های رایج
1. ارث‌بری عمیق فقط برای reuse.
2. autoload که همیشه همه فایل‌ها را require می‌کند.
3. interface بدون نیاز واقعی.

## مثال گام‌به‌گام
```bash
php session-13-oop-advanced/examples/01-oop-advanced.php
```

## الگوی بهتر
Prefer composition: سرویس، Notifier را می‌گیرد نه اینکه از یک Base بزرگ ارث ببرد.

## تمرین‌ها
### آسان
Interface `Logger` با `log(string $msg): void` و دو پیاده‌سازی Echo و Array.

### چالشی
Autoload ساده برای کلاس `App\Greeter` در مسیر `src/App/Greeter.php`.

## راهنمای حل
namespace را به مسیر تبدیل کنید؛ فقط اگر فایل بود require کنید.

## خودآزمایی
1. interface چه اجباری می‌کند؟
2. composition یعنی چه؟
3. autoload چه زمانی صدا می‌شود؟
4. PSR-4 در یک جمله؟
5. چرا God base class بد است؟
6. تفاوت implements و extends؟

## جمع‌بندی و پل جلسهٔ بعد
حالا کد را ماژولارتر می‌نویسید. جلسهٔ بعد کار با فایل‌ها.
