# جلسه ۱۸: Composer و PSR-4 (۹۰ دقیقه)

**محیط فرض:** PHP 8.2+ · Composer 2

## پیش‌نیازها
جلسه ۱۳ (autoload ذهنی).

## اهداف قابل‌اندازه‌گیری
می‌توانید `composer.json` با PSR-4 بنویسید؛ `composer dump-autoload` بزنید؛ یک پکیج را require کنید؛ و از `vendor/autoload.php` استفاده کنید.

## چرا مهم است؟
اکوسیستم PHP مدرن روی Composer می‌چرخد. بدون آن، مدیریت وابستگی دستی و شکننده است.

## مفهوم عمیق
```json
{
  "autoload": {
    "psr-4": { "Clinic\\": "src/" }
  }
}
```
نام `Clinic\Hello` ↔ فایل `src/Hello.php`.

```bash
composer dump-autoload
php examples/01-composer.php
```

## اشتباه‌های رایج
1. commit کردن `vendor/` در بعضی پروژه‌ها بدون سیاست مشخص (معمولاً نباید).
2. namespace و مسیر ناهماهنگ.
3. فراموش dump-autoload بعد از تغییر composer.json.

## مثال گام‌به‌گام
پوشهٔ `examples/composer-demo` را ببینید و دستورها را اجرا کنید.

## الگوی بهتر
یک `composer.json` در ریشهٔ اپ؛ autoload برای کد خودتان؛ وابستگی‌ها قفل با `composer.lock`.

## تمرین‌ها
### آسان
کلاس `Clinic\Adder` بسازید و از طریق autoload صدا بزنید.

### چالشی
یک پکیج کوچک مثل `vlucas/phpdotenv` را فقط مفهومی در README توضیح دهید کجا مفید است (نصب واقعی اختیاری).

## راهنمای حل
بعد از افزودن کلاس، `composer dump-autoload`؛ در اسکریپت `require vendor/autoload.php`.

## خودآزمایی
1. PSR-4 چیست؟
2. vendor/autoload.php چه می‌کند؟
3. dump-autoload چه زمانی؟
4. composer.lock چرا مهم است؟
5. تفاوت require و require-dev؟
6. چرا namespace باید با پوشه بخورد؟

## جمع‌بندی و پل جلسهٔ بعد
وابستگی‌ها مدیریت می‌شوند. جلسهٔ بعد امنیت: XSS، CSRF، SQLi، آپلود ناامن.
