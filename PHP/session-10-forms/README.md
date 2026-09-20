# جلسه ۱۰: فرم‌ها، GET/POST، اعتبارسنجی و sanitize (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+ · سرور داخلی: `php -S localhost:8000`

## پیش‌نیازها
جلسات ۰۱–۰۹ (به‌ویژه آرایه‌ها، رشته‌ها، توابع).

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید داده‌ی فرم را از `$_GET`/`$_POST` بخوانید؛ متد درخواست را چک کنید؛ ورودی را اعتبارسنجی (validation) و پاک‌سازی (sanitize) کنید؛ و خروجی را برای HTML با `htmlspecialchars` امن چاپ کنید.

## چرا مهم است؟
تقریباً هر اپ PHP با فرم شروع می‌شود. بدون اعتبارسنجی و escape، XSS و دادهٔ خراب وارد سیستم می‌شود.

## مفهوم عمیق
- `$_GET`: داده از query string (برای فیلتر/جست‌وجوی ایدمپوتنت مناسب‌تر).
- `$_POST`: بدنهٔ درخواست (برای تغییر state مثل ثبت‌نام).
- `$_REQUEST` را در کد جدید ترجیح ندهید — منبع داده مبهم می‌شود.

پاک‌سازی ≠ اعتبارسنجی. Sanitize شکل داده را امن/یکدست می‌کند؛ validate قبول/رد منطقی است.

```php
$email = filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL);
$name  = trim((string)($_POST['name'] ?? ''));
$safe  = htmlspecialchars($name, ENT_QUOTES | ENT_SUBSTITUTE, 'UTF-8');
```

## اشتباه‌های رایج
1. چاپ مستقیم `$_POST` داخل HTML بدون `htmlspecialchars`.
2. اعتماد به validate فقط سمت مرورگر (`required` در HTML).
3. استفاده از `$_REQUEST` و گیج شدن بین GET و POST.

## مثال گام‌به‌گام
فایل‌های `examples/form.html` و `examples/handle.php` را با سرور داخلی اجرا کنید:

```bash
cd session-10-forms/examples
php -S localhost:8000
# مرورگر: http://localhost:8000/form.html
```

## الگوی بهتر
پردازش فقط روی `POST`؛ PRG (Post/Redirect/Get) برای جلوگیری از resubmit؛ پیام خطا در آرایهٔ `$errors`.

## تمرین‌ها
### آسان
فرمی با `age` بسازید که فقط عدد ۱ تا ۱۲۰ را قبول کند.

### چالشی
فرم تماس با `name`, `email`, `message`؛ همه را validate کنید و نسخهٔ escape‌شده را نمایش دهید.

## راهنمای حل
برای سن از `FILTER_VALIDATE_INT` با `min_range`/`max_range` استفاده کنید. برای ایمیل `FILTER_VALIDATE_EMAIL`. پیام را با طول حداقل ۱۰ کاراکتر بسنجید.

## خودآزمایی
1. تفاوت GET و POST چیست؟
2. چرا `htmlspecialchars` لازم است؟
3. `filter_input` چه کمکی می‌کند؟
4. چرا فقط HTML5 کافی نیست؟
5. PRG یعنی چه؟
6. `$_REQUEST` چرا نامناسب است؟

## جمع‌بندی و پل جلسهٔ بعد
ورودی کاربر را امن خواندید. جلسهٔ بعد خطاها و exception را منظم می‌کنیم تا failها قابل‌مدیریت شوند.
