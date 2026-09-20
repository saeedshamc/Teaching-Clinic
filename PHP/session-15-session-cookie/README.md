# جلسه ۱۵: Session و Cookie امن (۱۲۰ دقیقه)

**محیط فرض:** PHP 8.2+ · `php -S localhost:8000`

## پیش‌نیازها
جلسه ۱۰ (فرم‌ها).

## اهداف قابل‌اندازه‌گیری
می‌توانید session را شروع و داده ذخیره کنید؛ تفاوت session و cookie را بگویید؛ پرچم‌های امن cookie (`HttpOnly`, `Secure`, `SameSite`) را تنظیم کنید؛ و session fixation را مفهومی بشناسید.

## چرا مهم است؟
Login بدون session امن، دزدیده می‌شود. تنظیمات غلط cookie مسیر حملهٔ XSS→سرقت نشست است.

## مفهوم عمیق
Session: داده روی سرور، شناسه معمولاً در cookie. Cookie: داده روی کلاینت.

```php
session_set_cookie_params([
    'lifetime' => 0,
    'path' => '/',
    'secure' => true,      // فقط HTTPS در تولید
    'httponly' => true,
    'samesite' => 'Lax',
]);
session_start();
session_regenerate_id(true); // بعد از login
```

در localhost بدون HTTPS، `secure` را برای دمو می‌توانید false بگذارید — در تولید true.

## اشتباه‌های رایج
1. ذخیرهٔ رمز یا کارت در cookie.
2. فراموش `session_regenerate_id` بعد از احراز هویت.
3. قرار دادن دادهٔ حساس در cookie قابل‌خواندن JS بدون HttpOnly.

## مثال گام‌به‌گام
```bash
cd session-15-session-cookie/examples
php -S localhost:8000
```
`counter.php` را در مرورگر چندبار باز کنید.

## الگوی بهتر
حداقل داده در session؛ انقضا؛ regenerate پس از login؛ خروج با `session_destroy`.

## تمرین‌ها
### آسان
بازدید شمار را در `$_SESSION` نگه دارید.

### چالشی
دکمهٔ reset که session را پاک کند.

## راهنمای حل
`$_SESSION = []; session_destroy();` و پاک کردن cookie نشست.

## خودآزمایی
1. دادهٔ session کجاست؟
2. HttpOnly چه می‌کند؟
3. SameSite به چه دردی می‌خورد؟
4. session fixation یعنی چه؟
5. چرا regenerate بعد از login؟
6. Secure یعنی چه؟

## جمع‌بندی و پل جلسهٔ بعد
نشست امن شد. جلسهٔ بعد MySQL با PDO و prepared statements.
